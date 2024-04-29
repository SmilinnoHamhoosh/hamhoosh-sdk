package com.smilinno.smilinnolibrary.model.chat


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResponseChat(
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
        @SerializedName("agent_handoff")
        val agentHandoff: AgentHandoff?,
        @SerializedName("events")
        val events: Any?, // null
        @SerializedName("response")
        val response: List<Response?>?,
        @SerializedName("tabname")
        val tabname: String? // default
    ) {
        @Keep
        data class AgentHandoff(
            @SerializedName("additional_properties")
            val additionalProperties: Any?, // null
            @SerializedName("initiate")
            val initiate: Boolean?, // false
            @SerializedName("type")
            val type: Any? // null
        )

        @Keep
        data class Response(
            @SerializedName("recipient_id")
            val recipientId: String?, // hamhoush@gmail.com
            @SerializedName("text")
            val text: String? // لطفا شهر خود را وارد نمایید
        )
    }
}