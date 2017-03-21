package com.zhdhr0000.architecture.tinder.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.zhdhr0000.architecture.R
import com.zhdhr0000.architecture.tinder.view.adapter.TinderAdapter.TinderViewHolder

/**
 * Created by win7 on 2017/3/21.
 */
class TinderAdapter : RecyclerView.Adapter<TinderViewHolder> {
    var data: List<String>

    constructor(data: List<String>) : super() {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TinderViewHolder {
        return TinderViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_tinder, parent, false))
    }

    override fun onBindViewHolder(holder: TinderViewHolder?, position: Int) {
        holder?.textview?.text = data[position]
    }

    override fun getItemCount(): Int {
        if (data == null) {
            return 0
        }
        return data.size
    }

    class TinderViewHolder : RecyclerView.ViewHolder {
        var textview: TextView
        constructor(itemView: View?) : super(itemView) {
            this.textview = itemView?.findViewById(R.id.textview) as TextView
        }
    }
}