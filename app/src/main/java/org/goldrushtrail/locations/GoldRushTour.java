package org.goldrushtrail.locations;

/**
 * Created by cs211d on 3/9/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

public class GoldRushTour implements Parcelable {
    private final Long id;
    private final String title;
    private final String tour;
    private final String details;
    private final Double latitude;
    private final Double longitude;
    private final Double cameraZoom;
    private final String drawable;

    public GoldRushTour(
            Long id,
            String title,
            String tour,
            String details,
            Double latitude,
            Double longitude,
            Double cameraZoom,
            String drawable
    ) {
        this.id = id;
        this.title = title;
        this.tour = tour;
        this.details = details;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cameraZoom = cameraZoom;
        this.drawable = drawable;
    }

    private GoldRushTour(Parcel in) {
        id = in.readLong();
        title = in.readString();
        tour = in.readString();
        details = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        cameraZoom = in.readDouble();
        drawable = in.readString();
    }

    public static final Creator<GoldRushTour> CREATOR = new Creator<GoldRushTour>() {
        public GoldRushTour createFromParcel(Parcel in) {
            return new GoldRushTour(in);
        }

        public GoldRushTour[] newArray(int size) {
            return new GoldRushTour[size];
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

    public Double getCameraZoom() { return cameraZoom; }

    public String getDrawable() {
        if ( drawable == null ) throw new IllegalStateException("NO IMAGE AVAILABLE!");
        return drawable;
    }

    public boolean hasImage() {
        return !(drawable == null || drawable.isEmpty());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(tour);
        dest.writeString(details);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeDouble(cameraZoom);
        dest.writeString(drawable);
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
            cameraZoom + ", " +
            drawable;
    }
}
