package com.example.felipe.tareaandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Photo> mDataset;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        ImageView mImgtView;
        ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.title);
            mImgtView = (ImageView) v.findViewById(R.id.image);
        }
    }

    public ListAdapter(Context context, ArrayList<Photo> myDataset) {
        mDataset = myDataset;
        mContext = context;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_imagen, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextView.setText(mDataset.get(position).getTitle());
        Glide.with(mContext).load(mDataset.get(position).getImageUrl()).into(holder.mImgtView);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}

