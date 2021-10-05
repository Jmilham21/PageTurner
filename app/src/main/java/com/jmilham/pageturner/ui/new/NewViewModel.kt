package com.jmilham.pageturner.ui.new

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This the New Fragment"
    }
    val text: LiveData<String> = _text
}