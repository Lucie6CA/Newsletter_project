package com.example.newsletter.models

import com.google.gson.annotations.SerializedName

data class EditeursResponse(
    val status: String,
    @SerializedName("sources")
    val editeurs: List<Editeurs>
)