package com.example.intelliplanner

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.intelliplanner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private var backKeyPressedTime: Long = 0
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
                    startActivity(Intent(this@MainActivity, com.example.plan.PlanActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }

                //계획 관리 활성화
                R.id.nav_managePlan -> {
                    it.isChecked = true
                    binding.drawerLayout.closeDrawers()
                    startActivity(Intent(this@MainActivity, com.example.plan.ManagePlanActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }

                //고객센터 활성화
                R.id.nav_cs -> {
                    it.isChecked = true
                    binding.drawerLayout.closeDrawers()
                    startActivity(Intent(this@MainActivity, MyPageActivity::class.java))
                    return@setNavigationItemSelectedListener true
                }

                else -> {
                    return@setNavigationItemSelectedListener true
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        //2000 == 2000 milliseconds == 2 seconds
        if(System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            Toast.makeText(this, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show()
            return
        }

        //2초 내에 뒤로가기 한번 더 누를시 앱 종료
         if(System.currentTimeMillis() <= backKeyPressedTime + 2000)
             finish()
    }
}