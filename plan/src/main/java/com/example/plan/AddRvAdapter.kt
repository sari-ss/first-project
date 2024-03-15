package com.example.plan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plan.databinding.ContentItemBinding

class AddRvAdapter(val contentList: ArrayList<Int>) : RecyclerView.Adapter<AddRvAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ContentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.num.text = contentList[position].toString()
        holder.deleteBtn.setOnClickListener {
            contentList.removeAt(position)
            for(i in 0 until contentList.size)
                contentList[i] = i + 1
        }
    }

    override fun getItemCount(): Int {
        return contentList.size
    }

    inner class Holder(val binding: ContentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var deleteBtn = binding.contentDeleteBtn
        var num = binding.contentNum
    }
}