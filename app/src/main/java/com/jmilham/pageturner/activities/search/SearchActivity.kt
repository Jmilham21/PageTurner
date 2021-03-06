package com.jmilham.pageturner.activities.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.jmilham.pageturner.databinding.ActivitySearchBinding
import com.jmilham.pageturner.helper.adapter.BookModelAdapter
import com.jmilham.pageturner.helper.screen.KeyboardHelper.hideKeyboard
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

        setupObservers()
        displayKeyboard()

        binding.search.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                (binding.recycler.adapter as? BookModelAdapter)?.clearDataSet()
                hideKeyboard()
                binding.loadingLayout.visibility = View.VISIBLE
                viewModel.searchBooks(textView.text.toString())
            }
            true
        }
    }

    private fun setupObservers() {
        viewModel.liveBookModel.observe(this, {
            binding.loadingLayout.visibility = View.GONE
            when {
                it == null -> {
                    // api or parsing failed
                }
                it.query.isEmpty() -> {
                    // call wasn't successful
                }
                else -> {
                    // data is good and can work with
                    binding.recycler.layoutManager = LinearLayoutManager(this)
                    val decoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
                    binding.recycler.adapter = BookModelAdapter(it.books)
                    binding.recycler.addItemDecoration(decoration)
                }
            }
        })
    }

    /***
     * Helper that shows the keyboard for the Activity.
     *
     * Called from onCreate by design but may be needed on resume?
     */
    private fun displayKeyboard() {
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