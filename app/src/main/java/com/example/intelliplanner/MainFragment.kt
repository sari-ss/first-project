package com.example.intelliplanner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.intelliplanner.databinding.FragmentMainBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)

        //spinner 구현
        val months = resources.getStringArray(R.array.months_array)
        val monthAdapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item, months)
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.mainMonthSpinner.adapter = monthAdapter

        val weeks = resources.getStringArray(R.array.week_array)
        val weekAdapter = ArrayAdapter<String>(requireContext(), R.layout.spinner_item, weeks)
        weekAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.mainWeekSpinner.adapter = weekAdapter

        val timeList = ArrayList<Int>()
        for(i in 9..24)
            timeList.add(i)
        binding.mainTimetable.adapter = TimeTableRvAdapter(timeList)
        binding.mainTimetable.layoutManager = LinearLayoutManager(requireActivity())

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}