package com.netgear.homework.movie.repository

import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

open class MovieApiDataSource<T> {

    // Create a logging interceptor to log network requests and responses

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Create an OkHttpClient with the logging interceptor

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    // Create a Retrofit instance
    internal val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL) // Replace with your API base URL
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    internal suspend fun requestMovieDataSource(apiRequest: () -> Call<T>): Result<T?> {
        // Call API
        return suspendCancellableCoroutine { continuation ->
            apiRequest().run {
                enqueue(object : retrofit2.Callback<T> {
                    override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
                        if (response.isSuccessful) {
                            continuation.resume(Result.success(response.body()))
                        } else {
                            //continuation.resumeWithException(Exception("API request failed with code ${response.code()}"))
                            continuation.resume(Result.failure(Exception("API request failed with code ${response.code()} and message: ${response.message()}")))
                        }
                    }

                    override fun onFailure(call: Call<T>, t: Throwable) {
                        //continuation.resumeWithException(t)
                        continuation.resume(Result.failure(t))
                    }
                })

                // Allow cancellation of the coroutine to cancel the Retrofit call
                continuation.invokeOnCancellation {
                    cancel()
                }
            }
        }
    }

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2Yzc4OTZjYjQ5ZDc4NTQxMzk2NzhmZDRlOTRkYjdkZCIsIm5iZiI6MTcxOTc4MzQ3MS44Njg4ODgsInN1YiI6IjY2ODFjYzVkYTZhMmJkMzFkYmUyNjhlMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.9uJ3oagRSknIVByw_pYjav0z0vkjCq56JfQbCQLzuB0"

    }
}