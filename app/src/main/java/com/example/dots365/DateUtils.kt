package com.example.dots365

import java.util.Calendar

object DateUtils {

    private const val DAY_MS = 24 * 60 * 60 * 1000L

    /**
     * Main function used by renderer / wallpaper
     */
    fun getDaysPassed(startMillis: Long, totalDays: Int): Int {
        val now = System.currentTimeMillis()
        val diff = now - startMillis
        val days = (diff / DAY_MS).toInt() + 1
        return days.coerceIn(0, totalDays)
    }

    /**
     * Helper for yearly mode only
     */
    fun getDayOfYear(): Int {
        return Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
    }
}
