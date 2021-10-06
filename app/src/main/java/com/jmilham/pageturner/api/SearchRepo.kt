package com.jmilham.pageturner.api

import android.content.Context
import com.jmilham.pageturner.R
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Request

class SearchRepo(private val context: Context) {

    private val client = OkHttpClient() // Could use more setup

    /***
     * Build a basic call to do a query search on the API
     *
     * @param queryString raw string to search with
     */
    fun querySearch(queryString: String): Call {
        val request = Request.Builder()
            .url(
                SearchHelper.formatSearchText(
                    context.getString(R.string.base_api_url) + context.getString(
                        R.string.query_search,
                        queryString
                    )
                )
            )
            .build()
        return client.newCall(request)
    }
}

object SearchHelper {
    // Helper to remove empty spaces and replace with +
    fun formatSearchText(unformatted: String): String {
        return unformatted.replace(" ", "+")
    }
}