package goldrushtrail.org.goldrushtrail;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{

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

        for( GoldCoastLocation location: mLocations)
        {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(location.getTitle()));
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.795197,-122.404224), 14));
    }
}
