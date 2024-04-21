package com.example.plan
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.plan.databinding.DialogPlanNameBinding

interface PlanNameDialogInterface {
    fun onBtnClicked(planName: String)
}
class PlanNameDialog(planNameDialogInterface: PlanNameDialogInterface): DialogFragment() {
    private var _binding: DialogPlanNameBinding? = null
    private val binding get() = _binding!!

    private var planNameDialogInterface: PlanNameDialogInterface? = null

    init {
        this.planNameDialogInterface = planNameDialogInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogPlanNameBinding.inflate(inflater, container, false)

        // 레이아웃 배경을 투명하게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //취소버튼 클릭
        binding.planNameCancelBtn.setOnClickListener {
            dismiss()
        }

        //확인버튼 클릭
        binding.planNameDoneBtn.setOnClickListener {
            val planName = binding.planNameEditText
            this.planNameDialogInterface?.onBtnClicked(binding.planNameEditText.text.toString())
            dismiss()
        }
        return binding.root
    }
}