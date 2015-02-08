package com.flesh.recyclerfragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by aaronfleshner on 2/8/15.
 */
public class SimpleStringRecyclerViewAdapter extends RecyclerView.Adapter<SimpleStringRecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> mDataSet;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView mTextView;
        String mData;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.text);
            v.setOnClickListener(this);
        }

        //Bind all of the data to the view
        public void bindView(String data) {
            mData = data;
            mTextView.setText(mData);
        }

        // set the click listener
        @Override
        public void onClick(View v) {
            if (mData != null)
                Log.e("MyAdapter", mData);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SimpleStringRecyclerViewAdapter(String[] myDataset) {
        mDataSet = new ArrayList<String>(Arrays.asList(myDataset));
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SimpleStringRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                         int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_text_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bindView(mDataSet.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void removeItem(int pos) {
        mDataSet.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos, mDataSet.size());
    }

    public void addItem(String item, int pos) {
        mDataSet.add(pos, item);
        notifyItemInserted(pos);
        notifyItemRangeInserted(pos, mDataSet.size());
    }
}