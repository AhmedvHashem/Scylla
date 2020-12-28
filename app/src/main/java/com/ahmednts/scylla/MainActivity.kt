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

enum class CellType(@get:LayoutRes val type: Int) {
  USER(R.layout.item_user_cell),
  POST(R.layout.item_post_cell)
}

abstract class ICell {
  abstract val type: CellType
}

class UserCell : ICell() {
  override val type: CellType
    get() = CellType.USER
}

class PostCell : ICell() {
  override val type: CellType
    get() = CellType.POST
}

abstract class ICellViewHolder<DATA>(view: View) : RecyclerView.ViewHolder(view) {
  abstract fun bind(item: DATA)
}

class UserCellViewHolder(view: View) : ICellViewHolder<ICell>(view) {
  override fun bind(item: ICell) {
    val item = item as UserCell

  }
}

class PostCellViewHolder(view: View) : ICellViewHolder<ICell>(view) {
  override fun bind(item: ICell) {
    val item = item as PostCell

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
    return getItem(position).type.type
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ICellViewHolder<ICell> {
    val inflater = LayoutInflater.from(parent.context)

    return when (viewType) {
      CellType.USER.type -> {
        val view = inflater.inflate(viewType, parent, false)
        UserCellViewHolder(view)
      }
      CellType.POST.type -> {
        val view = inflater.inflate(viewType, parent, false)
        PostCellViewHolder(view)
      }
      else -> throw NullPointerException("o.O")
    }
  }

  override fun onBindViewHolder(holder: ICellViewHolder<ICell>, position: Int) {
    holder.bind(getItem(position))
  }
}
