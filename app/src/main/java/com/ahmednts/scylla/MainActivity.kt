package com.ahmednts.scylla

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val adapter = RecyclerViewAdapter()
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter
    recyclerView.setHasFixedSize(false)

    val items = mutableListOf<ICell>()
    items.add((UserCell()))
    items.add((PostCell()))
    items.add((PostCell()))
    items.add((PostCell()))
    items.add((UserCell()))
    items.add((PostCell()))

    adapter.submitList(items)
  }
}

abstract class ICell {
  @get:LayoutRes
  abstract val type: Int
}

class UserCell : ICell() {
  override val type: Int
    get() = R.layout.item_user_cell
}

class PostCell : ICell() {
  override val type: Int
    get() = R.layout.item_post_cell
}

abstract class ICellViewHolder<DATA>(view: View) : RecyclerView.ViewHolder(view) {
  abstract fun bind(item: DATA)
}

class UserCellViewHolder(view: View) : ICellViewHolder<ICell>(view) {
  override fun bind(item: ICell) {

  }
}

class PostCellViewHolder(view: View) : ICellViewHolder<ICell>(view) {
  override fun bind(item: ICell) {

  }
}

class RecyclerViewAdapter : ListAdapter<ICell, ICellViewHolder<ICell>>(object : DiffUtil.ItemCallback<ICell>() {
  override fun areItemsTheSame(oldItem: ICell, newItem: ICell): Boolean {
    return true
  }

  override fun areContentsTheSame(oldItem: ICell, newItem: ICell): Boolean {
    return true
  }

}) {

  override fun getItemViewType(position: Int): Int {
    return getItem(position).type
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ICellViewHolder<ICell> {
    val inflater = LayoutInflater.from(parent.context)

    return when (viewType) {
      R.layout.item_user_cell -> {
        val view = inflater.inflate(R.layout.item_user_cell, parent, false)
        UserCellViewHolder(view)
      }
      R.layout.item_post_cell -> {
        val view = inflater.inflate(R.layout.item_post_cell, parent, false)
        PostCellViewHolder(view)
      }
      else -> throw NullPointerException("o.O")
    }
  }

  override fun onBindViewHolder(holder: ICellViewHolder<ICell>, position: Int) {
    holder.bind(getItem(position))
  }
}
