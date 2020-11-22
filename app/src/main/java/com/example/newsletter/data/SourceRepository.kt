package com.example.newsletter.data

import com.example.newsletter.data.service.SourceOnlineService
import com.example.newsletter.models.ArticleResponse

class SourceRepository {
    private val apiService: SourceOnlineService

    init {
        apiService = SourceOnlineService()
    }

    fun getArticles(subject :String): ArticleResponse = apiService.getArticles(subject)


    companion object {
        private var instance: SourceRepository? = null
        fun getInstance(): SourceRepository {
            if (instance == null) {
                instance = SourceRepository()
            }
            return instance!!
        }
    }
}