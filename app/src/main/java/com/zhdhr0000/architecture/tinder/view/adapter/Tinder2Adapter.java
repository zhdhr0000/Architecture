package com.zhdhr0000.architecture.tinder.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhdhr0000.architecture.R;

import java.util.List;

/**
 * Created by win7 on 2017/3/21.
 */

public class Tinder2Adapter extends RecyclerView.Adapter<Tinder2Adapter.Tinder2ViewHolder> {


    private List<String> data;

    public Tinder2Adapter(List<String> data){
        this.data = data;
    }

    @Override
    public Tinder2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Tinder2ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tinder, parent, false));
    }

    @Override
    public void onBindViewHolder(Tinder2ViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        if (data == null)return 0;
        return data.size();
    }

    class Tinder2ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public Tinder2ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
