package com.dsckiet.zomatoclone.model


import com.squareup.moshi.Json
import java.io.Serializable

data class BgColor(
    val tint: String,
    val type: String
):Serializable