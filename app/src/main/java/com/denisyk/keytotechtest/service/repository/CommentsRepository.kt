package com.denisyk.keytotechtest.service.repository

import com.denisyk.keytotechtest.base.Result
import com.denisyk.keytotechtest.service.model.Comment
import io.reactivex.Single

interface CommentsRepository {

    fun fetchComments(start: Int, limit: Int, end: Int): Single<Result<List<Comment>>>

}