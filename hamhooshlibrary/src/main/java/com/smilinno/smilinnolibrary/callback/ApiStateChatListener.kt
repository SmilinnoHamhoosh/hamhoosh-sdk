package com.smilinno.smilinnolibrary.callback

import com.smilinno.smilinnolibrary.model.chat.ResponseChat
import retrofit2.Response

/**
 * An interface that defines the methods that an assistant listener must implement.
 */
interface ApiStateChatListener {
    fun onResponse(response: Response<ResponseChat>)
    fun onFailure(e : Throwable)
}
