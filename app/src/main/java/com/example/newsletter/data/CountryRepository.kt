package com.example.newsletter.data

import com.example.newsletter.data.service.CountryOnlineService
import com.example.newsletter.models.ArticleResponse

class CountryRepository {
    private val apiService: CountryOnlineService

    init {
        apiService = CountryOnlineService()
    }

    fun getArticles(subject: String): ArticleResponse = apiService.getArticles(subject)


    companion object {
        private var instance: CountryRepository? = null
        fun getInstance(): CountryRepository {
            if (instance == null) {
                instance = CountryRepository()
            }
            return instance!!
        }
    }
}