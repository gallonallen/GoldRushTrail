package org.goldrushtrail.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public ToursListRecyclerViewAdapter(ToursListFragment.GoldRushTourFragmentListener listener)
    {
        mListener = listener;
        mTours = listener.getTours();
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
        holder.mTitleView.setText( mTours.get(position).getTitle() );
        String details = mTours.get(position).getDetails();
        if ( details.length()>80 ) {
            holder.mDetailView.setText(details.substring(0, 80) + "...");
        } else {
            holder.mDetailView.setText(details);
        }

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
        public GoldRushTour mTour;

        public ViewHolder(View view)
        {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.tourTitle);
            mDetailView = (TextView) view.findViewById(R.id.tourDetail);
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + mDetailView.getText() + "'";
        }
    }
}
