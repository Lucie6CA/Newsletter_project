package com.example.newsletter.data.service

import com.example.newsletter.data.Article

interface ArticleService {

    fun getArticles():List<Article>

}