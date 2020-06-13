package com.dsckiet.zomatoclone.model


import com.squareup.moshi.Json

data class R(
    @Json(name = "has_menu_status")
    val hasMenuStatus: HasMenuStatus,
    @Json(name = "is_grocery_store")
    val isGroceryStore: Boolean,
    @Json(name = "res_id")
    val resId: Int
)