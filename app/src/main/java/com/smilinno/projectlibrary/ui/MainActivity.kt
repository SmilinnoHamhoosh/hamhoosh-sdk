package com.smilinno.projectlibrary.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.smilinno.projectlibrary.databinding.ActivityMainBinding
import com.smilinno.smilinnolibrary.HamhooshLibrary
import com.smilinno.smilinnolibrary.callback.ApiStateBotsListener
import com.smilinno.smilinnolibrary.callback.ApiStateChatListener
import com.smilinno.smilinnolibrary.callback.ApiStateLoginListener
import com.smilinno.smilinnolibrary.model.bots.ResponseBots
import com.smilinno.smilinnolibrary.model.chat.ResponseChat
import com.smilinno.smilinnolibrary.model.login.ResponseLogin
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var hamhooshLibrary : HamhooshLibrary? = null
    private var token = ""
    private var username = ""
    private var password = ""
    private var botId = ""
    private var text = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        hamhooshLibrary = HamhooshLibrary.Builder()
            .setToken(token)
            .build()

        //login
        binding.login.setOnClickListener {
            lifecycleScope.launch {
                hamhooshLibrary?.login(username,password,object : ApiStateLoginListener{
                    override fun onResponse(response: Response<ResponseLogin>) {
                        binding.txtLogin.text = response.body().toString()
                    }
                    override fun onFailure(e: Throwable) {
                        binding.txtLogin.text = e.toString()
                    }
                })
            }

        }
        //chat
        binding.chat.setOnClickListener {
            lifecycleScope.launch {
                hamhooshLibrary?.sendChat(botId,text,object : ApiStateChatListener{
                    override fun onResponse(response: Response<ResponseChat>) {
                        binding.txtLogin.text = response.body().toString()
                    }
                    override fun onFailure(e: Throwable) {
                        binding.txtLogin.text = e.toString()
                    }
                })
            }
        }
        //bots
        binding.bots.setOnClickListener {
            lifecycleScope.launch {
                hamhooshLibrary?.getBots(object : ApiStateBotsListener{
                    override fun onResponse(response: Response<ResponseBots>) {
                        val string = StringBuilder()
                        response.body()?.data?.accountOwned?.forEach {
                           string.append(" - name: ${it?.name}\n")
                        }
                        binding.txtLogin.text = string.toString()
                    }
                    override fun onFailure(e: Throwable) {
                        binding.txtLogin.text = e.toString()
                    }
                })
            }
        }
    }
}