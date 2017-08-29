package com.zhdhr0000.architecture.parallax.view.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhdhr.library.ParallaxView
import com.zhdhr0000.architecture.R

/**
 * Created by zhangyinghao on 2017/8/29.
 */

class ParallaxLandAdapter(var dataset: MutableList<String>) : RecyclerView.Adapter<ParallaxAdapter.ParallaxViewHolder>() {

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ParallaxAdapter.ParallaxViewHolder?, position: Int) {
        holder?.image?.setImageURI(dataset[position])
        holder?.parallax?.setParallaxDirection(ParallaxView.DIRECTION_HORIZONTAL)
        holder?.itemView?.setBackgroundColor(Color.rgb((Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt()))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ParallaxAdapter.ParallaxViewHolder? {
        return ParallaxAdapter.ParallaxViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_parallax_land, parent, false))
    }

}