package com.example.intelliplanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intelliplanner.databinding.ActivityLoginBinding
import com.example.plan.ConfirmDialog
import com.example.plan.ConfirmDialogInterface

class LoginActivity : AppCompatActivity(), ConfirmDialogInterface {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    private var backKeyPressedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginLoginBtn.setOnClickListener{
            //SQL에서 사용자 정보 확인 필요
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.loginRegisterBtn.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        //2000 == 2000 milliseconds == 2 seconds
        if(System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            val dialog = ConfirmDialog(this@LoginActivity, "종료하시겠습니까?",null, "확인", 0)
            this?.let { dialog.show(it.supportFragmentManager, "ConfirmDialog") }
        }

        //2초 내에 뒤로가기 한번 더 누를시 앱 종료
        if(System.currentTimeMillis() <= backKeyPressedTime + 2000)
            finish()
    }

    override fun onButtonClicked(id: Int) {
        if(id == 0)
            finish()
    }
}