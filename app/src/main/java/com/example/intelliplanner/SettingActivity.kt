package com.example.intelliplanner

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.intelliplanner.databinding.ActivitySettingBinding
import com.example.plan.ConfirmDialog
import com.example.plan.ConfirmDialogInterface

class SettingActivity : AppCompatActivity(), ConfirmDialogInterface {
    private val binding by lazy {
        ActivitySettingBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //toolbar 뒤로가기 설정
        binding.settingToolbar.apply {
            setNavigationIcon(R.drawable.ic_back)
            setNavigationOnClickListener {
                finish()
            }
        }

        binding.settingChangePW.setOnClickListener {
            startActivity(Intent(this@SettingActivity, ChangePwActivity::class.java))
        }

        binding.settingLogout.setOnClickListener {
            logout()
        }

        binding.settingQuit.setOnClickListener {
            quit()
        }
    }

    private fun logout() {
        // 다이얼로그 실행
        val dialog = ConfirmDialog(this@SettingActivity, "로그아웃 하시겠어요?", null, "확인", 0)
        this?.let { dialog.show(it.supportFragmentManager, "ConfirmDialog") }
    }

    private fun quit() {
        // 다이얼로그
        val title = "정말 계정을 삭제하시겠어요?"
        val content = "지금까지의 정보가 모두 사라집니다."
        val dialog = ConfirmDialog(this@SettingActivity, title, content, "확인", 1)
        this?.let { dialog.show(it.supportFragmentManager, "ConfirmDialog") }
    }

    override fun onButtonClicked(id: Int) { // 다이얼로그 확인 메시지 클릭
        if (id == 0) { // 로그아웃
            System.exit(0)
        }
        else if (id == 1) { // 회원탈퇴
            //회원탈퇴 activity로 넘어감
        }
        // 화면 이동
        this?.finishAffinity()
    }
}