package com.work.thirukkural.view

import android.view.animation.Interpolator

class SpringInterpolator: Interpolator {
    override fun getInterpolation(input: Float): Float {
        return (-Math.sin(Math.PI * (8 * input)) * Math.pow(
            Math.PI,
            -(2 * input).toDouble()
        ) * 1.2).toFloat()
    }
}