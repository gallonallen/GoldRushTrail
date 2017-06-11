package org.goldrushtrail.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.goldrushtrail.R;
import org.goldrushtrail.locations.GoldRushTour;

import java.util.ArrayList;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link GoldRushTourFragmentListener}
 * interface.
 */
public class ToursListFragment extends Fragment
{

    private GoldRushTourFragmentListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ToursListFragment()
    {
    }
     //Why use newInstance() in this case???
     /*
     Definition:
     The newInstance() method of Class class can invoke zero-argument constructor
     whereas newInstance() method of Constructor class can invoke any number of arguments.
     So Constructor class is preferred over Class class.
      */
    public static ToursListFragment newInstance()
    {
        ToursListFragment fragment = new ToursListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_goldrushtour_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView)
        {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new ToursListRecyclerViewAdapter(mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof GoldRushTourFragmentListener)
        {
            mListener = (GoldRushTourFragmentListener) context;
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface GoldRushTourFragmentListener
    {
        void tourListClickEventHandler(GoldRushTour tour);
        ArrayList<GoldRushTour> getTours();
    }
}

/*
FROM: NavigationDrawerActivity.java

    @Override
    public void tourListClickEventHandler(GoldRushTour tour)
    {
        Intent intent = new Intent(this, TourDetailActivity.class);
        intent.putExtra(TourDetailFragment.ARG_TOUR, tour);
                        //TourDetailFragment.ARG_TOUR is used
                        //as the 'key' for the 'value' "tour"

        startActivity(intent);
    }
 */
