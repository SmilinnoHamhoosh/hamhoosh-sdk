package com.smilinno.smilinnolibrary

import com.smilinno.smilinnolibrary.callback.ApiStateBotsListener
import com.smilinno.smilinnolibrary.callback.ApiStateChatListener
import com.smilinno.smilinnolibrary.callback.ApiStateLoginListener
import com.smilinno.smilinnolibrary.model.chat.RequestChat
import com.smilinno.smilinnolibrary.network.provider.ApiProvider

class HamhooshLibrary private constructor(private val builder: Builder) {

    var token: String?

    fun login(user : String, pass : String, apiStateLoginListener: ApiStateLoginListener){
        ApiProvider.apiStateLoginListener = apiStateLoginListener
        ApiProvider.callLoginApi(user,pass)
    }

    fun sendChat(botId : String,text : String, apiStateChatListener: ApiStateChatListener){
        ApiProvider.apiStateChatListener = apiStateChatListener
        val body = RequestChat(text)
        ApiProvider.callChatApi(botId, body)
    }

    fun getBots(apiStateBotsListener: ApiStateBotsListener){
        ApiProvider.apiStateBotsListener = apiStateBotsListener
        ApiProvider.callBotsApi()
    }

    class Builder() {

        private var token: String? = null

        fun setToken(token: String) = apply { this.token = token }

        fun getToken() = token

        fun build(): HamhooshLibrary {
            ApiProvider.token = token
            return HamhooshLibrary(
                builder = this@Builder,
            )
        }
    }

    init {
        token = builder.getToken()
    }


}