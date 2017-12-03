package org.goldrushtrail.fragments;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import org.goldrushtrail.R;
import org.goldrushtrail.locations.GoldRushTour;
import org.goldrushtrail.fragments.ToursListFragment.GoldRushTourFragmentListener;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link GoldRushTour} and makes a call to the
 * specified {@link GoldRushTourFragmentListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ToursListRecyclerViewAdapter extends RecyclerView.Adapter<ToursListRecyclerViewAdapter.ViewHolder>
{

    private final List<GoldRushTour> mTours;
    private final ToursListFragment.GoldRushTourFragmentListener mListener;
    //private final Resources mResources;

    public ToursListRecyclerViewAdapter(ToursListFragment.GoldRushTourFragmentListener listener)
    {
        mListener = listener;
        mTours = listener.getTours();
        //This is required for the image portion.
        //mResources = mListener.getResources();  //Was a part of SiteList....  TODO: Find out about the getResource() method.


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_goldrushtour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        holder.mTour = mTours.get(position);
        //First: use 'mTours.get(position)' in order to get the object that contains
        //  the data that will go into the ViewHolder object.
        //Summary: holder represents EACH CARD(xml)
        //         mTours.get(position) represents the data that goes into the card.
        holder.mTitleView.setText( mTours.get(position).getTitle() );
        //holder.mImageView.setImageResource();
        String details = mTours.get(position).getDetails();
        if ( details.length()>80 ) {
            holder.mDetailView.setText(details.substring(0, 80) + "...");
        } else {
            holder.mDetailView.setText(details);
        }
        //TODO: Create a method "private int getImage(String titleOfTour)"
        //getImage(mTours.get(position).getTour());

        holder.mImageView.setImageResource(getImage(mTours.get(position).getTour()));



        holder.mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (null != mListener)
                {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.tourListClickEventHandler(holder.mTour);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mTours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public final View mView;
        public final TextView mTitleView;
        public final TextView mDetailView;
        public final ImageView mImageView; //Added June 1
        public GoldRushTour mTour;

        public ViewHolder(View view)
        {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.tourTitle);
            mDetailView = (TextView) view.findViewById(R.id.tourDetail);
            mImageView = (ImageView) view.findViewById(R.id.image_tour_default); //Added
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + mDetailView.getText() + "'";
        }
    }
    private int getImage(String title)
    {
        //Log.d("onBind(): ", "title: "+title);
        int drawableId = 0;
        switch (title)
        {
            case "YerbaBuena":
                drawableId =  R.drawable.image_tour_yb;
                break;
            case "Commercial":
                drawableId = R.drawable.image_tour_co;
                break;
            case "Embarcadero":
                drawableId = R.drawable.image_tour_em;
                break;
            case "Financial":
                drawableId = R.drawable.image_tour_fi;
                break;
            case "JacksonSquare":
                drawableId = R.drawable.image_tour_js;
                break;
            case "Portsmouth":
                drawableId = R.drawable.image_tour_ps;
                break;
            default:
                Log.d("onBind(): ", "You misspelled something");
                drawableId =  R.drawable.image_tour_yb;
                break;
        }
        return drawableId;
    }


}



/*

    FROM: SiteListRecyclerViewAdapter.java



    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
        //ViewHolder is the inner class below
        holder.mLocation = mLocations.get(position);
        holder.mTitleView.setText(mLocations.get(position).getTitle());
        String details = mLocations.get(position).getDetails();
        if ( details.length()>80 ) {
            holder.mDetailsView.setText(details.substring(0, 80) + "...");
        } else {
            holder.mDetailsView.setText(details);
        }
        String tourEnum = mLocations.get(position).tourEnum().toString();
        int imageResource = R.drawable.noimage;
        if ( mLocations.get(position).hasImage() )
        {
            int tResource = mResources.getIdentifier(mLocations.get(position).getDrawable(), "drawable", mPackageName);
            if (tResource > 0)
            {
                imageResource = tResource;
            }
        }
        holder.mImageView.setImageResource(imageResource);

        holder.mView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mListener.sitesListClickEventHandler(holder.mLocation);
            }
        });
    }
 */
