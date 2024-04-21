package com.example.intelliplanner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.intelliplanner.databinding.TimetableItemBinding

class TimeTableRvAdapter(val timeList: Array<Int>) : RecyclerView.Adapter<TimeTableRvAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = TimetableItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.time.text = timeList[position].toString()
    }

    override fun getItemCount(): Int {
        return timeList.size
    }

    inner class Holder(val binding: TimetableItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var time = binding.rvTime
    }
}