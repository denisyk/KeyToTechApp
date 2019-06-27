package com.denisyk.keytotechtest.service.repository

import com.denisyk.keytotechtest.base.BaseRepository
import com.denisyk.keytotechtest.service.model.Comment
import io.reactivex.Single
import com.denisyk.keytotechtest.utils.applySchedulers
import com.denisyk.keytotechtest.base.Result
import com.denisyk.keytotechtest.service.mapper.CommentListMapper

class CommentsRepositoryImpl : BaseRepository(), CommentsRepository {

    override fun fetchComments(start: Int, limit: Int, end: Int): Single<Result<List<Comment>>> {
        return restClient.fetchComments(start, limit)
            .applySchedulers()
            .flatMap(CommentListMapper(end))
            .map { Result.fromData(it) }
            .onErrorReturn { Result.fromError(it)}
    }

}