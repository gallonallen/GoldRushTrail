package goldrushtrail.org.goldrushtrail;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{
    private TextView infoWindowTitle;
    private ImageView infoWindowImage;

    private GoogleMap mMap;
    private ArrayList<GoldCoastLocation> mLocations;
    private static final HashMap<String, Integer> LABEL_COLORS = new HashMap<String, Integer>() {{
        put(GoldCoastLocation.TOUR_ENUM.YB.toString(), R.color.colorYB);
        put(GoldCoastLocation.TOUR_ENUM.EM.toString(), R.color.colorEM);
        put(GoldCoastLocation.TOUR_ENUM.JS.toString(), R.color.colorJS);
        put(GoldCoastLocation.TOUR_ENUM.FI.toString(), R.color.colorFI);
        put(GoldCoastLocation.TOUR_ENUM.PS.toString(), R.color.colorPS);
        put(GoldCoastLocation.TOUR_ENUM.CO.toString(), R.color.colorCO);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mLocations = new GCLAssetReader().getGoldCoastLocations(getApplicationContext());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap)
    {

        mMap = googleMap;


        for(int i= 0; i<=5; i++)

        for( GoldCoastLocation location: mLocations)
        {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            //ss.setSpan(new  BackgroundColorSpan(Color.YELLOW ),57  ,68  ,0);
            //title.setSpan(new BackgroundColorSpan(LABEL_COLORS.get(location.tourEnum())) , 0, location.getTitle().length(), 0);
            //public static enum TOUR_ENUM {YB, EM, JS, FI, PS, CO};

            //TODO: Create a helper method that determines the marker color based off of the tour type
            //TODO: Create a method that splits the title string and adds a '\n' in the middle if it exceeds a certain length, since some titles go off the screen.
            /*
            markerOptions = new MarkerOptions()
                .title()
                .icon(getIcon(location.toursEnum()))
             */
            MarkerOptions markerOptions;

            if(location.tourEnum() == GoldCoastLocation.TOUR_ENUM.YB)
            {
                markerOptions = new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        .title(location.getTitle());
            }
            else if(location.tourEnum() == GoldCoastLocation.TOUR_ENUM.EM)
            {
                markerOptions = new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                        .title(location.getTitle());
            }
            else if(location.tourEnum() == GoldCoastLocation.TOUR_ENUM.JS)
            {
                markerOptions = new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                        .title(location.getTitle());
            }
            else if(location.tourEnum() == GoldCoastLocation.TOUR_ENUM.FI)
            {
                markerOptions = new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        .title(location.getTitle());
            }
            else if(location.tourEnum() == GoldCoastLocation.TOUR_ENUM.PS)
            {
                markerOptions = new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        .title(location.getTitle());
            }
            else if(location.tourEnum() == GoldCoastLocation.TOUR_ENUM.CO)
            {
                markerOptions = new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))
                        .title(location.getTitle());
            }else {
                markerOptions = new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        .title(location.getTitle());
            }


            Log.d("In the for-loop", "location.tourEnum() : "+location.tourEnum());
            mMap.addMarker(markerOptions);

        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.795197,-122.400000), 14));


        //PolygonOptions polygonOptions = new PolygonOptions().addAll(Iterable<LatLng>);
        //mMap.addPolygon(polygonOptions);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){

            @Override
            public void onInfoWindowClick(Marker marker) {
                //TODO: Put the location.getTitle() in an intent for the GCLDetailActivity, so that it can iterate through the ArrayList to find the GCLObject
                //TODO: Ask Karl if you should implement the DetailActivity now or wait
                String title = marker.getTitle();
                //Intent intent = new Intent(this, DetailFragment.class); //TODO: Put the appropriate class file in here
                //intent.putExtras(title);
                Toast.makeText(getApplicationContext(), "Info window clicked \n"+title, Toast.LENGTH_SHORT).show();
                //startActivity(intent);
            }
        });
    }
}
