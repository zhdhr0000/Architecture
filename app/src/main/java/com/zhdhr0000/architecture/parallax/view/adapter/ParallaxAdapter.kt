package com.zhdhr0000.architecture.parallax.view.adapter

import android.graphics.Color
import android.graphics.drawable.Animatable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo
import com.zhdhr.library.ParallaxView
import com.zhdhr0000.architecture.R

/**
 * Created by zhangyinghao on 2017/8/1.
 */
class ParallaxAdapter(var dataset: MutableList<String>) : RecyclerView.Adapter<ParallaxAdapter.ParallaxViewHolder>() {

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ParallaxViewHolder?, position: Int) {
        holder?.image?.setImageURI(dataset[position])
        holder?.parallax?.setParallaxDirection(ParallaxView.DIRECTION_VERTICAL)
        holder?.itemView?.setBackgroundColor(Color.rgb((Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt()))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ParallaxViewHolder {
        return ParallaxViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_parallax, parent, false))
    }

    class ParallaxViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var image: SimpleDraweeView = itemView?.findViewById(R.id.sdv_image) as SimpleDraweeView
        var parallax: ParallaxView = itemView?.findViewById(R.id.parallax_view) as ParallaxView
    }

}
