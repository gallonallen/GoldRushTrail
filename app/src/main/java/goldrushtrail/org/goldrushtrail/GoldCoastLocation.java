package goldrushtrail.org.goldrushtrail;

/**
 * Created by cs211d on 3/9/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class GoldCoastLocation implements Parcelable {
    public static enum TOUR_ENUM {YB, EM, JS, FI, PS, CO};
    private final Long id;
    private final String title;
    private final String tour;
    private final String details;
    private final Double latitude;
    private final Double longitude;
    private final String drawable;
    private final Integer key;

    public GoldCoastLocation(
            Long id,
            String title,
            String tour,
            String details,
            Double latitude,
            Double longitude,
            String drawable,
            Integer key
    ) {
        this.id = id;
        this.title = title;
        this.tour = tour;
        this.details = details;
        this.latitude = latitude;
        this.longitude = longitude;
        this.drawable = drawable;
        this.key = key;
    }

    private GoldCoastLocation(Parcel in) {
        id = in.readLong();
        title = in.readString();
        tour = in.readString();
        details = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        drawable = in.readString();
        key = in.readInt();
    }

    public static final Creator<GoldCoastLocation> CREATOR = new Creator<GoldCoastLocation>() {
        public GoldCoastLocation createFromParcel(Parcel in) {
            return new GoldCoastLocation(in);
        }

        public GoldCoastLocation[] newArray(int size) {
            return new GoldCoastLocation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTour() {
        return tour;
    }

    public String getDetails() {
        return details;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getDrawable() {
        if ( drawable == null ) throw new IllegalStateException("NO IMAGE AVAILABLE!");
        return drawable;
    }

    public Integer getKey() {
        return key;
    }

    public boolean hasImage() {
        return !(drawable == null || drawable.isEmpty());
    }

    public TOUR_ENUM tourEnum()  {
        if (tour.equals("YerbaBuena")) return TOUR_ENUM.YB;
        else if (tour.equals("Commercial")) return TOUR_ENUM.CO;
        else if (tour.equals("Embarcadero")) return TOUR_ENUM.EM;
        else if (tour.equals("Financial")) return TOUR_ENUM.FI;
        else if (tour.equals("JacksonSquare")) return TOUR_ENUM.JS;
        else if (tour.equals("Portsmouth")) return TOUR_ENUM.PS;
        else throw new IllegalStateException("UNKOWN TOUR: "+tour);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(tour);
        dest.writeString(details);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeString(drawable);
        dest.writeInt(key);
    }

    @Override
    public String toString() {
        return
            id + ", " +
            title + ", " +
            tour + ", " +
            details.substring(0, 40) + "... , " +
            latitude + ", " +
            longitude + ", " +
            drawable + ", " +
            key;
    }
}
