package org.goldrushtrail;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import org.goldrushtrail.fragments.SitesListFragment;
import org.goldrushtrail.fragments.SitesMapFragment;
import org.goldrushtrail.locations.GCLAssetReader;
import org.goldrushtrail.locations.GoldCoastLocation;

public class NavigationActivity extends AppCompatActivity
    implements SitesListFragment.GoldRushSiteFragmentListener,
        SitesMapFragment.SitesMapFragmentListener
{
    private ArrayList<GoldCoastLocation> mLocations;
    private static final HashMap<String, Integer> LABEL_COLORS = new HashMap<String, Integer>() {{
        put(GoldCoastLocation.TOUR_ENUM.YB.toString(), R.color.colorYB);
        put(GoldCoastLocation.TOUR_ENUM.EM.toString(), R.color.colorEM);
        put(GoldCoastLocation.TOUR_ENUM.JS.toString(), R.color.colorJS);
        put(GoldCoastLocation.TOUR_ENUM.FI.toString(), R.color.colorFI);
        put(GoldCoastLocation.TOUR_ENUM.PS.toString(), R.color.colorPS);
        put(GoldCoastLocation.TOUR_ENUM.CO.toString(), R.color.colorCO);
    }};
    private boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        mLocations = new GCLAssetReader().getGoldCoastLocations(getApplicationContext());
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.navigation_sites:
                                selectedFragment = SitesListFragment.newInstance();
                                break;
                            case R.id.navigation_map:
                                selectedFragment = SitesMapFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, SitesListFragment.newInstance());
        transaction.commit();

    }

    @Override
    public ArrayList<GoldCoastLocation> getLocations()
    {
        return mLocations;
    }

    @Override
    public void onListClickEvent(View v, Long locationId)
    {
        GoldCoastLocation location = null;
        for ( GoldCoastLocation tLoc: mLocations ) {
            if ( tLoc.getId().equals(locationId)) {
                location = tLoc;
                break;
            }
        }
        if (mTwoPane)
        {
            Bundle arguments = new Bundle();
            arguments.putParcelable(GoldCoastLocationDetailFragment.ARG_LOCATION, location);
            GoldCoastLocationDetailFragment fragment = new GoldCoastLocationDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.goldcoastlocation_detail_container, fragment)
                    .commit();
        } else
        {
            Context context = v.getContext();
            Intent intent = new Intent(context, GoldCoastLocationDetailActivity.class);
            intent.putExtra(GoldCoastLocationDetailFragment.ARG_LOCATION, location);

            context.startActivity(intent);
        }

    }

}
