package com.example.plan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.plan.databinding.ActivityManagePlanBinding

class ManagePlanActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityManagePlanBinding.inflate(layoutInflater)
    }
    val dateList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val dateObserver = object: Observer<String> {
            override fun onChanged(value: String) {
                dateList.add(value)
            }
        }
    }
}