package com.dsckiet.zomatoclone.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class BgColor(
    val tint: String,
    val type: String
):Parcelable