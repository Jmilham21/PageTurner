package com.jmilham.pageturner.helper.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log

object NavigationHelper {

    /***
     * Helper method to send user to new Activity from nearly any context
     *
     * Could potentially be issue with Theme Wrapped Context.
     */
    fun sendToActivity(
        currentContext: Context,
        destinationActivity: Activity,
        extras: Bundle? = null
    ) {
        val intent = Intent(currentContext, destinationActivity.javaClass)
        intent.putExtra("bundle", extras)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK) // forcing a clear stack
        currentContext.startActivity(intent)
        try {
            (currentContext as Activity).overridePendingTransition(0, 0)
        } catch (exception: Exception) {
            Log.e("sendToActivity", exception.message.toString())
        }
    }
}