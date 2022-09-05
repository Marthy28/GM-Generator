package com.example.dndcharactergenerator.data.api

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Dans ce fichier on crée une requête API.
 * On récupère l'adresse de l'api, et la requête
 * on va ensuite créer la requête en utilisant retrofit
 * on ajoute un header à retrofit grace à ok http client
 */

interface ApiInterface {
    //@GET("games")

    //get games retourne un objet résultat
   // fun getGames(): Call<Resultat>

    companion object {
        fun create(userAgent: String): ApiInterface {
            //création de l'instance de retrofit, on met l'adresse de l'api

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(chain.request()
                        .newBuilder()
                        .header("User-Agent", userAgent)
                        .build())
                }.build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://rawg.io/api/") //à rendre modifiable
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }
}