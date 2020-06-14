package com.dsckiet.zomatoclone.model


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import org.checkerframework.checker.nullness.qual.Raw
import java.io.Serializable

@Parcelize
data class Restaurant(
    @Json(name = "all_reviews")
    val allReviews: AllReviews,
    @Json(name = "all_reviews_count")
    val allReviewsCount: Int,
    val apikey: String,
    @Json(name = "average_cost_for_two")
    val averageCostForTwo: Int,
    @Json(name = "book_again_url")
    val bookAgainUrl: String,
    @Json(name = "book_form_web_view_url")
    val bookFormWebViewUrl: String,
    val cuisines: String,
    val currency: String,
    val deeplink: String,
    val establishment: List<String>,
    @Json(name = "events_url")
    val eventsUrl: String,
    @Json(name = "featured_image")
    val featuredImage: String,
    @Json(name = "has_online_delivery")
    val hasOnlineDelivery: Int,
    @Json(name = "has_table_booking")
    val hasTableBooking: Int,
    val highlights: List<String>,
    val id: String,
    @Json(name = "include_bogo_offers")
    val includeBogoOffers: Boolean,
    @Json(name = "is_book_form_web_view")
    val isBookFormWebView: Int,
    @Json(name = "is_delivering_now")
    val isDeliveringNow: Int,
    @Json(name = "is_table_reservation_supported")
    val isTableReservationSupported: Int,
    @Json(name = "is_zomato_book_res")
    val isZomatoBookRes: Int,
    val location: Location,
    @Json(name = "menu_url")
    val menuUrl: String,
    @Json(name = "mezzo_provider")
    val mezzoProvider: String,
    val name: String,
    val offers: @RawValue List<Any>,
    @Json(name = "opentable_support")
    val opentableSupport: Int,
    @Json(name = "order_deeplink")
    val orderDeeplink: String,
    @Json(name = "order_url")
    val orderUrl: String,
    @Json(name = "phone_numbers")
    val phoneNumbers: String,
    @Json(name = "photo_count")
    val photoCount: Int,
    @Json(name = "photos_url")
    val photosUrl: String,
    @Json(name = "price_range")
    val priceRange: Int,
    @Json(name = "R")
    val r: R,
    @Json(name = "store_type")
    val storeType: String,
    @Json(name = "switch_to_order_menu")
    val switchToOrderMenu: Int,
    val thumb: String,
    val timings: String,
    val url: String,
    @Json(name = "user_rating")
    val userRating: UserRating
):Parcelable