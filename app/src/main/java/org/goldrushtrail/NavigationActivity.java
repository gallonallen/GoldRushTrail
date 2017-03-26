package org.goldrushtrail;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.ArrayList;

import org.goldrushtrail.fragments.ToursListFragment;
import org.goldrushtrail.fragments.SitesListFragment;
import org.goldrushtrail.fragments.SitesMapFragment;
import org.goldrushtrail.locations.GCLAssetReader;
import org.goldrushtrail.locations.GoldRushLocation;
import org.goldrushtrail.locations.GoldRushTour;

public class NavigationActivity extends AppCompatActivity
    implements SitesListFragment.GoldRushSiteFragmentListener,
        SitesMapFragment.SitesMapFragmentListener,
        ToursListFragment.GoldRushTourFragmentListener
{
    private ArrayList<GoldRushLocation> mLocations;
    private ArrayList<GoldRushTour> mTours;
    private boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        GCLAssetReader assetReader = new GCLAssetReader();
        mLocations = assetReader.getGoldRushLocations(this);
        mTours = assetReader.getGoldRushTours(this);

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
                            case R.id.navigation_tours:
                                selectedFragment = ToursListFragment.newInstance();
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
    public ArrayList<GoldRushLocation> getLocations()
    {
        return mLocations;
    }

    @Override
    public void onListClickEventHandler(GoldRushLocation location)
    {
        launchDetailsActivity(location);
    }

    @Override
    public void onInfoWindowClickHandler(GoldRushLocation location)
    {
        launchDetailsActivity(location);
    }

    private void launchDetailsActivity(GoldRushLocation location)
    {
        if (mTwoPane)
        {
            Bundle arguments = new Bundle();
            arguments.putParcelable(LocationDetailFragment.ARG_LOCATION, location);
            LocationDetailFragment fragment = new LocationDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.goldcoastlocation_detail_container, fragment)
                    .commit();
        } else
        {
            Intent intent = new Intent(this, LocationDetailActivity.class);
            intent.putExtra(LocationDetailFragment.ARG_LOCATION, location);

            startActivity(intent);
        }

    }

    @Override
    public void onTourInteraction(GoldRushTour goldRushTour)
    {

    }

    @Override
    public ArrayList<GoldRushTour> getTours()
    {
        return mTours;
    }
}
