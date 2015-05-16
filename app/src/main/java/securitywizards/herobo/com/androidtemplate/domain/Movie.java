package securitywizards.herobo.com.androidtemplate.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Jaenelle Isidro (jaenelleisidro@yahoo.com) on 1/4/2015.
 */
public class Movie implements Serializable {

    /*
    {
    name: "Mr & Mrs Murder",
    start_time: "8:30pm",
    end_time: "9:30pm",
    channel: "TEN",
    rating: "M"
    }
     */
    public int id;
    public String name;
    public String start_time;
    public String end_time;
    public String channel;
    public String rating;


}
