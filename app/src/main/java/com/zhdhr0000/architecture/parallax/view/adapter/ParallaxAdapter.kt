package com.zhdhr0000.architecture.parallax.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.view.SimpleDraweeView
import com.zhdhr0000.architecture.R

/**
 * Created by zhangyinghao on 2017/8/1.
 */
class ParallaxAdapter(var dataset: MutableList<String>) : RecyclerView.Adapter<ParallaxViewHolder>() {

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ParallaxViewHolder?, position: Int) {
        holder?.image?.setImageURI(dataset[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ParallaxViewHolder {
        return ParallaxViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_parallax,parent,false))
    }

}

class ParallaxViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView){
    var image:SimpleDraweeView = itemView?.findViewById(R.id.sdv_image) as SimpleDraweeView
}