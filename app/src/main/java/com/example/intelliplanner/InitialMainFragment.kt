package com.example.intelliplanner

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.intelliplanner.databinding.FragmentInitialMainBinding
import com.example.plan.AddPlanActivity
import com.example.plan.PlanNameDialog
import com.example.plan.PlanNameDialogInterface
import com.example.plan.PlanNameViewModel

const val PLAN_NAME = "plan_name"

class InitialMainFragment : Fragment(), PlanNameDialogInterface {
    private val viewModel: PlanNameViewModel by lazy {
        ViewModelProvider(requireActivity()).get(PlanNameViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInitialMainBinding.inflate(inflater, container, false)

        binding.mainAddPlanBtn.setOnClickListener{
            val dialog = PlanNameDialog(this@InitialMainFragment)
            requireActivity().let { dialog.show(it.supportFragmentManager, "PlanNameDialog") }
        }

        return binding.root
    }

    override fun onBtnClicked(planName: String) {
        val intent = Intent(requireActivity(), AddPlanActivity::class.java)
        intent.putExtra(PLAN_NAME, planName)
        startActivity(intent)
    }
}