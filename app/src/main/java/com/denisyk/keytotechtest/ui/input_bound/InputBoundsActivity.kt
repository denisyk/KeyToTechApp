package com.denisyk.keytotechtest.ui.input_bound

import android.os.Bundle
import com.denisyk.keytotechtest.R
import com.denisyk.keytotechtest.base.BaseActivity
import com.denisyk.keytotechtest.ui.comment_list.CommentsListActivity
import com.jakewharton.rxbinding2.view.RxView
import kotlinx.android.synthetic.main.activity_bounds.*
import java.util.concurrent.TimeUnit

class InputBoundsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bounds)

        RxView.clicks(btnSubmit)
            .throttleFirst(200, TimeUnit.MILLISECONDS)
            .doOnNext { onSubmitBtnClicked() }
            .subscribe()
    }

    private fun onSubmitBtnClicked() {
        val minText = etMinBound.text.toString().trim()
        val maxText = etMaxBound.text.toString().trim()

        if(minText.isEmpty()) {
            return
        }

        if(maxText.isEmpty()) {
            return
        }

        val min = etMinBound.text.toString().trim().toInt()
        val max = etMaxBound.text.toString().trim().toInt()

        CommentsListActivity.launchActivity(this, min, max)
    }

}