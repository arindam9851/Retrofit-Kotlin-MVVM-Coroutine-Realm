package com.mvvm_kotlin_retrofit.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm_kotlin_retrofit.R
import com.mvvm_kotlin_retrofit.data.model.Data
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(private val users: ArrayList<Data>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: Data) {
            itemView.apply {
                textViewUserName.text = user.code
                textViewUserEmail.text = user.lang

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: List<Data>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}