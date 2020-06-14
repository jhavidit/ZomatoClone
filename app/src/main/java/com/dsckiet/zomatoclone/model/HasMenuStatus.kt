package com.dsckiet.zomatoclone.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HasMenuStatus(
    val delivery: Int,
    val takeaway: Int
): Parcelable