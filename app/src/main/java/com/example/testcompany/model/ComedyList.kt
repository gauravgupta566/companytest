package com.example.testcompany.model

import com.google.gson.annotations.SerializedName

data class ComedyList(
    @SerializedName("page")
    val page: Page
)