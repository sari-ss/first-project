package com.example.intelliplanner

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.example.intelliplanner.databinding.ActivityMainBinding
import com.example.plan.AddPlanActivity
import com.example.plan.ConfirmDialog
import com.example.plan.ConfirmDialogInterface
import com.example.plan.ManagePlanActivity
import com.example.plan.PlanNameViewModel

class MainActivity : AppCompatActivity(), ConfirmDialogInterface {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: PlanNameViewModel by lazy {
        ViewModelProvider(this).get(PlanNameViewModel::class.java)
    }
    private var backKeyPressedTime: Long = 0
    private lateinit var datePickerDialog: DatePickerDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if(fragment == null) { //아무것도 배치되어있지 않음
            supportFragmentManager.beginTransaction().apply {
                add(R.id.fragment_container, InitialMainFragment())
                commit()
            }
        }

        //plan의 개수가 0이 아닐때 fragment 교체작업 필요

        //메뉴 버튼 구현 (Drawer 열기)
        binding.mainMenuButton.setOnClickListener {
            binding.drawerLayout.open()
            binding.drawerLayout.openDrawer(GravityCompat.START)
            binding.drawerLayout.openDrawer(Gravity.LEFT)
        }

        //drawerlayout navigationview 구현
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {

                //계획 추가 활성화
                R.id.nav_addPlan -> {
                    it.isChecked = true
                    binding.drawerLayout.closeDrawers()
                    startActivity(Intent(this, AddPlanActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }

                //계획 관리 활성화
                R.id.nav_managePlan -> {
                    it.isChecked = true
                    binding.drawerLayout.closeDrawers()
                    startActivity(Intent(this, ManagePlanActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }

                //고객센터 활성화
                R.id.nav_cs -> {
                    it.isChecked = true
                    binding.drawerLayout.closeDrawers()
                    startActivity(Intent(this, SettingActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }

                else -> {
                    return@setNavigationItemSelectedListener true
                }
            }
        }
    }

    override fun onButtonClicked(id: Int) {
        if(id == 0)
            finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        //2000 == 2000 milliseconds == 2 seconds
        if(System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            val dialog = ConfirmDialog(this@MainActivity, "종료하시겠습니까?",null, "확인", 0)
            this?.let { dialog.show(it.supportFragmentManager, "ConfirmDialog") }
        }

        //2초 내에 뒤로가기 한번 더 누를시 앱 종료
         if(System.currentTimeMillis() <= backKeyPressedTime + 2000)
             finish()
    }
}