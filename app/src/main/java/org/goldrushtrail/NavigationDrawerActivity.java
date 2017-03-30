package org.goldrushtrail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.goldrushtrail.fragments.SitesListFragment;
import org.goldrushtrail.fragments.SitesMapFragment;
import org.goldrushtrail.fragments.ToursListFragment;
import org.goldrushtrail.locations.GCLAssetReader;
import org.goldrushtrail.locations.GoldRushLocation;
import org.goldrushtrail.locations.GoldRushTour;
import org.goldrushtrail.locations.NavigationItemHelpActivity;


import java.util.ArrayList;

public class NavigationDrawerActivity extends AppCompatActivity
        implements
        NavigationView.OnNavigationItemSelectedListener,
        SitesListFragment.GoldRushSiteFragmentListener,
        SitesMapFragment.SitesMapFragmentListener,
        ToursListFragment.GoldRushTourFragmentListener
{
    private ArrayList<GoldRushLocation> mLocations;
    private ArrayList<GoldRushTour> mTours;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        GCLAssetReader assetReader = new GCLAssetReader();
        mLocations = assetReader.getGoldRushLocations(this);
        mTours = assetReader.getGoldRushTours(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about)
        {

        }
        else if (id == R.id.nav_help)
        {
            Intent intent = new Intent(getApplicationContext(), NavigationItemHelpActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_web)
        {
            Uri uri = Uri.parse("http://goldrushtrail.org/");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
        else if (id == R.id.nav_developers)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public ArrayList<GoldRushLocation> getLocations()
    {
        return mLocations;
    }

    @Override
    public void sitesListClickEventHandler(GoldRushLocation location)
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
        Intent intent = new Intent(this, LocationDetailActivity.class);
        intent.putExtra(LocationDetailFragment.ARG_LOCATION, location);

        startActivity(intent);
    }

    @Override
    public void tourListClickEventHandler(GoldRushTour tour)
    {
        Intent intent = new Intent(this, TourDetailActivity.class);
        intent.putExtra(TourDetailFragment.ARG_TOUR, tour);

        startActivity(intent);
    }

    @Override
    public ArrayList<GoldRushTour> getTours()
    {
        return mTours;
    }
}
