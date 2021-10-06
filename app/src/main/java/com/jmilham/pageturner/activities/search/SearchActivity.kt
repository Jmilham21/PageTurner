package com.jmilham.pageturner.activities.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.jmilham.pageturner.databinding.ActivitySearchBinding
import com.jmilham.pageturner.helper.screen.KeyboardHelper.openKeyboard

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this).get(SearchViewModel::class.java)

        binding.search.requestFocus()
        // gain focus for search bar and open soft keyboard
        openKeyboard(binding.search)
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