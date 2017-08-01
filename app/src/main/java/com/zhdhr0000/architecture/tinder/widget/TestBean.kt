package com.zhdhr0000.architecture.tinder.widget

/**
 * Created by zhangyinghao on 2017/5/24.
 */
data class TestBean(var a: Int = 1, var b: String = "b", var c: Int = 2, var d: String = "d") {

    val e: Int = 0;

    init {
        c = a * c
    }

    constructor(a: Int, c: Int) : this(a, "b", c, "d")

    constructor(a: Int, c: Int, b: String,d:String) : this(a, b, c, d)

}