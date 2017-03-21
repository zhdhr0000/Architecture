package com.zhdhr0000.architecture.tinder.widget

/**
 * Created by zhangyh on 2017/3/21.
 */
class LinearRegressionKt {

    var N: Int
    var alpha: Double
    var beta: Double
    var R2: Double
    var svar: Double
    var svar0: Double
    var svar1: Double

    constructor(x: FloatArray, y: FloatArray) {
        if (x.size != y.size) throw IllegalArgumentException("array lengths are not equal")
        N = x.size

        val sumx: Double = (0..(N - 1)).sumByDouble { x[it].toDouble() }
        val sumy: Double = (0..(N - 1)).sumByDouble { y[it].toDouble() }
        val sumx2: Double = (0..(N - 1)).sumByDouble { (x[it] * x[it]).toDouble() }

        val xbar: Double = sumx / N
        val ybar: Double = sumy / N

        val xxbar = (0..(N - 1)).sumByDouble { (x[it] - xbar) * (x[it] - xbar) }
        val yybar = (0..(N - 1)).sumByDouble { (y[it] - ybar) * (y[it] - ybar) }
        val xybar = (0..(N - 1)).sumByDouble { (x[it] - xbar) * (y[it] - ybar) }

        beta = xybar / xxbar
        alpha = ybar - beta * xbar

        var rss = 0.0
        var ssr = 0.0

        for (i in 0..(N - 1)) {
            val fit = beta * x[i] + alpha
            rss += (fit - y[i]) * (fit - y[i])
            ssr += (fit - ybar) * (fit - ybar)
        }

        val degreesOfFreedom = N - 2
        R2 = ssr / yybar
        svar = rss / degreesOfFreedom
        svar1 = svar / xxbar
        svar0 = svar / N + xbar * xbar * svar1
    }

    fun intercept(): Double = alpha
    fun slope() = beta
    fun R2() = R2
    fun interceptStdErr() = Math.sqrt(svar0)
    fun slopeStdErr() = Math.sqrt(svar1)
    fun predict(x: Double) = Math.sqrt(beta * x + alpha)
}