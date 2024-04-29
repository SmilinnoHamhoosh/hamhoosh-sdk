package com.smilinno.smilinnolibrary.network.provider

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.smilinno.smilinnolibrary.callback.ApiStateBotsListener
import com.smilinno.smilinnolibrary.callback.ApiStateChatListener
import com.smilinno.smilinnolibrary.callback.ApiStateLoginListener
import com.smilinno.smilinnolibrary.model.bots.ResponseBots
import com.smilinno.smilinnolibrary.model.chat.RequestChat
import com.smilinno.smilinnolibrary.model.chat.ResponseChat
import com.smilinno.smilinnolibrary.model.login.ResponseLogin
import com.smilinno.smilinnolibrary.network.apiservices.ApiServices
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal object ApiProvider {

    var token: String? = null
    var apiStateLoginListener: ApiStateLoginListener? = null
    var apiStateChatListener: ApiStateChatListener? = null
    var apiStateBotsListener: ApiStateBotsListener? = null
    private var base_url: String = "https://demo.hamhoush.ir/"
    private val gson: Gson get() = GsonBuilder().create()

    private fun tokenInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
                .header("Authorization", "Bearer $token")
                .header("Content-Type", "application/json")
            val actualRequest = request.build()
            it.proceed(actualRequest)
        }
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(tokenInterceptor())
            .retryOnConnectionFailure(true)
            .build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(getOkHttpClient())
            .build()
    }

    private fun <S> createServiceWithToken(serviceClass: Class<S>): S {
        return retrofit.create(serviceClass)
    }


    fun callLoginApi(username: String, password: String) {
        val call: Call<ResponseLogin> =
            createServiceWithToken(ApiServices::class.java).login(username, password)
        call.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(
                call: Call<ResponseLogin>,
                response: Response<ResponseLogin>
            ) {
                apiStateLoginListener?.onResponse(response)
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                apiStateLoginListener?.onFailure(t)
            }
        })
    }

    fun callChatApi(botId : String,body: RequestChat) {
        val call: Call<ResponseChat> =
            createServiceWithToken(ApiServices::class.java).chat(botId,body)
        call.enqueue(object : Callback<ResponseChat> {
            override fun onResponse(
                call: Call<ResponseChat>,
                response: Response<ResponseChat>
            ) {
                apiStateChatListener?.onResponse(response)
            }

            override fun onFailure(call: Call<ResponseChat>, t: Throwable) {
                apiStateChatListener?.onFailure(t)
            }
        })
    }

    fun callBotsApi() {
        val call: Call<ResponseBots> =
            createServiceWithToken(ApiServices::class.java).bots()
        call.enqueue(object : Callback<ResponseBots> {
            override fun onResponse(
                call: Call<ResponseBots>,
                response: Response<ResponseBots>
            ) {
                apiStateBotsListener?.onResponse(response)
            }

            override fun onFailure(call: Call<ResponseBots>, t: Throwable) {
                apiStateBotsListener?.onFailure(t)
            }
        })
    }

}