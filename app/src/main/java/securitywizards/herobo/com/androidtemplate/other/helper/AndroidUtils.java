package securitywizards.herobo.com.androidtemplate.other.helper;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaenelle Isidro (jaenelleisidro@yahoo.com) on 12/20/2014.
 */
public class AndroidUtils {
    private final DownloadManager manager;
    private final Context context;
    private String defaultDownloadLocation;

    public AndroidUtils(Context context){
        this.context=context;
        this.manager=(DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
    }

    public String convertUriGalleryToString(Uri selectedImage){
        String[] filePathColumn = { MediaStore.Images.Media.DATA };

        Cursor cursor = context.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    public List<String> userEmailAccounts() {
        AccountManager accountManager = AccountManager.get(context);
        final Account[] accounts = accountManager.getAccountsByType("com.google");
        final List<String> emailAddresses = new ArrayList<String>(accounts.length);
        for (final Account account : accounts) {
            emailAddresses.add(account.name);
        }
        return emailAddresses;
    }

    //requires permission WRITE_EXTERNAL_STORAGE
    public void download(String downloadLink,String fileName){
        Uri source = Uri.parse(downloadLink);
        DownloadManager.Request request=new DownloadManager.Request(source);
        request.setDescription(downloadLink);
        request.setTitle(fileName);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB){
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,fileName);
        request.setMimeType("application/vnd.android.package-archive");

        manager.enqueue(request);

    }
//    long enqueue=1;
//    BroadcastReceiver receiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
//                long downloadId = intent.getLongExtra(
//                        DownloadManager.EXTRA_DOWNLOAD_ID, 0);
//                DownloadManager.Query query = new DownloadManager.Query();
//                query.setFilterById(enqueue);
//                Cursor c = manager.query(query);
//                if (c.moveToFirst()) {
//                    int columnIndex = c
//                            .getColumnIndex(DownloadManager.COLUMN_STATUS);
//                    if (DownloadManager.STATUS_SUCCESSFUL == c
//                            .getInt(columnIndex)) {
//                        install(uriString);
//                    }
//                }
//            }
//        }
//    };

    public void showDownload(Activity me) {
        Intent i = new Intent();
        i.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
        me.startActivity(i);
    }

    public void install(String fileLocation){
        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/download/" + fileName)), "application/vnd.android.package-archive");
//        fileLocation="/mnt/sdcard/a.apk";
        intent.setDataAndType(Uri.fromFile(new File(fileLocation)), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public boolean isBackgorundServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public void alert(String message){
        Toast.makeText(context, message,Toast.LENGTH_LONG).show();
    }

    public void simpleDownload(String urlString,File destination) throws IOException {
        URL website = new URL(urlString);
        ReadableByteChannel rbc;
        rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream(destination);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
    }

    public void downloadAndInstall() {


    }

    private boolean isAppInstalled(String packageName) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    public boolean isAppInstalledByAppName(String appName)
    {
        List<PackageInfo> PackList = context.getPackageManager().getInstalledPackages(0);
        for (int i=0; i < PackList.size(); i++)
        {
            PackageInfo PackInfo = PackList.get(i);
            if (  (PackInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
            {
                String appTitle = PackInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
                if(appName.equals(appTitle)){
                    return true;
                }
            }
        }
        return false;
    }

    public File getDefaultDownloadLocation(String fileName){
        return new File(getDefaultDownloadLocation(),fileName);
    }

    public File getDefaultDownloadLocation() {
        /*
             * This sections checks the phone to see if there is a SD card. if
             * there is an SD card, a directory is created on the SD card to
             * store the test log results. If there is not a SD card, then the
             * directory is created on the phones internal hard drive
             */
        //if there is no SD card, create new directory objects to make directory on device
        File directory;
        if (Environment.getExternalStorageState() == null) {
            //create new file directory object
            directory = new File(Environment.getDataDirectory()+ "/Downloads/");
            // if no directory exists, create new directory
            if (!directory.exists()) {
                directory.mkdir();
            }
            return directory;
            // if phone DOES have sd card
        } else if (Environment.getExternalStorageState() != null) {
            // search for directory on SD card
            directory = new File(Environment.getExternalStorageDirectory()
                    + "/Downloads/");

            // if no directory exists, create new directory to store test
            // results
            if (!directory.exists()) {
                directory.mkdir();
            }
            return directory;
        }// end of SD card checking
        return new File("/");
//        throw new Exception("can't find default download location");
    }

    /**
     * loads fragment on specied viewgroup Id
     * @param actionBarActivity the actionbar activity that contains the fragment
     * @param viewGroupFragmentContainerId contains the viewgroup id that will contain the fragment
     * @param fragment the fragment that we will place inside the viewgroup
     */

    public static void loadFragment(ActionBarActivity actionBarActivity,int viewGroupFragmentContainerId,Fragment fragment){
        final FragmentManager fragmentManager = actionBarActivity.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(viewGroupFragmentContainerId, fragment)
                .commit();

    }


    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void sendMail(String to,String subject,String message){
        sendMail(to,subject,message,"Send Email");
    }

    public void sendMail(String to,String subject,String message,String dialogTitle){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/html");
        intent.putExtra(Intent.EXTRA_EMAIL,to);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        context.startActivity(Intent.createChooser(intent,dialogTitle));

    }
}
