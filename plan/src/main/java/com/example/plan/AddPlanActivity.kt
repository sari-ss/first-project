package com.example.plan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plan.databinding.ActivityPlanBinding

val subjectList = ArrayList<String>() //추가된 과목 리스트

//intent 에서 extras 전송할 때 key
const val SUBJECT_NAME = "subject name"
const val SUBJECT_POSITION = "subject position"

//과목 리스트
class AddPlanActivity : AppCompatActivity(), ConfirmDialogInterface {
    private val binding by lazy {
        ActivityPlanBinding.inflate(layoutInflater)
    }
    private val viewModel: PlanNameViewModel by lazy {
        ViewModelProvider(this).get(PlanNameViewModel::class.java)
    }
    private var adapter: AddPlanRvAdapter? = null

    //companion object instance 초기화
    init {
        instance = this
    }

    //객체 정보 전달
    companion object{
        private var instance:AddPlanActivity? = null
        fun getInstance(): AddPlanActivity? {
            return instance
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = AddPlanRvAdapter(subjectList)
        binding.planRv.adapter = adapter
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

        //subject 리스트 수정되었을 경우
        //recyclerview adapter에게 리스트가 수정되었음을 알림
        if(intent == null || intent.extras == null) return
        if(intent.extras?.getBoolean(EDIT_SUBJECT) == true)
            binding.planRv.adapter?.notifyDataSetChanged()
    }

    fun deleteSubject(data: String) {
        subjectList.remove(data)
        adapter?.notifyDataSetChanged()
    }

    fun editSubject(subject: String, position: Int) {
        val intent = Intent(this@AddPlanActivity, AddActivity::class.java)
        intent.putExtra(SUBJECT_NAME, subject)
        intent.putExtra(SUBJECT_POSITION, position)
        startActivity(intent)
    }

    override fun onButtonClicked(id: Int) {
        if (id == 0) {
            //SQL에 저장하는 작업 필요
            //시간표 추가 작업 필요
            finish()
        }
        if (id == 1) {
            finish()
        }
    }
}