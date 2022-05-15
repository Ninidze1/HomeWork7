package com.example.facebook.app.util

import android.util.Log.d

object Logger {

    const val LOG_TAG = "apilog"

    fun logger(log: String) {
        d(LOG_TAG, log)
    }
}