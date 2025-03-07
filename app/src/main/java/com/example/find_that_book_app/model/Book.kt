package com.example.find_that_book_app.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://www.googleapis.com/books/v1/"

interface BooksApi {
    @GET("volumes")
    suspend fun getPopularBooks(
        @Query("q") query: String = "''",
        @Query("orderBy") orderBy: String = "relevance",
        @Query("maxResults") maxResults: Int = 20,
        @Query("key") apiKey: String
    ): GoogleBooksResponse

    @GET("volumes")
    suspend fun getSearchedBook(
        @Query("q") query: String,
        @Query("orderBy") orderBy: String = "relevance",
        @Query("maxResults") maxResults: Int = 20,
        @Query("key") apiKey: String
    ): GoogleBooksResponse

    companion object {
        var booksService: BooksApi? = null

        fun getInstance(): BooksApi {
            if (booksService == null) {
                booksService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(BooksApi::class.java)
            }
            return booksService!!
        }
    }
}

