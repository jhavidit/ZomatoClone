package com.dsckiet.zomatoclone.model


import com.squareup.moshi.Json
import java.io.Serializable

data class AllReviews(
    val reviews: List<Review>
):Serializable