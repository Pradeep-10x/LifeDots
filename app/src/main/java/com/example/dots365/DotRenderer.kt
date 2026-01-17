package com.example.dots365

import android.content.Context
import android.graphics.*
import android.util.Log
import kotlin.math.ceil

object DotRenderer {

    private const val TAG = "DOTS365"

    fun render(context: Context, width: Int, height: Int): Bitmap {

        Log.d(TAG, "DotRenderer.render() start")

        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)

        // ===== BACKGROUND =====
        canvas.drawColor(Color.parseColor("#121212"))

        // ===== READ USER PREFS =====
        val mode = UserPrefs.getMode(context)
        val startDate = UserPrefs.getStartDate(context)
        val totalDays = UserPrefs.getTotalDays(context).coerceAtMost(365)

        // ===== CALCULATE CURRENT DAY INDEX =====
        val todayIndex = when (mode) {
            UserPrefs.MODE_YEARLY ->
                DateUtils.getDayOfYear() - 1

            else ->
                DateUtils.getDaysPassed(startDate, totalDays) - 1
        }.coerceIn(0, totalDays - 1)

        val totalDots = totalDays
        val dotsPerRow = 15
        val rows = ceil(totalDots / dotsPerRow.toFloat()).toInt()

        // ===== DESIGN VALUES (UNCHANGED) =====
        val dotRadius = width * 0.0110f
        val spacingX = dotRadius * 4.8f
        val spacingY = dotRadius * 4.4f

        val gridWidth = (dotsPerRow - 1) * spacingX
        val startX = (width - gridWidth) / 2f
        val startY = height * 0.18f

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        // ===== DRAW DOTS =====
        var index = 0
        for (row in 0 until rows) {
            for (col in 0 until dotsPerRow) {
                if (index >= totalDots) break

                val cx = startX + col * spacingX
                val cy = startY + row * spacingY

                paint.color = when {
                    index < todayIndex ->
                        Color.parseColor("#EDEDED")   // past (white)

                    index == todayIndex ->
                        Color.parseColor("#F57A18")   // today (orange)

                    else ->
                        Color.parseColor("#3A3A3A")   // future (grey)
                }

                canvas.drawCircle(cx, cy, dotRadius, paint)
                index++
            }
        }

        // ===== TEXT =====
        val daysPassed = todayIndex + 1
        val daysLeft = totalDots - daysPassed
        val percent = (daysPassed * 100) / totalDots

        val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#F57A18")
            textSize = width * 0.034f
            textAlign = Paint.Align.CENTER
            alpha = 210
        }

        canvas.drawText(
            "$daysLeft d left • $percent%",
            width / 2f,
            height * 0.80f,
            textPaint
        )

        Log.d(TAG, "DotRenderer.render() done")
        return bitmap
    }
}
