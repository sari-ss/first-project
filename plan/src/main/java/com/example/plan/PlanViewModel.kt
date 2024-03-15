package com.example.plan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlanViewModel: ViewModel() {
    private var _subject = MutableLiveData<String>("")
    val subject = _subject

    fun setSubject(item: String) {
        _subject.value = item
    }
}