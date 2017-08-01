package com.zhdhr0000.architecture.parallax.view.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
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

    override fun initDataAndEvent() {
        recyclerview = view?.findViewById(R.id.recycler_view) as RecyclerView
        recyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.adapter = ParallaxAdapter(MutableList(20, { StringBuffer().append(it).toString() }))
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
