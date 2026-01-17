package com.example.dots365

import android.content.Context
import android.content.SharedPreferences

object UserPrefs {

    private const val PREFS_NAME = "dots_prefs"

    private const val KEY_MODE = "mode"
    private const val KEY_START_DATE = "start_date"
    private const val KEY_TOTAL_DAYS = "total_days"

    const val MODE_YEARLY = "yearly"
    const val MODE_WEEKLY = "weekly"
    const val MODE_CUSTOM = "custom"

    private fun prefs(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    /* ================= YEARLY ================= */

    fun saveYearly(context: Context) {
        prefs(context).edit()
            .putString(KEY_MODE, MODE_YEARLY)
            .putLong(KEY_START_DATE, System.currentTimeMillis())
            .putInt(KEY_TOTAL_DAYS, 365)
            .apply()
    }

    /* ================= WEEKLY ================= */

    fun saveWeekly(context: Context) {
        prefs(context).edit()
            .putString(KEY_MODE, MODE_WEEKLY)
            .putLong(KEY_START_DATE, System.currentTimeMillis())
            .putInt(KEY_TOTAL_DAYS, 7)
            .apply()
    }

    /* ================= CUSTOM RANGE ================= */

    fun saveCustomRange(context: Context, startMillis: Long, endMillis: Long) {
        val days =
            ((endMillis - startMillis) / (24 * 60 * 60 * 1000)).toInt() + 1

        prefs(context).edit()
            .putString(KEY_MODE, MODE_CUSTOM)
            .putLong(KEY_START_DATE, startMillis)
            .putInt(KEY_TOTAL_DAYS, days)
            .apply()
    }

    /* ================= READERS (USED BY WALLPAPER) ================= */

    fun getMode(context: Context): String =
        prefs(context).getString(KEY_MODE, MODE_YEARLY) ?: MODE_YEARLY

    fun getStartDate(context: Context): Long =
        prefs(context).getLong(KEY_START_DATE, System.currentTimeMillis())

    fun getTotalDays(context: Context): Int =
        prefs(context).getInt(KEY_TOTAL_DAYS, 365)
}
