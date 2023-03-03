package com.example.cidpro.http

import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class OkHttp {
    fun get(url: String, callback: Callback) {
        Thread {
            val client: OkHttpClient = OkHttpClient.Builder().build()
            val request: okhttp3.Request = okhttp3.Request.Builder()
                    .url(url)
                    .build()
            client.newCall(request).enqueue(callback)
        }.start()
    }

    fun post(url: String, json: String, callback: Callback) {
        Thread {
            val requestBody: RequestBody =
                    json.toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
            val client: OkHttpClient = OkHttpClient.Builder().build()
            val request: okhttp3.Request = okhttp3.Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build()
            client.newCall(request).enqueue(callback)
        }.start()
    }
}
