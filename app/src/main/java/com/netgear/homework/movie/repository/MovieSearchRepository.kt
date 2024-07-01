package com.netgear.homework.movie.repository

import com.netgear.homework.movie.repository.data.MovieSearchResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class MovieRepository : MovieApiDataSource<MovieSearchResponse>(){

    suspend fun searchMovie(text: String): MovieSearchResponse? {
        // Call API
        return suspendCancellableCoroutine { continuation ->
            val call = retrofit.create(ApiService::class.java).searchMovie(
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI2Yzc4OTZjYjQ5ZDc4NTQxMzk2NzhmZDRlOTRkYjdkZCIsIm5iZiI6MTcxOTc4MzQ3MS44Njg4ODgsInN1YiI6IjY2ODFjYzVkYTZhMmJkMzFkYmUyNjhlMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.9uJ3oagRSknIVByw_pYjav0z0vkjCq56JfQbCQLzuB0",
                text
            )

            call.enqueue(object : retrofit2.Callback<MovieSearchResponse> {
                override fun onResponse(call: Call<MovieSearchResponse>, response: retrofit2.Response<MovieSearchResponse>) {
                    if (response.isSuccessful) {
                        continuation.resume(response.body())
                    } else {
                        continuation.resumeWithException(Exception("API request failed with code ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<MovieSearchResponse>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })

            // Allow cancellation of the coroutine to cancel the Retrofit call
            continuation.invokeOnCancellation {
                call.cancel()
            }
        }
    }

}
