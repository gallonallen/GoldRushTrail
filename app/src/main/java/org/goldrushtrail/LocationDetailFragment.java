package org.goldrushtrail;

import android.app.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.goldrushtrail.locations.GoldRushLocation;

/**
 * A fragment representing a single GoldRushLocation detail screen.
 * This fragment is either contained in a {@link LocationDetailActivity}
 * in two-pane mode (on tablets) or a {@link LocationDetailActivity}
 * on handsets.
 */
public class LocationDetailFragment extends Fragment
{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_LOCATION = "location";

    /**
     * The dummy content this fragment is presenting.
     */
    private GoldRushLocation mLocation;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LocationDetailFragment()
    {
    }
    //Added June 4 for the imagery
    private Resources mResources;
    private String mPackName;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_LOCATION))
        {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mLocation = getArguments().getParcelable(ARG_LOCATION);
            final Activity activity = this.getActivity();
            mPackName = activity.getApplicationContext().getPackageName();
            mResources = activity.getResources();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            appBarLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity.getApplicationContext(), LocationDetailImageActivity.class);
                    intent.putExtra("title", mLocation.getTitle());
                    int imageInt = mResources.getIdentifier(mLocation.getDrawable(), "drawable", mPackName);
                    intent.putExtra("image",imageInt );
                    startActivity(intent);
                }
            });
            if (appBarLayout != null)
            {
                int imageInt = mResources.getIdentifier(mLocation.getDrawable(), "drawable", mPackName);
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageInt);
                //Drawable drawable = getResources().getDrawable( imageInt );
                appBarLayout.setTitle(mLocation.getTitle());
                int width = bitmap.getWidth();
                int height = getNewHeight(width);
                int yCoordinate = getYCoordinate(bitmap.getHeight(), height);

                bitmap = Bitmap.createBitmap(bitmap, 0, yCoordinate, width, height);
                Drawable drawable = new BitmapDrawable(activity.getResources(),bitmap);
                appBarLayout.setBackground(drawable);
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.goldrushlocation_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mLocation != null)
        {
            //Adding the extra whitespace so that the user can scroll the last line of the paragraph up to the middle of the screen, rather then view it from the very bottom(UX).
            ((TextView) rootView.findViewById(R.id.goldcoastlocation_detail)).setText(mLocation.getDetails()+"\n\n\n\n\n\n\n\n\n\n");
        }

        return rootView;
    }
    private int getNewHeight(int width)
    {
        //The dimensions of the CollapsableToolbarLayout is precisely W: 1000, H: 488
        double newHeight = width * .488;
        return (int)newHeight;
    }

    private int getYCoordinate(int bitmapHeight, int imageHeight)
    {
        int difference = bitmapHeight - imageHeight;
        return difference/2;
    }
}


