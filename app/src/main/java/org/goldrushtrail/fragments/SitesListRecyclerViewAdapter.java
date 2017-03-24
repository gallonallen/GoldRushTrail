package org.goldrushtrail.fragments;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.goldrushtrail.R;
import org.goldrushtrail.locations.GoldCoastLocation;

import java.util.ArrayList;

public class SitesListRecyclerViewAdapter
        extends RecyclerView.Adapter<SitesListRecyclerViewAdapter.ViewHolder>
{

    private final SitesListFragment.GoldRushSiteFragmentListener mListener;
    private final ArrayList<GoldCoastLocation> mLocations;
    private final Resources mResources;
    private final String mPackageName;


    public SitesListRecyclerViewAdapter(SitesListFragment.GoldRushSiteFragmentListener listener )
    {
        mListener = listener;
        mLocations = mListener.getLocations();
        mResources = mListener.getResources();
        mPackageName = mListener.getPackageName();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.goldcoastlocation_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {
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
                mListener.onListClickEvent(v, holder.mLocation.getId());
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mLocations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private final View mView;
        private final TextView mTitleView;
        private final TextView mDetailsView;
        private final ImageView mImageView;
        private GoldCoastLocation mLocation;

        public ViewHolder(View view)
        {
            super(view);
            mView = view;
            mTitleView = (TextView) view.findViewById(R.id.title);
            mDetailsView = (TextView) view.findViewById(R.id.details);
            mImageView = (ImageView) view.findViewById(R.id.imageView);
        }

        @Override
        public String toString()
        {
            return super.toString() + " '" + mTitleView.getText() + "'";
        }
    }
}

