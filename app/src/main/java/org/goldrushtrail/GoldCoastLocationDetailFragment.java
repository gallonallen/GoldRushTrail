package org.goldrushtrail;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.goldrushtrail.locations.GoldCoastLocation;

/**
 * A fragment representing a single GoldCoastLocation detail screen.
 * This fragment is either contained in a {@link GoldCoastLocationListActivity}
 * in two-pane mode (on tablets) or a {@link GoldCoastLocationDetailActivity}
 * on handsets.
 */
public class GoldCoastLocationDetailFragment extends Fragment
{
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_LOCATION = "location";

    /**
     * The dummy content this fragment is presenting.
     */
    private GoldCoastLocation mLocation;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GoldCoastLocationDetailFragment()
    {
    }

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
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null)
            {
                appBarLayout.setTitle(mLocation.getTitle());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.goldcoastlocation_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mLocation != null)
        {
            ((TextView) rootView.findViewById(R.id.goldcoastlocation_detail)).setText(mLocation.getDetails());
        }

        return rootView;
    }
}
