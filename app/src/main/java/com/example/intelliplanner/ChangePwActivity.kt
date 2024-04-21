package com.example.intelliplanner

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.intelliplanner.databinding.ActivityChangePwBinding
import com.example.plan.ConfirmDialog
import com.example.plan.ConfirmDialogInterface

class ChangePwActivity : AppCompatActivity(), ConfirmDialogInterface {
    private val binding by lazy {
        ActivityChangePwBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.changePwToolbar.apply {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener {
                finish()
            }
        }

        binding.changePwConfirmBtn.setOnClickListener {
            //SQL에서 확인작업 필요
            //일치하지 않으면 changePw_pwMismatch visible
            //확인되면 changePw_confirmLayout gone, changePw_newPwLayout visible
        }

        val newPw = binding.changePwNewPw
        val confirmPw = binding.changePwConfirmPw

        if(newPw.text != confirmPw)
            binding.changePwNewPwMismatch.visibility = View.VISIBLE
        else
            binding.changePwNewPwMismatch.visibility = View.INVISIBLE

        binding.changePwChangePwBtn.setOnClickListener {
            ConfirmDialog(this, "변경하시겠습니까?", null, "확인", 0)
        }
    }

    override fun onButtonClicked(id: Int) {
        if(id == 0) {
            //SQL 비밀번호 업데이트
            finish()
        }
    }
}