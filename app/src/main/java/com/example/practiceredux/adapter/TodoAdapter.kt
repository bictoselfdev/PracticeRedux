package com.example.practiceredux.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practiceredux.databinding.ItemTodoBinding

class TodoAdapter(private var itemList: ArrayList<String>) : RecyclerView.Adapter<TodoAdapter.RecyclerViewHolder?>() {
    private lateinit var binding: ItemTodoBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.updateViewHolder(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class RecyclerViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun updateViewHolder(item: String) {

            binding.tvTodo.text = item
        }
    }
}