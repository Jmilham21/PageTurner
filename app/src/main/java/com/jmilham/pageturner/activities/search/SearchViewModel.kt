package com.jmilham.pageturner.activities.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jmilham.pageturner.api.SearchRepo
import com.jmilham.pageturner.models.BookModel
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException


class SearchViewModel(val app: Application) : AndroidViewModel(app) {

    private val searchRep = SearchRepo(app)
    var liveBookModel: MutableLiveData<BookModel?> = MutableLiveData()


    fun searchBooks(searchText: String) {
        searchRep.querySearch(searchText).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Search", "Query Search Failure")
                liveBookModel.postValue(null)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    try {
                        val bodyResponse = response.body?.string()
                        val model = Gson().fromJson<BookModel>(
                            bodyResponse.toString(),
                            object : TypeToken<BookModel>() {}.type
                        )
                        liveBookModel.postValue(model)
                    } catch (exception: Exception) {
                        Log.e("Search", "Failed to parse")
                        liveBookModel.postValue(null)
                    }

                } else {
                    Log.e("Search", "Search failed successfully")
                    liveBookModel.postValue(BookModel())
                }
            }
        })
    }

}