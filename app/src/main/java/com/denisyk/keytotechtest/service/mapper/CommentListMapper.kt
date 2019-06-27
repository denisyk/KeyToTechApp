package com.denisyk.keytotechtest.service.mapper

import com.denisyk.keytotechtest.service.model.Comment
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.Function

class CommentListMapper(private val maxValue: Int) : Function<List<Comment>, Single<List<Comment>>> {

    override fun apply(items: List<Comment>): Single<List<Comment>> {
        return Observable.fromIterable(items)
            .filter { it.id <= maxValue }
            .toList()
    }

}