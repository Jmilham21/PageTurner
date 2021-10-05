package com.jmilham.pageturner.helper.screen

import android.app.Activity
import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager


object KeyboardHelper {

    /***
     * Allows an Activity to hide soft keyboard with out
     * passing any view.
     */
    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    /***
     * Hides soft keyboard
     *
     * @param view
     *
     */
    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /***
     * Only allow click action to occur when touched.
     * Stubbing out potential other actions
     */
    val noActionTouch = OnTouchListener { v, event ->
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_UP -> v.performClick()
            else -> {
            }
        }
        v.performClick()
        true // the listener has consumed the event
    }
}