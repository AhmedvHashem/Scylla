package com.ahmednts.scylla

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    val items = mutableListOf<ICellViewModel>()
    items.add(UserViewModel(UserCell()))
    items.add(PostViewModel(PostCell()))
    items.add(PostViewModel(PostCell()))
    items.add(PostViewModel(PostCell()))
    items.add(UserViewModel(UserCell()))
    items.add(PostViewModel(PostCell()))

    adapter.submitList(items)
  }
}

const val USER_CELL = 1000
const val POST_CELL = 2000

interface ICell
class UserCell : ICell
class PostCell : ICell

class UserCellViewHolder(view: View) : RecyclerView.ViewHolder(view)
class PostCellViewHolder(view: View) : RecyclerView.ViewHolder(view)

interface ICellViewModel {
  val type: Int

  fun bind(view: RecyclerView.ViewHolder)
}

class UserViewModel(private val item: UserCell) : ICellViewModel {
  override val type: Int
    get() = USER_CELL

  override fun bind(view: RecyclerView.ViewHolder) {
  }
}

class PostViewModel(private val item: PostCell) : ICellViewModel {
  override val type: Int
    get() = POST_CELL

  override fun bind(view: RecyclerView.ViewHolder) {

  }
}

class RecyclerViewAdapter : ListAdapter<ICellViewModel, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<ICellViewModel>() {
  override fun areItemsTheSame(oldItem: ICellViewModel, newItem: ICellViewModel): Boolean {
    return true
  }

  override fun areContentsTheSame(oldItem: ICellViewModel, newItem: ICellViewModel): Boolean {
    return true
  }

}) {

  override fun getItemViewType(position: Int): Int {
    return getItem(position).type
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflater = LayoutInflater.from(parent.context)

    return when (viewType) {
      USER_CELL -> {
        val view = inflater.inflate(R.layout.item_user_cell, parent, false)
        UserCellViewHolder(view)
      }
      POST_CELL -> {
        val view = inflater.inflate(R.layout.item_post_cell, parent, false)
        PostCellViewHolder(view)
      }
      else -> throw NullPointerException("o.O")
    }
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    getItem(position).bind(holder)
  }
}
