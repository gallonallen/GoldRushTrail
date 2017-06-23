package org.goldrushtrail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import org.goldrushtrail.fragments.ToursListFragment;
import org.goldrushtrail.locations.GoldRushLocation;

import java.util.ArrayList;

/**
 * An activity representing a single GoldRushTour detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link NavigationDrawerActivity}.
 */
public class TourDetailActivity extends AppCompatActivity
{
    ArrayList<GoldRushLocation> locations = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goldrushtour_detail);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        final Toolbar toolbar = (Toolbar) findViewById(R.id.tour_detail_toolbar);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.tour_toolbar_layout);
        collapsingToolbarLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final ProgressDialog pd = new ProgressDialog(TourDetailActivity.this);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pd.setTitle("Retrieving photos...");
                pd.setProgress(0);
                pd.setMax(280);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int progress = 0;
                        while(progress <= 100)
                        {
                            try
                            {
                                pd.setProgress(progress);
                                progress++;
                                Thread.sleep(10);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                        pd.dismiss();
                        TourDetailActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getApplicationContext(), "Swipe right to see other images ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                thread.start();
                pd.show();
                Intent intent = new Intent(getApplicationContext(), TourImagesActivity.class);
                locations = getIntent().getParcelableArrayListExtra(TourDetailFragment.ARG_LOCATIONS);
                intent.putParcelableArrayListExtra(TourDetailFragment.ARG_LOCATIONS, locations);
                startActivity(intent);
            }
        });


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null)
        {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putParcelable(TourDetailFragment.ARG_TOUR,
                    getIntent().getParcelableExtra(TourDetailFragment.ARG_TOUR));

            TourDetailFragment fragment = new TourDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.goldcoasttour_detail_container, fragment)
                    .commit();
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
            navigateUpTo(new Intent(this, ToursListFragment.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /*
    //REMEMBER: put 'View view' into the parameter to use in the layout's 'android:onClick' attribute.
    public void toolbarClick(View view)
    {
        //IDEA: Put an ID and Bind the CollapsingToolbar.  Then put an onClickListener on it.
        //YOU CAN'T USE xml 'android:onClick'.  See the following URL for details
        //https://issuetracker.google.com/issues/37048075
        //There is a problem with Android Lolipop and the application for some reason.
        //See the URL below for details
        //https://stackoverflow.com/questions/27531381/android-5-and-onclick-in-xml-layout

        Log.d("toolbarClick", " June 18, 2017");
        Toast.makeText(getApplicationContext(), "The onclick works", Toast.LENGTH_SHORT ).show();
        Intent intent = new Intent(this, TourImagesActivity.class);
        locations = getIntent().getParcelableArrayListExtra(TourDetailFragment.ARG_LOCATIONS);
        intent.putParcelableArrayListExtra(TourDetailFragment.ARG_LOCATIONS, locations);
        Toast.makeText(getApplicationContext(), "The onclick works", Toast.LENGTH_SHORT ).show();
        startActivity(intent);

    }
    */


}
