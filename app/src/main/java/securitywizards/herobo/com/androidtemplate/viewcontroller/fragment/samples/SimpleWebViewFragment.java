package securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.samples;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import securitywizards.herobo.com.androidtemplate.R;
import securitywizards.herobo.com.androidtemplate.other.helper.AndroidUtils;
import securitywizards.herobo.com.androidtemplate.viewcontroller.fragment.BaseFragment;

public class SimpleWebViewFragment extends BaseFragment {
    @Inject
    AndroidUtils androidUtils;
	private static final String KEY_HOMEPAGE = "KEY_HOMEPAGE";
	private View view;
	private WebView webView;
	private ProgressBar progressBar;
	private String homepage;


    boolean errorPageLoaded=false;

	@Override
	public View onCreateView2(LayoutInflater inflater, ViewGroup container,Bundle savedState) {
		view = inflater.inflate(R.layout.fragment_simplewebview, container, false);
		webView=(WebView) view.findViewById(R.id.webView);
		progressBar=(ProgressBar) view.findViewById(R.id.progressBar);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient(){
			@Override
		    public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if(isOnline()==false && errorPageLoaded==false){
					openLinkByWebView("file:///android_asset/error.html");
                    errorPageLoaded=true;
//				}else if(url.contains(homepage)==false){
//					MainActivity.this.openLinkByDefaultBrowser(url);
	            	return true;
				}else{
				setLastUrl(url);
		        return false;
				}
		    }
		    @Override
		    public void onReceivedError (WebView view, int errorCode, 
		        String description, String failingUrl) {
//		        if (errorCode == ERROR_TIMEOUT) {
		            openLinkByWebView("file:///android_asset/error.html");
//		        }
		    }
		});
		
		webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
				if(isOnline()==false){
					openLinkByWebView("file:///android_asset/error.html");
				}
            	
               if(progress < 100 && progressBar.getVisibility() == ProgressBar.GONE){
            	   progressBar.setVisibility(ProgressBar.VISIBLE);
               }
               progressBar.setProgress(progress);
               if(progress == 100) {
            	   progressBar.setVisibility(ProgressBar.GONE);
               }
               webView.setBackgroundColor(0);
            }
            
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            	openLinkByWebView("file:///android_asset/error.html");
            }
        });

		webView.setDownloadListener(new DownloadListener() {
	        public void onDownloadStart(final String url, String userAgent,
	                String contentDisposition, final String mimetype,
	                long contentLength) {
                final String fileName=Uri.parse(url).getLastPathSegment();
                androidUtils.alert("Download Started "+fileName);

                    Uri source = Uri.parse(url);
                    // Make a new request pointing to the .apk url
                    DownloadManager.Request request = new DownloadManager.Request(source);
                    // appears the same in Notification bar while downloading
                    request.setDescription(url);
                    request.setTitle(fileName);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                        request.allowScanningByMediaScanner();
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    }
                    // save the file in the "Downloads" folder of SDCARD
                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "SmartPigs.apk");
                    // get download service and enqueue file
                    if (url.endsWith(".apk")) {
                        request.setMimeType("application/vnd.android.package-archive");
                    }
                    DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);
	        }
	    });

        if (webViewBundle != null) {
            webView.restoreState(webViewBundle);
        }

		return view;
	}

    @Override
    public void onActivityCreated2(Bundle savedInstanceState) {
        openLinkByWebView(homepage);
    }

    //Base Fragment Events
    volatile Bundle webViewBundle;

    @Override
    public void onSaveInstanceState2(Bundle outState) {
        outState.putString(KEY_HOMEPAGE,homepage);
        outState.putParcelable("webViewBundle",webViewBundle);
        outState.putBoolean("errorPageLoaded",errorPageLoaded);
        webView.saveState(webViewBundle);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        homepage=savedInstanceState.getString(KEY_HOMEPAGE);
        webViewBundle=(Bundle)savedInstanceState.getParcelable("webViewBundle");
        errorPageLoaded=savedInstanceState.getBoolean("errorPageLoaded");
    }

    public void openLinkByWebView(String url){
		webView.stopLoading();
		if(isOnline()==false){
			url="file:///android_asset/error.html";
		}
		webView.loadUrl(url);
	}
	
	public void openLinkByDefaultBrowser(String url) {
		Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(i);
	}

	public String getLastUrl(){
		SharedPreferences myPrefs = getActivity().getSharedPreferences("myPrefs", Activity.MODE_PRIVATE);
		return myPrefs.getString("url",homepage);
	}
	
	public void setLastUrl(String url){
		SharedPreferences myPrefs =  getActivity().getSharedPreferences("myPrefs", Activity.MODE_PRIVATE);
		SharedPreferences.Editor e = myPrefs.edit();
		e.putString("url", url);
		e.commit();
	}
	
	public boolean isOnline() {
        Activity activity=getActivity();
        if(activity==null){
            return false;
        }
	    ConnectivityManager cm =
	        (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = cm.getActiveNetworkInfo();
	    if(networkInfo==null){
	    	return false;
	    }

	    if(networkInfo.isConnectedOrConnecting()){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	public boolean tieOnKeyDown(int keyCode, KeyEvent event){
        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch(keyCode)
            {
            case KeyEvent.KEYCODE_BACK:
                if(webView.canGoBack() == true){
                	webView.goBack();
                	return false;
                }else{
                	new AlertDialog.Builder(getActivity())
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Confirm Exit")
                    .setMessage("Are you sure you wanted to quit the app?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        	getActivity().finish();    
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
                	return false;
                }
            }

        }
    	return true;
	}
    public static final SimpleWebViewFragment newInstance(String homepageUrl) {
        SimpleWebViewFragment f = new SimpleWebViewFragment();
        Bundle bd = new Bundle();
        bd.putSerializable(KEY_HOMEPAGE, homepageUrl);
        f.setArguments(bd);
        f.homepage=homepageUrl;
        return f;
    }


}
