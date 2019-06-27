package com.denisyk.keytotechtest.ui.comment_list

import com.denisyk.keytotechtest.service.usecase.CommentsUseCase
import com.denisyk.keytotechtest.utils.KEY_LIMIT

class CommentsListPresenter(private val view: CommentsListContract.View,
                            private val useCase: CommentsUseCase) : CommentsListContract.Presenter {

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun fetchComments(start: Int, end: Int, isFirst: Boolean) {
        var _start = start
        if(start > 0)
            _start = start - 1
        useCase.fetchComments(_start, KEY_LIMIT, end)
            .doOnSubscribe { view.showProgress() }
            .doOnSuccess {
                view.hideProgress()
                if(it.isSuccess()) {
                    val data = it.data
                    if(data.isNullOrEmpty()) {
                        view.showEmptyList()
                    } else {
                        if(isFirst) {
                            view.setComments(data)
                        } else {
                            view.addComments(data)
                        }
                    }
                } else {
                }
            }.subscribe()
    }

}