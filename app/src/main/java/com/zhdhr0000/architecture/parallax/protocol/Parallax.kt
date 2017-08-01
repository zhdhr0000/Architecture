package com.zhdhr0000.architecture.parallax.protocol

import com.zhdhr0000.architecture.base.IPresenter
import com.zhdhr0000.architecture.base.IView

/**
 * Created by zhangyinghao on 2017/7/28.
 */
interface Parallax{
    interface View : IView{

    }
    interface Presenter:IPresenter<View>{

    }
}