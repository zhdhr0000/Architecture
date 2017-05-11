package com.zhdhr0000.architecture.tinder.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zhdhr0000.architecture.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyinghao on 2017/5/11.
 */

public class Tinder3Adapter extends BaseAdapter {

    private List data;

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tinder tinder;
        if (convertView == null) {
            tinder = new Tinder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tinder, parent, false);
            tinder.textView = (TextView) convertView.findViewById(R.id.textview);
            convertView.setTag(tinder);
        } else {
            tinder = (Tinder) convertView.getTag();
        }
        tinder.textView.setText("position " + position);
        return convertView;
    }

    public void setData(List data) {
        this.data = data;
    }

    class Tinder {
        TextView textView;
    }
}
