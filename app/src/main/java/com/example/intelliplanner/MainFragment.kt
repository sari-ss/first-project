package com.example.intelliplanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intelliplanner.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)

        //spinner 구현
        val plans = resources.getStringArray(R.array.plan_list)
        val planAdapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item, plans)
        planAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.mainPlanListSpinner.adapter = planAdapter

        val timeList = Array<Int>(15){i -> i + 9}
        binding.mainTimetable.adapter = TimeTableRvAdapter(timeList)
        binding.mainTimetable.layoutManager = LinearLayoutManager(requireActivity())

        return binding.root
    }
}