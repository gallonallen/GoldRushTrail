package org.goldrushtrail;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

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
            Activity activity = this.getActivity();
            mPackName = activity.getApplicationContext().getPackageName();
            mResources = activity.getResources();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null)
            {
                int imageInt = mResources.getIdentifier(mLocation.getDrawable(), "drawable", mPackName);
                Drawable drawable = getResources().getDrawable( imageInt );
                appBarLayout.setTitle(mLocation.getTitle());
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
            ((TextView) rootView.findViewById(R.id.goldcoastlocation_detail)).setText(mLocation.getDetails());
        }

        return rootView;
    }
}
