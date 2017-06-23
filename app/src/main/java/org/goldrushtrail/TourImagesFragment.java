package org.goldrushtrail;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TourImagesFragment extends Fragment
{
    public static int newInstanceCount=0;
    public static ArrayList<String> titleArray = new ArrayList<>();



    public TourImagesFragment()
    {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleArray.add(null);
        Log.d("onCreate: ", "null added to titleArray, as a 'sentinel' placeholder");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        //Log.d("onCreateView: ", "View being created. newInstance count: "+newInstanceCount);
        View view = inflater.inflate(R.layout.fragment_tour_images, container, false);
        ImageView locationImage = (ImageView)view.findViewById(R.id.images_of_tour);
        locationImage.setBackgroundResource(getArguments().getInt("image"));
        //((MainFragmentActivity) getActivity()).setActionBarTitle(YOUR_TITLE);

        //ViewPager viewPager = (ViewPager)view.findViewById(R.id.pager);
        //Log.d("viewpager position: ", " "+viewPager.getCurrentItem());
        getActivity().setTitle(getArguments().getString("title"));
        //Log.d("onCreateView: ", "image: "+getArguments().getInt("image")+", title: "+getArguments().getString("title"));




        //TODO:
        //To get the title, use the following 'getArguments().getInt("image")'
        return view;
    }
    //FIRST: An ArrayList of fragments are created in TourImagesActivity
    //SECOND: OnCreate is created twice
    public static TourImagesFragment newInstance(String title, int image)
    {
        //Log.d("newInstance: ", " "+newInstanceCount+"; \ttitle: "+title);
        newInstanceCount++;

        TourImagesFragment f = new TourImagesFragment();
        Bundle b = new Bundle();
        b.putString("title", title);
        titleArray.add(title);
        b.putInt("image", image);

        f.setArguments(b);

        return f;
    }

}
