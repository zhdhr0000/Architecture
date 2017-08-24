package com.zhdhr0000.architecture.parallax.view.fragment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.zhdhr0000.architecture.R
import com.zhdhr0000.architecture.base.BaseFragment
import com.zhdhr0000.architecture.parallax.presenter.ParallaxPresenter
import com.zhdhr0000.architecture.parallax.protocol.Parallax
import com.zhdhr0000.architecture.parallax.view.adapter.ParallaxAdapter

/**
 * Created by zhangyinghao on 2017/7/28.
 */

class ParallaxFragment : BaseFragment<Parallax.Presenter>(), Parallax.View {

    lateinit var recyclerview: RecyclerView
    val imgs: Array<String> = arrayOf(
            "https://img.wowdsgn.com/instagram/category/images/1ee6b8e8-8226-4779-90dd-eb5957489006_2dimension_392x238.png",
            "https://img.wowdsgn.com/instagram/category/images/8377a668-c8b7-40eb-bbfe-0e71f88aa046_2dimension_395x240.png",
            "https://img.wowdsgn.com/instagram/category/images/e7240f7f-9bf6-4930-ba4d-24f2478d60b0_2dimension_382x256.png",
            "https://img.wowdsgn.com/instagram/category/images/aa4770d3-2dab-4e01-9289-c9f57ee326d8_2dimension_471x288.png",
            "https://img.wowdsgn.com/instagram/category/images/43d1799f-0fe4-4ac5-be79-0cc4b43b608d_2dimension_402x286.png",
            "https://img.wowdsgn.com/instagram/category/images/fb1292be-5265-4802-ab50-5c1db462c872_2dimension_391x255.png",
            "https://img.wowdsgn.com/social/insta/026de96b72f140649549320135297b911491813654803026de96b72f140649549320135297b911491813654804_2dimension_750x750",
            "https://img.wowdsgn.com/social/insta/0WSeHw_1494721860376_2dimension_1224x1224",
            "https://img.wowdsgn.com/social/insta/17HBUEf1d4840b615d4de397af20afa40f7dc51491567630348_2dimension_828x828",
            "https://img.wowdsgn.com/social/insta/1WT8H7_1493640270164_2dimension_1080x1080",
            "https://img.wowdsgn.com/social/insta/1Yc8IP6f17fab5c0dc4d92ab7b633a86490fca1493169903783_2dimension_539x404",
            "https://img.wowdsgn.com/social/insta/1wu4He_1494570158007_2dimension_864x864"
    )

    override fun initDataAndEvent() {
        recyclerview = view?.findViewById(R.id.recycler_view) as RecyclerView
        recyclerview.layoutManager = LinearLayoutManager(activity as Context?)
        recyclerview.adapter = ParallaxAdapter(MutableList(imgs.size, { imgs[it] }))
    }

    override fun initPresenter(): Parallax.Presenter? {
        return ParallaxPresenter()
    }

    override fun getLayoutID(): Int {
        return R.layout.fragment_parallax
    }

    override fun showError(type: Int, msg: String?) {
        showToast(msg)
    }
}
