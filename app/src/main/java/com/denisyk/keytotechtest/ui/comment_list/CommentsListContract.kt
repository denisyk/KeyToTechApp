package com.denisyk.keytotechtest.ui.comment_list

import com.denisyk.keytotechtest.base.BasePresenter
import com.denisyk.keytotechtest.base.BaseViewPresenter
import com.denisyk.keytotechtest.service.model.Comment

interface CommentsListContract {

    interface Presenter : BasePresenter {
        fun fetchComments(start: Int, end: Int, isFirst:Boolean = true)
    }

    interface View : BaseViewPresenter<Presenter> {
        fun showEmptyList()
        fun setComments(comments: List<Comment>)
        fun addComments(comments: List<Comment>)
    }
}