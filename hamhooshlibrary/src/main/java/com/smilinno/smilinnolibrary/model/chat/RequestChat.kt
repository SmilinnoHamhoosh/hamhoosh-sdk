package com.smilinno.smilinnolibrary.model.chat


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class RequestChat(
    @SerializedName("data")
    val data: String?, // سلام
)