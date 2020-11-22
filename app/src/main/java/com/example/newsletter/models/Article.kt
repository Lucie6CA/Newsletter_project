package com.example.newsletter.models

import java.util.*

data class Article(
    val id: String,
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: Date,
    val content: String,
    var favorite: Int =0

)