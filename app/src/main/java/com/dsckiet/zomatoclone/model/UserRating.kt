package com.dsckiet.zomatoclone.model


import com.squareup.moshi.Json

data class UserRating(
    @Json(name = "aggregate_rating")
    val aggregateRating: String,
    @Json(name = "rating_color")
    val ratingColor: String,
    @Json(name = "rating_obj")
    val ratingObj: RatingObj,
    @Json(name = "rating_text")
    val ratingText: String,
    val votes: String
)