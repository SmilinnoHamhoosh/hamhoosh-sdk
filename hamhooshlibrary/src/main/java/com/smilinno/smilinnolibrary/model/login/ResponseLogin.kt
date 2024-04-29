package com.smilinno.smilinnolibrary.model.login


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResponseLogin(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("error_code")
    val errorCode: Int?, // 0
    @SerializedName("message")
    val message: String?, // احراز هویت کاربر با موفقیت انجام شد!
    @SerializedName("success")
    val success: Boolean? // true
) {
    @Keep
    data class Data(
        @SerializedName("access_token")
        val accessToken: String?, // eyJhztSbim8A-gducHU
        @SerializedName("access_token_expiry")
        val accessTokenExpiry: Double?, // 1714907700.0
        @SerializedName("refresh_token")
        val refreshToken: String?, // eyX35rUqjv8hG9kl8
        @SerializedName("refresh_token_expiry")
        val refreshTokenExpiry: Double?, // 1714994100.0
        @SerializedName("token_type")
        val tokenType: String? // bearer
    )
}