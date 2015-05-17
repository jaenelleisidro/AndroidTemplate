package securitywizards.herobo.com.androidtemplate.viewcontroller.service;


import securitywizards.herobo.com.androidtemplate.domain.DownloadItem;

/**
 * Created by ripplewave on 4/16/2015.
 */
public class DownloadServiceEvent {
    public DownloadItem downloadItem;
    public int progress;
    public int maxProgress;
    public boolean isCompleted=false;
    public boolean isFailed=false;
}
