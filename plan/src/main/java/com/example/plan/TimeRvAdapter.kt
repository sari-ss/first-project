package com.example.plan

import android.app.TimePickerDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TimePicker
import androidx.recyclerview.widget.RecyclerView
import com.example.plan.databinding.TimeItemBinding

class TimeRvAdapter(val context: AddActivity, val dayOfWeekAdapter: ArrayAdapter<String>, val timeList: ArrayList<TimeData>) : RecyclerView.Adapter<TimeRvAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeRvAdapter.Holder {
        val binding = TimeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: TimeRvAdapter.Holder, position: Int) {
        holder.setData(dayOfWeekAdapter, timeList[position], position)
    }

    override fun getItemCount(): Int {
        return timeList.size
    }

    inner class Holder(val binding: TimeItemBinding): RecyclerView.ViewHolder(binding.root) {
        var data: TimeData? = null
        var position: Int? = null

        init {
            binding.timeRvDayOfWeekSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selected = parent?.getItemAtPosition(position).toString()
                        data!!.dayOfWeek = selected
                    }
                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }

            binding.timeRvStartTime.setOnClickListener{
                val time = showTimePicker(binding.timeRvStartTime.text.toString())
                if(time.isNotEmpty())
                    binding.timeRvStartTime.text = time
            }
            binding.timeRvEndTime.setOnClickListener {
                val time = showTimePicker(binding.timeRvEndTime.text.toString())
                if(time.isNotEmpty())
                    binding.timeRvEndTime.text = time
            }
            binding.timeRvDeleteBtn.setOnClickListener {
                context.deleteTime(position!!)
            }
        }

        fun setData(adapter: ArrayAdapter<String>, data: TimeData, position: Int) {
            binding.timeRvDayOfWeekSpinner.adapter = adapter
            val dayOfWeek = binding.timeRvDayOfWeekSpinner
            this.data = data
            this.position = position
        }

        fun showTimePicker(initialText: String) : String {
            var time = ""
            val dialog = TimePickerDialog(context, object : TimePickerDialog.OnTimeSetListener{
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    time = "$hourOfDay:$minute"
                }
            }, 24, 60, false).show()
            return time
        }
    }

    inner class TextWatcher
}

data class TimeData(var dayOfWeek: String, var Start: String, var End: String)