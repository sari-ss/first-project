package com.example.plan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlanNameViewModel: ViewModel() {
    private var _name = MutableLiveData<String>("")
    val name get() = _name
    fun setDate(data: String) {
        _name.value = data
    }
}