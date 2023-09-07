package com.work.thirukkural.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.work.thirukkural.R
import com.work.thirukkural.data.entities.Kural
import com.work.thirukkural.ui.kural.KuralActivity


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
    var shareContent = kural.kural
    val p = getExplanations(explanations, kural)
    if(p.isNotEmpty()) {
        shareContent += ("\n\n" + p.toString())
    }
    intent.putExtra(Intent.EXTRA_TEXT, shareContent)
    context.startActivity(intent)
}

fun getExplanations(explanations: List<Int>, kural: Kural): StringBuilder {
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
    return stringBuilder
}

fun showNotification(context: Context?, kuralId: Int, kuralDescription: String) {
    if(context == null) {
        return
    }
    val kuralIntent = Intent(context, KuralActivity::class.java)
    kuralIntent.putExtra("kuralId", kuralId)

    // Channel ID
    val channelId = "defaultId"

    // Notification Manager
    val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    // Create the TaskStackBuilder
    val kuralPendingIndent: PendingIntent? = TaskStackBuilder.create(context).run {
        // Add the intent, which inflates the back stack
        addNextIntentWithParentStack(kuralIntent)
        // Get the PendingIntent containing the entire back stack
        getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        createNotificationChannel(channelId, notificationManager)
    }
    val notification = NotificationCompat.Builder(context, channelId)
    .setSmallIcon(R.drawable.ic_events)
    .setContentTitle("தினம் ஒரு குறள்")
    .setStyle(NotificationCompat.BigTextStyle().bigText(kuralDescription))
    .addAction(R.drawable.ic_search, "உரை", kuralPendingIndent).
    setContentIntent(kuralPendingIndent).setAutoCancel(true).build()
    notification.flags = notification.flags or Notification.FLAG_AUTO_CANCEL
    notificationManager.notify(1, notification)

}

@RequiresApi(Build.VERSION_CODES.O)
fun createNotificationChannel(channelId: String, notificationManager: NotificationManager) {
    val notificationChannel = NotificationChannel(channelId, "Life", NotificationManager.IMPORTANCE_LOW)
    with(notificationChannel) {
        enableLights(true)
        lightColor = Color.RED
        enableVibration(true)
        vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
    }
    notificationManager.createNotificationChannel(notificationChannel)

}
