package org.goldrushtrail.fragments;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.goldrushtrail.R;
import org.goldrushtrail.locations.GoldRushLocation;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SitesMapFragmentListener} interface
 * to handle interaction events.
 * Use the {@link SitesMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SitesMapFragment extends Fragment implements OnMapReadyCallback
{
    private SitesMapFragmentListener mListener;
    private MapView mMap;
    private Map<Marker, GoldRushLocation> mapLocations;
    public SitesMapFragment()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SitesMapFragment.
     */
    public static SitesMapFragment newInstance()
    {
        SitesMapFragment fragment = new SitesMapFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sites_map, container, false);

        mMap = (MapView) rootView.findViewById(R.id.mapView);
        mMap.onCreate(savedInstanceState);

        mMap.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMap.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mapLocations = new HashMap<>();
        try
        {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(
                    getActivity(), R.raw.style_json));

            if (!success) {
                Log.e("Map styling: ", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("Map styling: ", "Can't find style. Error: ", e);
        }
        for( GoldRushLocation location: mListener.getLocations())
        {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            //ss.setSpan(new  BackgroundColorSpan(Color.YELLOW ),57  ,68  ,0);
            //title.setSpan(new BackgroundColorSpan(LABEL_COLORS.get(location.tourEnum())) , 0, location.getTitle().length(), 0);
            //public static enum TOUR_ENUM {YB, EM, JS, FI, PS, CO};

            /*
            markerOptions = new MarkerOptions()
                .title()
                .icon(getIcon(location.toursEnum()))
             */

            MarkerOptions markerOptions;

            if(location.tourEnum() == GoldRushLocation.TOUR_ENUM.YB)
            {
                markerOptions = new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                        .title(location.getTitle());
            }
            else if(location.tourEnum() == GoldRushLocation.TOUR_ENUM.EM)
            {
                markerOptions = new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                        .title(location.getTitle());
            }
            else if(location.tourEnum() == GoldRushLocation.TOUR_ENUM.JS)
            {
                markerOptions = new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                        .title(location.getTitle());
            }
            else if(location.tourEnum() == GoldRushLocation.TOUR_ENUM.FI)
            {
                markerOptions = new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                        .title(location.getTitle());
            }
            else if(location.tourEnum() == GoldRushLocation.TOUR_ENUM.PS)
            {
                markerOptions = new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        .title(location.getTitle());
            }
            else if(location.tourEnum() == GoldRushLocation.TOUR_ENUM.CO)
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
            Marker marker = googleMap.addMarker(markerOptions);
            mapLocations.put(marker, location);

        }
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.795197,-122.400000), 14));


        //PolygonOptions polygonOptions = new PolygonOptions().addAll(Iterable<LatLng>);
        //mMap.addPolygon(polygonOptions);

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){

            @Override
            public void onInfoWindowClick(Marker marker) {
                mListener.onInfoWindowClickHandler(mapLocations.get(marker));
            }
        });
    }

    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof SitesMapFragmentListener)
        {
            mListener = (SitesMapFragmentListener) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMap.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMap.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMap.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMap.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMap.onLowMemory();
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface SitesMapFragmentListener
    {
        public ArrayList<GoldRushLocation> getLocations();
        public void onInfoWindowClickHandler(GoldRushLocation location);
        public Resources getResources();
        public String getPackageName();
    }
}
