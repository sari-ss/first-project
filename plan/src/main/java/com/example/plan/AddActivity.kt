package com.example.plan

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plan.databinding.ActivityAddBinding

const val SUBJECT_NAME = "subject name"

class AddActivity : AppCompatActivity(), ConfirmDialogInterface {
    private val binding by lazy {
        ActivityAddBinding.inflate(layoutInflater)
    }
    private val viewModel: PlanViewModel by lazy {
        ViewModelProvider(this).get(PlanViewModel::class.java)
    }
    val contentList = ArrayList<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.addDoneBtn.setOnClickListener {
            val dialog = ConfirmDialog(this, "저장하시겠습니까?", null, "확인", 0)
            this.let {dialog.show(it.supportFragmentManager, "ConfirmDialog")}
        }
        binding.addCancelBtn.setOnClickListener {
            val dialog = ConfirmDialog(this, "작성을 취소하시겠습니까?", "지금까지 내용이 저장되지 않습니다.", "확인", 1)
            this.let {dialog.show(it.supportFragmentManager, "ConfirmDialog")}
        }
        binding.addContentBtn.setOnClickListener {
            contentList.add(contentList.size + 1)
            addContent()
        }
    }

    fun addContent() {
        if(contentList.size == 1) {
            binding.addContentRv.adapter = AddRvAdapter(contentList)
            binding.addContentRv.layoutManager = LinearLayoutManager(this)
            binding.addContentRv.visibility = View.VISIBLE
        }
        else {
            binding.addContentRv.adapter!!.notifyItemInserted(contentList.size - 1)
        }
    }

    fun removeContent(position: Int) {
        binding.addContentRv.adapter!!.notifyItemRemoved(position)
    }

    override fun onButtonClicked(id: Int) {
        if(id == 0) {
            viewModel.setSubject(binding.addSubjectName.text.toString())
            finish()
        }
        if(id == 1) {
            finish()
        }
    }
}