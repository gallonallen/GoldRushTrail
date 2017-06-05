package org.goldrushtrail;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
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
     * The dummy content this fragment is presenting.
     */
    private GoldRushTour mTour;

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
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.tour_toolbar_layout);
            if (appBarLayout != null)
            {
                appBarLayout.setTitle(mTour.getTitle());
                //appBarLayout.getContentScrim();//arg = Drawable
                //TODO: Find out how to create a Drawable object programmatically.
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
}
