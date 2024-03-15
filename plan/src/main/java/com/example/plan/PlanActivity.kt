package com.example.plan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plan.databinding.ActivityPlanBinding

class PlanActivity : AppCompatActivity(), ConfirmDialogInterface {
    private val binding by lazy {
        ActivityPlanBinding.inflate(layoutInflater)
    }
    private val viewModel: PlanViewModel by lazy {
        ViewModelProvider(this).get(PlanViewModel::class.java)
    }
    val subjectList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.planRv.adapter = PlanRvAdapter(subjectList)
        binding.planRv.layoutManager = LinearLayoutManager(this)

        binding.planCancelBtn.setOnClickListener {
            val dialog = ConfirmDialog(this, "작성을 취소하시겠습니까?", "지금까지 내용이 저장되지 않습니다.", "확인", 1)
            this.let { dialog.show(it.supportFragmentManager, "ConfirmDialog") }
        }
        binding.planDoneBtn.setOnClickListener {
            val dialog = ConfirmDialog(this, "저장하시겠습니까?", null, "확인", 0)
            this.let { dialog.show(it.supportFragmentManager, "ConfirmDialog") }
        }
        binding.planAddBtn.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }

        val observer = object: Observer<String> {
            override fun onChanged(value: String) {
                subjectList.add(viewModel.subject.toString())
                binding.planRv.adapter!!.notifyItemInserted(subjectList.size - 1)
            }
        }
    }

    override fun onButtonClicked(id: Int) {
        if (id == 0) {
            //뷰모델에 날짜 넘겨주는 작업 필요
            //SQL에 저장하는 작업 필요
            //시간표 추가 작업 필요
            finish()
        }
        if (id == 1) {
            finish()
        }
    }
}