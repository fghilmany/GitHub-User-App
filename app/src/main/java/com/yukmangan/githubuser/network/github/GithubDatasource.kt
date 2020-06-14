package com.yukmangan.githubuser.network.github

import com.google.gson.GsonBuilder
import com.yukmangan.githubuser.BuildConfig
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface GithubDatasource {

    @GET("search/users")
    fun getSearchData(
        @Header("Authorization")token:String = "",
        @Query("q")q:String? = ""
    ):Observable<Search>

    @GET("users/{username}")
    fun getDetail(
        @Header("Authorization")token:String = "",
        @Path("username")username:String? = ""
    ):Observable<DetailUser>

    @GET("users/{username}/followers")
    fun getFollower(
        @Header("Authorization")token:String = "",
        @Path("username")username:String? = ""
    ):Observable<Followers>

    @GET("users/{username}/following")
    fun getFollowing(
        @Header("Authorization")token:String = "",
        @Path("username")username:String? = ""
    ):Observable<Following>

    companion object{
        fun create():GithubDatasource{

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val interceptor = HttpLoggingInterceptor()
            interceptor.apply { interceptor.level = HttpLoggingInterceptor.Level.BODY }
            val client = OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(BuildConfig.GITHUB_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(GithubDatasource::class.java)

        }
    }
}