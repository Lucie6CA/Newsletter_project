package com.example.newsletter.models

data class Article(
    val id: String,
    val name: String,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    var favorite: Int =0

)