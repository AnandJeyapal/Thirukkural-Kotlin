package com.work.thirukkural.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.work.thirukkural.utils.FONT_PATH


class CustomTextView : AppCompatTextView {

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    fun init() {
        val tf = Typeface.createFromAsset(context.assets, FONT_PATH)
        setTypeface(tf, Typeface.NORMAL)
    }
}