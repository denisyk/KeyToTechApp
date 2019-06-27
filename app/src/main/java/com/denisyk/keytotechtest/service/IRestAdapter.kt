package com.denisyk.keytotechtest.service

interface IRestAdapter {

    fun <T> createApi(clazz: Class<T>): T

}