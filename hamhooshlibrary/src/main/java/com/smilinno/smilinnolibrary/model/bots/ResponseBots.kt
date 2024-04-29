package com.smilinno.smilinnolibrary.model.bots


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResponseBots(
    @SerializedName("data")
    val `data`: Data?,
    @SerializedName("error_code")
    val errorCode: Int?, // 0
    @SerializedName("message")
    val message: Any?, // null
    @SerializedName("success")
    val success: Boolean? // true
) {
    @Keep
    data class Data(
        @SerializedName("account_owned")
        val accountOwned: List<AccountOwned?>?,
        @SerializedName("shared")
        val shared: List<Any?>?
    ) {
        @Keep
        data class AccountOwned(
            @SerializedName("account")
            val account: Int?, // 4
            @SerializedName("_id")
            val id: String?, // 65df090f2891bf64b970b9ca
            @SerializedName("metadata")
            val metadata: Metadata?,
            @SerializedName("name")
            val name: String?, // Hi-Hello
            @SerializedName("role")
            val role: String?, // owner
            @SerializedName("timestamp")
            val timestamp: String?, // 2024-02-28T10:21:03.025000
            @SerializedName("user")
            val user: String? // hamhoush@gmail.com
        ) {
            @Keep
            data class Metadata(
                @SerializedName("language")
                val language: String? // en
            )
        }
    }
}