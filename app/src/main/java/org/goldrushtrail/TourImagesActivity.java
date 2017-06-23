package org.goldrushtrail;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import org.goldrushtrail.locations.GoldRushLocation;

import java.util.ArrayList;

public class TourImagesActivity extends AppCompatActivity
{
    private ArrayList<TourImagesFragment> fragmentsArrayList = new ArrayList<>();
    private ArrayList<GoldRushLocation> locationsArrayList = new ArrayList<>();
    private ArrayList<GoldRushLocation> locationHolder = new ArrayList<>();
    private Resources mResources;
    private String mPackName;
    private Toolbar toolbar;

    //TODO: Make sure the Fragment[] reference variable is global, so that the 'getItem()' method can access the array

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour_images);

        /*
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        */
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        //Handle the following in sequence:
        /*
        if (savedInstanceState == null)
        {
            Bundle arguments = new Bundle();
            arguments.putParcelable(TourDetailFragment.ARG_LOCATIONS,
                    getIntent().getParcelableExtra(TourDetailFragment.ARG_LOCATIONS));
            TourImagesFragment fragment = new TourImagesFragment();
            fragment.setArguments(arguments);
        }
        */

        mPackName = getApplicationContext().getPackageName();
        mResources = getResources();
        locationsArrayList = getIntent().getParcelableArrayListExtra(TourDetailFragment.ARG_LOCATIONS);

        if(locationsArrayList != null)
        {
            for(int i = 0; i<locationsArrayList.size(); i++)
            {
                GoldRushLocation site = locationsArrayList.get(i);
                //The line below is from the TourDetailFragment
                int imageInt = mResources.getIdentifier(locationsArrayList.get(i).getDrawable(), "drawable", mPackName);
                //Here's where the TourImageFragment is instantiated
                TourImagesFragment tour  = TourImagesFragment.newInstance(site.getTitle(), imageInt);

                //
                if(tour != null)
                {
                    //Log.d("onCreate(): ", "site title: "+site.getTitle()+", site image: "+site.getDrawable());
                    fragmentsArrayList.add(tour);
                }
            }

            //TODO: Make sure the onClick listener sends the data to 'this' Activity.
            //TODO: Find out why '.newInstance()' doesn't work in this context.
            //TODO: For GRT, you need to get the ArrayList from the Bundle.
            //TODO: Iterate through the ArrayList and create Fragment objects using the GRT data.
            pager.setAdapter(new TourImagesActivity.MyPagerAdapter(getSupportFragmentManager()));
        }
        toolbar = (Toolbar) findViewById(R.id.actionBar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //getSupportActionBar().setDisplayShowHomeEnabled(false);
            //The following line set the title of the toolbar to the first element inside of the FragmentArrayList, but it doesn't change when the
            //user swipes horizontally.
            //RATIONALE: onCreateView creates TWO fragments when first initiated.
            //TODO: Come up with some kind of logic to counter-act this issue.
            //getSupportActionBar().setTitle(fragmentsArrayList.get(0).getArguments().getString("title"));
        }
        ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                getSupportActionBar().setTitle(fragmentsArrayList.get(position).getArguments().getString("title"));
                if(locationHolder.size() == 0)
                {
                    locationHolder.add(locationsArrayList.get(position));
                }else {
                    locationHolder.add(0, locationsArrayList.get(position));
                }


            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    private class MyPagerAdapter extends FragmentPagerAdapter
    {

        public MyPagerAdapter(FragmentManager fm) {super(fm);}

        @Override
        public android.support.v4.app.Fragment getItem(int pos)
        {
            //getSupportActionBar().setTitle(fragmentsArrayList.get(pos).getArguments().getString("title"));
            return fragmentsArrayList.get(pos);
        }

        @Override
        public int getCount() {
            return fragmentsArrayList.size();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, LocationDetailFragment.class));
            //navigateUpTo(new Intent(this, NavigationDrawerActivity.class));
            return true;
        }
        else if(id == R.id.action_info)
        {
            Intent intent = new Intent(getApplicationContext(), LocationDetailActivity.class);
            intent.putExtra(LocationDetailFragment.ARG_LOCATION, locationHolder.get(0));
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tour_images, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
