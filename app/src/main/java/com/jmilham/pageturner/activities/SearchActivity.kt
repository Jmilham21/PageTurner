package com.jmilham.pageturner.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jmilham.pageturner.R

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

    }

    /***
     * Return to calling activity / context and don't display animation
     *
     * Intended to retain position / page content of calling context or activity
     */
    override fun onBackPressed() {
        finish()
        overridePendingTransition(0, 0)
    }
}