package com.example.plan

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plan.databinding.WorkItemBinding

class WorkRvAdapter(val context: AddActivity, val workList: ArrayList<String>) : RecyclerView.Adapter<WorkRvAdapter.Holder>() {
    //ViewHolder 객체 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = WorkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }
    //띄울 데이터
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setData(workList[position], position)
    }
    //띄울 뷰의 개수
    override fun getItemCount(): Int {
        return workList.size
    }

    inner class Holder(val binding: WorkItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var position: Int? = null
        init {
            binding.workRvDeleteBtn.setOnClickListener {
                context.deleteWork(position!!)
            }
        }

        fun setData(data: String, position: Int) {
            binding.workRvNum.text = "${position + 1}"
            binding.workRvEditText.setText(data)
            binding.workRvEditText.addTextChangedListener(MemoTextWatcher(position))
            this.position = position
        }
    }

    inner class MemoTextWatcher(var position: Int) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun afterTextChanged(s: Editable?) {}

        override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
            workList[position] = text.toString()
        }
    }
}