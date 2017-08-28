package com.zhdhr0000.architecture.parallax.view.adapter

import android.graphics.Color
import android.graphics.drawable.Animatable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo
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
//        val controller = Fresco.newDraweeControllerBuilder()
//                .setUri(dataset[position])
//                .setControllerListener(AutoFitListener(holder?.image))
//                .build()
//        holder?.image?.controller = controller
        holder?.itemView?.setBackgroundColor(Color.rgb((Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt()))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ParallaxViewHolder {
        return ParallaxViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_parallax, parent, false))
    }

    class ParallaxViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var image: SimpleDraweeView = itemView?.findViewById(R.id.sdv_image) as SimpleDraweeView
    }

    internal class AutoFitListener(val image: SimpleDraweeView?) : BaseControllerListener<ImageInfo>() {
        override fun onFinalImageSet(id: String?, imageInfo: ImageInfo?, animatable: Animatable?) {
            val aspect = imageInfo?.height?.toFloat()?.div(imageInfo.width.toFloat()) as Float
            image?.aspectRatio = aspect
            super.onFinalImageSet(id, imageInfo, animatable)
        }
    }

}
