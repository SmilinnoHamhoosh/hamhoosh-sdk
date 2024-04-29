package com.smilinno.smilinnolibrary.callback

import com.smilinno.smilinnolibrary.model.bots.ResponseBots
import retrofit2.Response

/**
 * An interface that defines the methods that an assistant listener must implement.
 */
interface ApiStateBotsListener {
    fun onResponse(response: Response<ResponseBots>)
    fun onFailure(e : Throwable)
}
