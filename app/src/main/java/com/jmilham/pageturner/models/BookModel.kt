package com.jmilham.pageturner.models

import com.google.gson.annotations.SerializedName

class BookModel {
    @SerializedName("numFound")
    var count: Int = -1

    @SerializedName("q")
    var query: String = ""

    @SerializedName("docs")
    var books: ArrayList<Book> = ArrayList()

    data class Book(
        var title: String = "",
        var isbn: ArrayList<String> = ArrayList(),
        @SerializedName("author_key")
        var authorKey: ArrayList<String> = ArrayList(),
        @SerializedName("author_name")
        var authorName: ArrayList<String> = ArrayList(),
        @SerializedName("cover_edition_key")
        var coverKey: String = ""
    )
}