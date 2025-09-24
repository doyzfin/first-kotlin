package com.example.myfirstapp

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: Int
)
