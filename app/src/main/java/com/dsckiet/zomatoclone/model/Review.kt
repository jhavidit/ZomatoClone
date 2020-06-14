package com.dsckiet.zomatoclone.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Review(
    val review: @RawValue List<Any>
): Parcelable