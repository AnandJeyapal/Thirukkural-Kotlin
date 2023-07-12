package com.work.thirukkural.view

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.OvershootInterpolator
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.FrameLayout
import android.widget.ImageView
import com.work.thirukkural.R

class ShakingBell(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private var bellCover: ImageView
    private var bell: ImageView
    private var alarmOn: View
    private var alarmOff: View
    private var animation: Animation
    private var animation2:Animation
    private var animation3: ValueAnimator

   init {
       LayoutInflater.from(getContext()).inflate(R.layout.shaking_bell, this, true)
       bellCover = findViewById<View>(R.id.bell_cover) as ImageView
       bell = findViewById<View>(R.id.bell) as ImageView

       alarmOn = findViewById<View>(R.id.notification_on)
       alarmOff = findViewById<View>(R.id.notification_off)
       animation = RotateAnimation(
           0f,
           -20f,
           Animation.RELATIVE_TO_SELF,
           0.5f,
           Animation.RELATIVE_TO_SELF,
           0.3f
       )
       animation.interpolator = SpringInterpolator()
       animation.duration = 1000

       animation2 = TranslateAnimation(0f, 20f, 0f, 0f)
       animation2.interpolator = SpringInterpolator()
       animation2.duration = 1000
       animation2.startOffset = 200

       animation3 = ValueAnimator.ofFloat(1f)
       animation3.duration = 600
       animation3.interpolator = OvershootInterpolator()
       animation3.startDelay = 600
       animation3.addListener(object : Animator.AnimatorListener {
           override fun onAnimationStart(animation: Animator) {}
           override fun onAnimationEnd(animation: Animator) {
               bellCover.visibility = GONE
               bell.visibility = GONE
               alarmOff.visibility = GONE
               alarmOn.visibility = VISIBLE
           }

           override fun onAnimationCancel(animation: Animator) {}
           override fun onAnimationRepeat(animation: Animator) {}
       })
   }

    fun shake() {
        alarmOff.visibility = GONE
        alarmOn.visibility = GONE
        bellCover.visibility = VISIBLE
        bell.visibility = VISIBLE
        bellCover.startAnimation(animation)
        bell.startAnimation(animation2)
        animation3.start()
    }

    fun setAlarmOff() {
        bellCover.visibility = GONE
        bell.visibility = GONE
        alarmOn.visibility = GONE
        alarmOff.visibility = VISIBLE
    }
}