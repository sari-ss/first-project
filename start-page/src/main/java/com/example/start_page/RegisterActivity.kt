package com.example.start_page

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.start_page.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.registerRegisterBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.registerDuplicateBtn.setOnClickListener {
            //SQL에서 확인작업 필요
        }

        val pw = binding.registerPwEditText.text.toString()
        val confirmPW = binding.registerConfrimPW.text.toString()
        if(pw != confirmPW)
            binding.registerDifferentPWTextView.visibility = View.VISIBLE
        binding.registerDifferentPWTextView.visibility = View.INVISIBLE
    }
}