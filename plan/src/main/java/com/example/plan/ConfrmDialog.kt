package com.example.plan

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.plan.databinding.DialogCustomBinding

interface ConfirmDialogInterface {
    fun onButtonClicked(id: Int)
}
class ConfirmDialog(
    confirmDialogInterface: ConfirmDialogInterface,
    title: String, content: String?, buttonText: String, id: Int
) : DialogFragment() {

    // 뷰 바인딩 정의
    private var _binding: DialogCustomBinding? = null
    private val binding get() = _binding!!

    //프로퍼티(멤버변수)
    private var confirmDialogInterface: ConfirmDialogInterface? = null
    private var title: String? = null
    private var content: String? = null
    private var buttonText: String? = null
    private var id: Int? = null

    //프로퍼티 초기화
    init {
        this.title = title
        this.content = content
        this.buttonText = buttonText
        this.id = id
        this.confirmDialogInterface = confirmDialogInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogCustomBinding.inflate(inflater, container, false)

        // 레이아웃 배경을 투명하게
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 제목
        binding.dialogTitle.text = title
        // 내용
        if (content == null) {
            binding.dialogDesc.visibility = View.GONE
        } else {
            binding.dialogDesc.text = content
        }
        // 확인 버튼 텍스트
        binding.dialogYesBtn.text = buttonText

        // 취소 버튼이 없는 다이얼로그는 id를 -1로 넘겨줌
        if (id == -1) {
            // 취소 버튼을 보이지 않게 처리
            binding.dialogNoBtn.visibility = View.GONE
        }

        // 취소 버튼 클릭
        binding.dialogNoBtn.setOnClickListener {
            dismiss()
        }

        // 확인 버튼 클릭
        binding.dialogYesBtn.setOnClickListener {
            this.confirmDialogInterface?.onButtonClicked(id!!)
            dismiss()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}