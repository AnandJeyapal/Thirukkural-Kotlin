package com.work.thirukkural.utils

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import com.work.thirukkural.R
import com.work.thirukkural.data.entities.Kural

fun getColorToUpdate(adhigaramId: Int, resources: Resources): Int {
    val colors = resources.getIntArray(R.array.primary_colors)
    val colorIndex = adhigaramId % 9
    return colors[colorIndex]
}

fun getDarkColorToUpdate(adhigaramId: Int, resources: Resources): Int {
    val colors = resources.getIntArray(R.array.primary_dark_colors)
    val colorIndex = adhigaramId % 9
    return colors[colorIndex]
}

fun shareMoreContent(type: Int, context: Context) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_SUBJECT, getMoreContentTitle(type))
    intent.putExtra(
        Intent.EXTRA_TEXT,
        getShareContent(type, context)
    )
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    context.startActivity(intent)
}

fun getMoreContentTitle(type: Int) = if (type == 1) "திருக்குறள்" else "திருக்குறளின் பெருமைகள்"

fun getShareContent(type: Int, context: Context): String {
    val stringBuilder = StringBuilder()
    if (type == 1) {
        val str = context.resources.getString(R.string.second_thirukkural_more_content)
        stringBuilder.append(str)
    } else {
        val titleOne = context.resources.getString(R.string.thirukkural_more_title_one)
        val titleTwo = context.resources.getString(R.string.thirukkural_more_title_two)
        val contentOne = context.resources.getString(R.string.thirukkural_more_content_one)
        val contentTwo = context.resources.getString(R.string.thirukkural_more_content_two)
        stringBuilder.append(titleOne).append("\n\n").append(contentOne).append("\n\n").append(titleTwo)
            .append("\n\n").append(contentTwo)
    }
    return stringBuilder.toString()
}

fun shareKural(kural: Kural, context: Context, explanations: List<Int>) {
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
    intent.putExtra(
        Intent.EXTRA_TEXT,
        kural.toString() + "\n\n" + getExplanations(explanations, kural)
    )
    context.startActivity(intent)
}

fun getExplanations(explanations: List<Int>, kural: Kural) {
    val stringBuilder = StringBuilder()
    if(explanations.contains(1)) {
        stringBuilder.append("சாலமன் பாப்பையா உரை").append("\n").append(kural.first_exp).append("\n")
    }
    if(explanations.contains(2)) {
        stringBuilder.append("மு.வ உரை").append("\n").append(kural.second_exp).append("\n")
    }
    if(explanations.contains(3)) {
        stringBuilder.append("English").append("\n").append(kural.third_exp).append("\n")
    }
}