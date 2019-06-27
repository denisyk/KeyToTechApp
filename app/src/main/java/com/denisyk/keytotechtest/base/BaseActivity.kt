package com.denisyk.keytotechtest.base

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

/**
 * All other activities must extend from this BaseActivity
 */
abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }

    override fun showProgress() {

    }

    override fun hideProgress() {
    }

}