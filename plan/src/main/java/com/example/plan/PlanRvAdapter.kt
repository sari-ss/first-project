package com.example.plan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plan.databinding.PlanItemBinding

/* RecyclerView Adapter (커스텀 어댑터)
-> subjectName = 과목명리스트 -> plan_subjectName에 들어감
-> ViewHolder생성해서 xml의 textView를 할당해서 리스트의 이름을 하나씩 넣으며 자동으로 뷰 생성
 */
class PlanRvAdapter(val subjectList : ArrayList<String>) : RecyclerView.Adapter<PlanRvAdapter.Holder>() {

    //레이아웃 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanRvAdapter.Holder {
        val binding = PlanItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return Holder(binding)
    }

    //레이아웃에 무슨 데이터를 넣을 것인지
    override fun onBindViewHolder(holder: PlanRvAdapter.Holder, position: Int) {
        holder.name.text = subjectList[position]
        holder.deleteBtn.setOnClickListener {
            subjectList.removeAt(position)
        }
        holder.editBtn.setOnClickListener {
        }
    }

    //생성될 뷰 개수 반환
    override fun getItemCount(): Int {
        return subjectList.size
    }

    //plan_item layout에 필요한 기능 구현
    inner class Holder(val binding: PlanItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var name = binding.planRvSubjectName
        val deleteBtn = binding.planRvDeleteBtn
        val editBtn = binding.planRVEditBtn
    }
}