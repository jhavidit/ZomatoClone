package com.dsckiet.zomatoclone.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RatingObj(
    @Json(name = "bg_color")
    val bgColor: BgColor,
    val title: Title
): Parcelable