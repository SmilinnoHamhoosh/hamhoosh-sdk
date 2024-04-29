package com.smilinno.smilinnolibrary.network.apiservices

import com.smilinno.smilinnolibrary.model.bots.ResponseBots
import com.smilinno.smilinnolibrary.model.chat.RequestChat
import com.smilinno.smilinnolibrary.model.chat.ResponseChat
import com.smilinno.smilinnolibrary.model.login.ResponseLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface ApiServices {

    @FormUrlEncoded
    @POST("api/api/auth/login")
     fun login(@Field("username") username: String, @Field("password") password: String): Call<ResponseLogin>

    @POST("chat/api/bot/{botId}/chat")
     fun chat(@Path("botId") botId: String,@Body requestChat: RequestChat?): Call<ResponseChat>

    @GET("api/api/account/bot")
     fun bots(): Call<ResponseBots>
}