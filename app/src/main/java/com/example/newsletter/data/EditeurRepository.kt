package com.example.newsletter.data

import com.example.newsletter.data.service.EditeurOnlineService
import com.example.newsletter.models.EditeursResponse

class EditeurRepository {
    private val apiService: EditeurOnlineService

    init {
        apiService = EditeurOnlineService()
    }

    fun getEditeur(): EditeursResponse = apiService.getEditor()


    companion object {
        private var instance: EditeurRepository? = null
        fun getInstance(): EditeurRepository {
            if (instance == null) {
                instance = EditeurRepository()
            }
            return instance!!
        }
    }
}