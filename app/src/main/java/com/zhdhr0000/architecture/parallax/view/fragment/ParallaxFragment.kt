package com.zhdhr0000.architecture.parallax.view.fragment

import com.zhdhr0000.architecture.R
import com.zhdhr0000.architecture.base.BaseFragment
import com.zhdhr0000.architecture.parallax.presenter.ParallaxPresenter
import com.zhdhr0000.architecture.parallax.protocol.Parallax
import com.zhdhr0000.architecture.parallax.view.activity.ParallaxViewActivity
import kotlinx.android.synthetic.main.fragment_parallax.*


/**
 * Created by zhangyinghao on 2017/7/28.
 */

class ParallaxFragment : BaseFragment<Parallax.Presenter>(), Parallax.View {


    override fun initDataAndEvent() {
        button_1.setOnClickListener { ParallaxViewActivity.startVertical(mActivity) }
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
