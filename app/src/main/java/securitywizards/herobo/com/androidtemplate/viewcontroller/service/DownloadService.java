package securitywizards.herobo.com.androidtemplate.viewcontroller.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import androidtemplate.securitywizards.herobo.com.androidtemplate.R;
import securitywizards.herobo.com.androidtemplate.domain.DownloadItem;
import securitywizards.herobo.com.androidtemplate.other.Injector;
import securitywizards.herobo.com.androidtemplate.other.helper.AndroidUtils;
import securitywizards.herobo.com.androidtemplate.viewcontroller.activity.MainActivity;

public class DownloadService extends IntentService {
    @Inject
    protected Bus eventBus;
    @Inject
    NotificationManager notificationManager;
    @Inject
    AndroidUtils androidUtils;

    private int result = Activity.RESULT_CANCELED;
    public static final String KEY_DOWNLOADITEM ="KEY_DOWNLOADITEM";
    public static final int DOWNLOAD_NOTIFICATION_ID = 1000; // Why 1000? Why not? :)

    public DownloadService() {
        super("DownloadService");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Injector.inject(this);
        // Register the bus so we can send notifications.
        eventBus.register(this);
    }

    // will be called asynchronously by Android
    //onhandle intent can handle long running process :/
    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent.hasExtra(KEY_DOWNLOADITEM)) {
            DownloadItem downloadItem = (DownloadItem) intent.getSerializableExtra(KEY_DOWNLOADITEM);
            try {
                httpDownload(downloadItem);
            } catch (IOException e) {
                //TODO:Log the error
                androidUtils.alert(e.getMessage());
            }
         }else{
            androidUtils.alert("no downllad item extra");
        }
    }

    private void updateNotification(String message,int progress,int max,String fileName,DownloadItem downloadItem) {
        notificationManager.notify(DOWNLOAD_NOTIFICATION_ID, getNotification(message,progress,max,fileName,downloadItem));
    }
    /**
     * Creates a notification to show in the notification bar
     *
     * @param message the message to display in the notification bar
     * @return a new {@link android.app.Notification}
     */
    private Notification getNotification(String message,int progress,int max,String fileName,DownloadItem downloadItem) {
        boolean onGoing=true;
        Intent intent=null;
//        if(fileName!=null) {
//            intent = new Intent(Intent.ACTION_VIEW);
//            intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            onGoing=false;
//        }else if(downloadItem!=null){
//            //TODO : who will we notify when there is downloaditem but user clicked notification
//            intent = new Intent(this, MainActivity.class);
//            intent.putExtra(KEY_DOWNLOADITEM, downloadItem);
//        }else{
//            intent = new Intent(this, MainActivity.class);
//        }
        intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        return new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.app_name))
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentText(message)
                .setAutoCancel(false)
                .setOnlyAlertOnce(true)
                .setOngoing(onGoing)
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setProgress(max,progress,false)
                .getNotification();

    }

    boolean isServiceDestroyed=false;
    @Override
    public void onDestroy() {
        super.onDestroy();
        isServiceDestroyed=true;
        eventBus.unregister(this);
     }



    private void httpDownload(DownloadItem downloadItem) throws IOException {
        final File targetFile = new File(downloadItem.saveLocation);
        if(targetFile.exists()){
            publishCompleted(downloadItem,targetFile.getAbsolutePath());
            androidUtils.install(targetFile.getAbsolutePath());
            return;
        }
        final File temporaryFile=new File(downloadItem.saveLocation+".temp");
        if(temporaryFile.exists()){temporaryFile.delete();};

        InputStream input = null;
        OutputStream output = null;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(downloadItem.downloadLocation);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            // expect HTTP 200 OK, so we don't mistakenly save error report
            // instead of the file
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new Exception("Server returned HTTP " + connection.getResponseCode()
                        + " " + connection.getResponseMessage());
            }

            // this will be useful to display download percentage
            // might be -1: server did not report the length
            int fileLength = connection.getContentLength();

            // download the file
            input = connection.getInputStream();
            output = new FileOutputStream(temporaryFile);

            byte data[] = new byte[4096];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                if (isServiceDestroyed) {
                    input.close();
                    return;
                }
                total += count;
                // publishing the progress....
                if (fileLength > 0) // only if total length is known
//                    publishProgress((int) (total * 100 / fileLength));
                    publishProgress(downloadItem, (int) total, fileLength);
                output.write(data, 0, count);
            }
            temporaryFile.renameTo(targetFile);
            publishCompleted(downloadItem,targetFile.getAbsolutePath());
            if(isServiceDestroyed==false) {
                androidUtils.install(targetFile.getAbsolutePath());
            }
        } catch (Exception e) {
            publishFailed(downloadItem);
            return;
        } finally {
            try {
                if (output != null)
                    output.close();
                if (input != null)
                    input.close();
            } catch (IOException ignored) {
            }

            if (connection != null)
                connection.disconnect();
        }
    }

    private void publishProgress(DownloadItem downloadItem, int progress, int maxProgress){
        updateNotification(downloadItem.downloadLocation,progress,maxProgress,null,downloadItem);
        eventBus.post(produceDownloadServiceEvent(downloadItem,progress,maxProgress,false,false));
    }
    private void publishCompleted(DownloadItem downloadItem,String fileName) {
        updateNotification(downloadItem.downloadLocation, 100, 100, fileName, downloadItem);
        eventBus.post(produceDownloadServiceEvent(downloadItem,1,1,false,true));
    }
    private void publishFailed(DownloadItem downloadItem) {
        eventBus.post(produceDownloadServiceEvent(downloadItem,1,1,true,false));
    }

    public DownloadServiceEvent produceDownloadServiceEvent(DownloadItem downloadItem,int progress,int maxProgress,boolean isFailed,boolean isCompleted) {
        DownloadServiceEvent event=new DownloadServiceEvent();
        event.downloadItem=downloadItem;
        event.progress=progress;
        event.maxProgress=maxProgress;
        event.isFailed=isFailed;
        event.isCompleted=isCompleted;
        return event;
    }

    public static void startDownload(Activity me,String downloadLocation,String saveLocation){
           DownloadItem downloadItem=new DownloadItem();
           downloadItem.downloadLocation=downloadLocation;
           downloadItem.saveLocation=saveLocation;
           startDownload(me,downloadItem);
    }

    public static void startDownload(Activity me,DownloadItem downloadItem){
        Intent intent = new Intent(me, DownloadService.class);
        // add infos for the service which file to download and where to store
        intent.putExtra(KEY_DOWNLOADITEM,downloadItem);
        me.startService(intent);
    }

}