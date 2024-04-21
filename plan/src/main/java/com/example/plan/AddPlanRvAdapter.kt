package com.example.plan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.plan.databinding.PlanItemBinding

/* RecyclerView Adapter (커스텀 어댑터)
-> subjectName = 과목명리스트 -> plan_subjectName에 들어감
-> ViewHolder생성해서 xml의 textView를 할당해서 리스트의 이름을 하나씩 넣으며 자동으로 뷰 생성
 */
class AddPlanRvAdapter(val subjectList: ArrayList<String>) : RecyclerView.Adapter<AddPlanRvAdapter.Holder>() {
    //레이아웃 반환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddPlanRvAdapter.Holder {
        val binding = PlanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    //레이아웃에 무슨 데이터를 넣을 것인지
    override fun onBindViewHolder(holder: AddPlanRvAdapter.Holder, position: Int) {
        holder.setData(subjectList[position], position)
    }

    //생성될 뷰 개수 반환
    override fun getItemCount(): Int {
        return subjectList.size
    }

    //plan_item layout에 필요한 기능 구현
    inner class Holder(val binding: PlanItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val  addplanActivity = AddPlanActivity.getInstance()
        var data: String? = null
        var position: Int? = null

        init {
            binding.planRvDeleteBtn.setOnClickListener {
                addplanActivity!!.deleteSubject(data!!)
            }
            binding.planRVEditBtn.setOnClickListener {
                addplanActivity!!.editSubject(data!!, position!!)
            }
        }

        fun setData(data: String, position: Int) {
            binding.planRvSubjectName.text = data
            this.data = data
            this.position = position
        }
    }
}