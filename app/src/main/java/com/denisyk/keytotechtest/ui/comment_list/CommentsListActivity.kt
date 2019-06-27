package com.denisyk.keytotechtest.ui.comment_list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denisyk.keytotechtest.R
import com.denisyk.keytotechtest.base.BaseActivity
import com.denisyk.keytotechtest.base.EndlessScrollListener
import com.denisyk.keytotechtest.service.model.Comment
import com.denisyk.keytotechtest.service.repository.CommentsRepositoryImpl
import com.denisyk.keytotechtest.service.usecase.CommentsUseCase
import com.denisyk.keytotechtest.utils.bindView

class CommentsListActivity : BaseActivity(), CommentsListContract.View, EndlessScrollListener.OnLoadMoreListener {

    override lateinit var presenter: CommentsListContract.Presenter
    private val recyclerView by bindView<RecyclerView>(R.id.recyclerView)
    private val toolbar by bindView<Toolbar>(R.id.toolbar)

    private lateinit var scrollListener: EndlessScrollListener
    private var adapter: CommentsAdapter? = null

    private var minBound = 0
    private var maxBound = 0

    companion object {
        private val KEY_ARGS_MIN_BOUND = "key_args_min_bound"
        private val KEY_ARGS_MAX_BOUND = "key_args_max_bound"

        fun launchActivity(context: Context, min: Int, max: Int) {
            val intent = Intent(context, CommentsListActivity::class.java)
            intent.putExtra(KEY_ARGS_MIN_BOUND, min)
            intent.putExtra(KEY_ARGS_MAX_BOUND, max)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comments_list)

        minBound = intent?.getIntExtra(KEY_ARGS_MIN_BOUND, 0) ?: 0
        maxBound = intent?.getIntExtra(KEY_ARGS_MAX_BOUND, 0) ?: 0

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        presenter = CommentsListPresenter(this, CommentsUseCase(CommentsRepositoryImpl()))
        presenter.fetchComments(minBound, maxBound)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    override fun showEmptyList() {
    }

    override fun setComments(comments: List<Comment>) {
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        scrollListener = EndlessScrollListener(layoutManager, this)
        recyclerView.addOnScrollListener(scrollListener)
        adapter = CommentsAdapter()
        recyclerView.adapter = adapter
        adapter?.setComments(comments)
        scrollListener.setLoaded()
    }

    override fun addComments(comments: List<Comment>) {
        adapter?.removeProgressItem()
        adapter?.addComments(comments)
        scrollListener.setLoaded()
    }

    override fun onLoadMore() {
        adapter?.let {
            it.addProgressItem()
            presenter.fetchComments(minBound + it.itemCount, maxBound, false)
        }
    }

}