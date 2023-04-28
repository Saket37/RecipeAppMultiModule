package com.example.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.model.RecipeResult
import com.example.presentation.databinding.ItemRecipeBinding
import javax.inject.Inject

class RecipeItemAdapter @Inject constructor() :
    ListAdapter<RecipeResult, RecipeItemVH>(DiffUtilCallBack()) {

    var onClick: ((id: Int) -> Unit) = fun(_: Int) {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeItemVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeBinding.inflate(inflater, parent, false)
        return RecipeItemVH(binding)
    }

    override fun onBindViewHolder(holder: RecipeItemVH, position: Int) {
        with(getItem(position)) {
            holder.bind(this)
            holder.binding.root.setOnClickListener {
                onClick(id)
            }
        }
    }

    private class DiffUtilCallBack() : DiffUtil.ItemCallback<RecipeResult>() {
        override fun areItemsTheSame(oldItem: RecipeResult, newItem: RecipeResult): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: RecipeResult, newItem: RecipeResult): Boolean {
            return oldItem == newItem
        }
    }
}