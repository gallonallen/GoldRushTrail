package org.goldrushtrail;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.Toast;

import org.goldrushtrail.locations.GoldRushLocation;

public class LocationDetailImageActivity extends AppCompatActivity {
    //private Bundle arguments;
    //private GoldRushLocation mLocation;
    //private String dataType;
    //private Toolbar toolbar;
    private String mTitle;
    private int mImageInt;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail_image);
        final ImageView image = (ImageView)findViewById(R.id.location_image);
        //toolbar = (Toolbar)findViewById(R.id.actionBarDetailImage);
        /*
        if (Build.VERSION.SDK_INT < 16)//before Jelly Bean Versions
        {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else // Jelly Bean and up
        {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int ui = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(ui);
            ActionBar actionBar = getSupportActionBar();
            actionBar.hide();
            //Hide actionbar
            //ActionBar actionBar = getActionBar();
            //actionBar.hide();
        }
        */

        //Bundle arguments = new Bundle();
        //image.setImageResource(arguments.getInt("image"));

        //arguments = getIntent().getExtras();

        mTitle = getIntent().getStringExtra("title");
        mImageInt = getIntent().getIntExtra("image", 0);

        if(mTitle == null || mImageInt == 0)
        {
            //Toast.makeText(getApplicationContext(), "mLocation is NULL", Toast.LENGTH_SHORT).show();
        }
        else
        {

            //dataType = getIntent().getType();
            image.setImageResource(mImageInt);
            Toast.makeText(getApplicationContext(), mTitle, Toast.LENGTH_SHORT).show();
            //toolbar.setTitle(mTitle);


            //mLocations = getArguments().getParcelable(TourDetailFragment.ARG_LOCATIONS);
            //Log.d("onCreate(): ", " "+arguments.get(TourDetailFragment.ARG_LOCATIONS).getClass().toString());
        }


    }//onCreate()


}
