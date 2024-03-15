package com.example.start_page

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.start_page.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginLoginBtn.setOnClickListener{
            startActivity(Intent(this, com.example.intelliplanner.MainActivity::class.java))
        }
        binding.loginRegisterBtn.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}