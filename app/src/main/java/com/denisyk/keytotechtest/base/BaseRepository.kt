package com.denisyk.keytotechtest.base

import com.denisyk.keytotechtest.service.RestClient

abstract class BaseRepository {
    val restClient = RestClient.getInstance().getService()
}