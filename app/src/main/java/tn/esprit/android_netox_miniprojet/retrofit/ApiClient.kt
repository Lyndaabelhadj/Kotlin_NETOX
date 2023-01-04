package tn.esprit.android_netox_miniprojet.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient {
    companion object {

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        var client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(interceptor).build()
      //val URL = "http://192.168.234.219:9091/"

        val URL = "http://10.0.2.2:9091/"
        var retrofit: Retrofit? = null
        fun getApiClient(): Retrofit? {
            if (retrofit == null) {
                retrofit =
                    Retrofit.Builder().baseUrl(URL).client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
    }

}