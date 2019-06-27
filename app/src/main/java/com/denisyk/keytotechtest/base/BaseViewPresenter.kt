package com.denisyk.keytotechtest.base

interface BaseViewPresenter<T> : BaseView {
    var presenter: T
}