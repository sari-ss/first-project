package com.example.plan

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plan.databinding.ActivityAddBinding

const val EDIT_SUBJECT = "edit subject"

//과목 1개 설정
class AddActivity : AppCompatActivity(), ConfirmDialogInterface {
    private val binding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }

    //time recyclerview, work recyclerview 의 adapter 에 들어갈 리스트
    private val workList = ArrayList<String>()
    private val timeList = ArrayList<TimeData>()

    //과목 수정시 수정할 과목명의 addplanActivity의 recyclerview 내 위치를 저장
    private var position: Int = 0
    var cnt = 0

    //timeRv, workRv 에서 item 삭제시 필요한 adapter
    private var timeRvAdapter: TimeRvAdapter? = null
    private var workRvAdapter: WorkRvAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dayOfWeekList = resources.getStringArray(R.array.day_of_week)
        val dayOfWeekAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dayOfWeekList)
        dayOfWeekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //시간 설정(요일, 시작시간, 종료시간) recyclerview adapter
        timeRvAdapter = TimeRvAdapter(this, dayOfWeekAdapter, timeList)
        binding.addTimeRv.adapter = timeRvAdapter
        binding.addTimeRv.layoutManager = LinearLayoutManager(this)

        workRvAdapter = WorkRvAdapter(this, workList)
        binding.addWorkRv.adapter = workRvAdapter
        binding.addWorkRv.layoutManager = LinearLayoutManager(this)

        //입력버튼 click
        binding.addDoneBtn.setOnClickListener {
            var dialog: ConfirmDialog? = null
            if(intent == null || intent.extras == null)
                dialog = ConfirmDialog(this, "저장하시겠습니까?", null, "확인", 0)
            else
                dialog = ConfirmDialog(this, "저장하시겠습니까?", null, "확인", 1)
            this.let {dialog.show(it.supportFragmentManager, "ConfirmDialog")}
        }

        //취소버튼 click
        binding.addCancelBtn.setOnClickListener {
            val dialog = ConfirmDialog(this, "작성을 취소하시겠습니까?", "지금까지 내용이 저장되지 않습니다.", "확인", 2)
            this.let {dialog.show(it.supportFragmentManager, "ConfirmDialog")}
        }

        //시간 추가 버튼 click
        binding.addTimeBtn.setOnClickListener {
            timeList.add(TimeData("월요일", "시작 시간", "종료 시간"))
            binding.addTimeRv.adapter!!.notifyItemInserted(timeList.size)
        }

        //할 일 추가 버튼 click
        binding.addWorkBtn.setOnClickListener {
            workList.add("")
            binding.addWorkRv.adapter!!.notifyDataSetChanged()
        }

        //AddPlanActivity에서 수정 버튼 클릭시 과목이름, 리스트에서 위치 받아옴
        if(intent == null || intent.extras == null) return
        val subject = intent.extras?.getString(SUBJECT_NAME)!!
        position = intent.extras?.getInt(SUBJECT_POSITION)!!
        binding.addSubjectName.setText(subject)
        //나머지 정보도 받아오는 작업 필요
    }

    //시간 삭제 버튼 click
    fun deleteTime(position: Int) {
        timeList.removeAt(position)
        timeRvAdapter?.notifyDataSetChanged()
    }

    //할 일 삭제 버튼 click
    fun deleteWork(position: Int) {
        workList.removeAt(position)
        workRvAdapter?.notifyDataSetChanged()
    }

    //ConfirmDialogInterface 에서 구현
    //확인 버튼 click
    override fun onButtonClicked(id: Int) {
        if(id == 0) {
            subjectList.add(binding.addSubjectName.text.toString())
            startActivity(Intent(this, AddPlanActivity::class.java))
        }
        if(id == 1) {
            subjectList[position] = binding.addSubjectName.text.toString()
            val intent = Intent(this, AddPlanActivity::class.java)
            intent.putExtra(EDIT_SUBJECT, true) //extra를 통해 단순한 과목추가가 아닌 과목 수정임을 알림
            startActivity(intent)
        }
        if(id == 2)
            finish()
    }
}