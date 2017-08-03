package com.zhdhr0000.architecture.parallax.view.adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
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
        holder?.tvCode?.text = dataset[position]
        holder?.itemView?.setBackgroundColor(Color.rgb((Math.random() * 255).toInt(), (Math.random() * 255).toInt(), (Math.random() * 255).toInt()))
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ParallaxViewHolder {
        return ParallaxViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_parallax, parent, false))
    }

    class ParallaxViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var image: SimpleDraweeView = itemView?.findViewById(R.id.sdv_image) as SimpleDraweeView
        var tvCode: TextView = itemView?.findViewById(R.id.tv_code) as TextView
    }

    override fun onViewAttachedToWindow(holder: ParallaxViewHolder?) {
        super.onViewAttachedToWindow(holder)
        val onGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener{

        }
        val parallaxScrollListener = ViewTreeObserver.OnScrollChangedListener {

        }
        val onDrawListener = ViewTreeObserver.OnDrawListener{

        }
        val onPreDrawListener = ViewTreeObserver.OnPreDrawListener {
            return@OnPreDrawListener false
        }
        holder?.itemView?.setTag(holder.hashCode(), parallaxScrollListener)
        holder?.itemView?.viewTreeObserver?.addOnScrollChangedListener(parallaxScrollListener)
    }

    override fun onViewDetachedFromWindow(holder: ParallaxViewHolder?) {
        super.onViewDetachedFromWindow(holder)
        try {
            val parallaxScrollListener = holder?.itemView?.getTag(holder.hashCode()) as ViewTreeObserver.OnScrollChangedListener
            holder?.itemView?.viewTreeObserver?.removeOnScrollChangedListener(parallaxScrollListener)
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}