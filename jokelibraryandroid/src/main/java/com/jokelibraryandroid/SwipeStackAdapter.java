package com.jokelibraryandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gurpreet on 9/11/2016.
 */
public class SwipeStackAdapter extends BaseAdapter {

    private List<String> mData = new ArrayList<>();

    public SwipeStackAdapter(List<String> data) {
        this.mData = data;
    }

    @Override
    public int getCount() {
        if (!mData.isEmpty() && mData != null) {
            return mData.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public String getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        TextView textViewCard = (TextView) convertView.findViewById(R.id.textViewCard);
        textViewCard.setText(mData.get(position));

        return convertView;
    }
}
