package com.denisyk.keytotechtest.ui.comment_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.denisyk.keytotechtest.R
import com.denisyk.keytotechtest.service.model.Comment
import kotlinx.android.synthetic.main.item_view_holder_comment.view.*

class CommentsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_PROGRESS = 0
    private val VIEW_TYPE_ITEM = 1

    private var items = ArrayList<Comment?>()

    fun setComments(items: List<Comment?>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addComments(items: List<Comment?>) {
        this.items.addAll(items)
        notifyItemRangeInserted(this.items.size - items.size, this.items.size)
    }

    fun addProgressItem() {
        items.add(null)
        notifyItemInserted(items.size - 1)
    }

    fun removeProgressItem() {
        items.removeAt(items.size - 1)
        notifyItemRemoved(items.size)
    }

    override fun getItemViewType(position: Int): Int {
        return if(items[position] == null) {
            VIEW_TYPE_PROGRESS
        } else {
            VIEW_TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_ITEM) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_holder_comment, parent, false)

            CommentViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view_holder_progress, parent, false)

            ProgressViewHolder(view)
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is CommentViewHolder) {
            items[position]?.let {
                holder.bindHolder(it)
            }
        }
    }

}

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindHolder(item: Comment) {
        itemView.name.text = item.name
        itemView.email.text = "Email: ${item.email}"
        itemView.comment.text = item.body
    }

}

class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)