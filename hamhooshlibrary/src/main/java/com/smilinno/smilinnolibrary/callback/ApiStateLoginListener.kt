package com.smilinno.smilinnolibrary.callback

import com.smilinno.smilinnolibrary.model.login.ResponseLogin
import retrofit2.Response

/**
 * An interface that defines the methods that an assistant listener must implement.
 */
interface ApiStateLoginListener {
    fun onResponse(response: Response<ResponseLogin>)
    fun onFailure(e : Throwable)
}
