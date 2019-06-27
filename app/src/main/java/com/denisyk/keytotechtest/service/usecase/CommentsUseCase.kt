package com.denisyk.keytotechtest.service.usecase

import com.denisyk.keytotechtest.service.repository.CommentsRepository

class CommentsUseCase(private val repository: CommentsRepository) {

    fun fetchComments(start: Int, limit: Int, end: Int) = repository.fetchComments(start, limit, end)

}