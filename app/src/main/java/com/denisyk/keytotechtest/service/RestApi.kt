package com.denisyk.keytotechtest.service

import com.denisyk.keytotechtest.service.model.Comment
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {

    @GET("/comments")
    fun fetchComments(
        @Query("_start") start: Int,
        @Query("_limit") limit: Int
    ) : Single<List<Comment>>
}