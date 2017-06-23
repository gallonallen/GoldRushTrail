package org.goldrushtrail;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.goldrushtrail.locations.GoldRushTour;


/**
 * A fragment representing a single GoldRushLocation detail screen.
 * This fragment is either contained in a {@link TourDetailActivity}
 * in two-pane mode (on tablets) or a {@link TourDetailActivity}
 * on handsets.
 */
public class TourDetailFragment extends Fragment
{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_TOUR = "tour";
    /**
     * The fragment argument representing array of locations that will be used
     * for the ViewPager of images and titles in the onClck method of the Collapsable toolbar
     */
    public static final String ARG_LOCATIONS = "locations";

    /**
     * The dummy content this fragment is presenting.
     */
    private GoldRushTour mTour;


    //Added June 4 for the imagery
    private Resources mResources;
    private String mPackName;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TourDetailFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_TOUR))
        {
            // Load the dummy content specified by the
            //           FRAGMENT ARGUMENTS.
            // In a real-world scenario, use a Loader
            // to load content from a content provider.
            mTour = getArguments().getParcelable(ARG_TOUR);
            Activity activity = this.getActivity();
            mPackName = activity.getApplicationContext().getPackageName();
            mResources = activity.getResources();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.tour_toolbar_layout);
            if (appBarLayout != null)
            {
                appBarLayout.setTitle(mTour.getTitle());
                int imageInt = mResources.getIdentifier(mTour.getDrawable(), "drawable", mPackName);
                //Drawable drawable = getResources().getDrawable( imageInt );
                //TODO: Programmatically change the dimensions of the image via cropping, then setBackground(drawable); .
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imageInt);
                //Log.d("Bitmap width", " "+bitmap.getWidth());
                //Log.d("Bitmap height", " "+bitmap.getHeight());
                //The dimensions of the CollapsableToolbarLayout is precisely W: 1000, H: 488
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
        View rootView = inflater.inflate(R.layout.goldrushtour_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mTour != null)
        {
            ((TextView) rootView.findViewById(R.id.goldcoasttour_detail)).setText(mTour.getDetails());
        }

        return rootView;
    }
    /*
    This method will ensure that the height of the newly-cropped image will scale to the
    CollapsableToolbarLayout correctly
     */
    public int getNewHeight(int width)
    {
        //The dimensions of the CollapsableToolbarLayout is precisely W: 1000, H: 488
        double newHeight = width * .488;
        return (int)newHeight;
    }
    public int getYCoordinate(int bitmapHeight, int imageHeight)
    {
        int difference = bitmapHeight - imageHeight;
        return difference/2;
    }

}

