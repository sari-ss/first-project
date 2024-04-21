package com.example.intelliplanner

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.intelliplanner.databinding.ActivityRegisterBinding
import org.json.JSONException
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    private val serverUrl = "http://192.168.152.1/C:\\Users\\smkbi\\AndroidStudioProjects\\IntelliPlanner/php파일"
    private val binding by lazy {
        ActivityRegisterBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.registerRegisterBtn.setOnClickListener {
            //SQL에 정보 저장
            onRegisterBtnClicked()
        }

        //아이디 중복 확인
        binding.registerDuplicateBtn.setOnClickListener {
            //SQL에서 확인작업 필요
            //아이디 중복일 경우 register_duplicateID visible
        }

        val pw = binding.registerPwEditText.text.toString()
        val confirmPW = binding.registerConfirmPw.text.toString()
        if(pw != confirmPW)
            binding.registerPwMismatch.visibility = View.VISIBLE
        binding.registerPwMismatch.visibility = View.INVISIBLE
    }

    //error dialog를 띄우는 메소드
    private fun showErrorDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage(message)
        builder.setPositiveButton("확인", null)
        val dialog = builder.create()
        dialog.show()
    }

    //success dialog를 띄우는 메소드
    private fun showSuccessDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Success")
        builder.setMessage(message)
        builder.setPositiveButton("확인", null)
        val dialog = builder.create()
        dialog.show()
    }

    private fun onRegisterBtnClicked() {
        if(binding.registerPwMismatch.visibility == View.VISIBLE) showErrorDialog("Pw mismatch")

        val userID = binding.registerIdEditText.text.toString()
        val userPw  = binding.registerPwEditText.text.toString()
        val userPwConfirm = binding.registerConfirmPw.text.toString()

        if(userID.isEmpty() || userPw.isEmpty() || userPwConfirm.isEmpty()) showErrorDialog("empty things exist")

        val json = JSONObject()
        json.put("userID", userID)
        json.put("userPw", userPw)

        sendPostRequest(json.toString())
    }

    // 서버로 POST 요청을 보내는 메소드
    private fun sendPostRequest(data: String) {
        val requestQueue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(
            Request.Method.POST,
            serverUrl,
            Response.Listener { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    val error = jsonResponse.getBoolean("error")

                    if (error) {
                        val errorMessage = jsonResponse.getString("message")
                        showErrorDialog(errorMessage)

                        // 에러 상세 정보를 출력할 경우
                        if (jsonResponse.has("errorDetails")) {
                            val errorDetails = jsonResponse.getString("errorDetails")
                            Log.e("ServerError", errorDetails)
                        }
                    } else {
                        val successMessage = jsonResponse.getString("message")
                        // 성공 메시지 처리
                        Log.d("ServerResponse", successMessage)
                    }
                } catch (e: JSONException) {
                    Log.e("JSONError", "Error parsing JSON: $response")

                    //json parsing error가 해결될때까지 성공 메세지코드는 여기 위치합니다.
                    showSuccessDialog("성공적으로 회원가입 되었습니다.")

                    //실질적으론 확인 버튼을 누르면 넘어가는게 아닌 2초 대기 후 넘어가는 코드
                    Handler().postDelayed({
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                    }, 2000) // 2초 대기
                }
            },
            Response.ErrorListener { error ->
                // 오류 처리
                Log.e("VolleyError", "Volley Error: ${error.networkResponse?.statusCode}, ${error.message}")
                showErrorDialog("서버 오류가 발생했습니다.")
            }
        ) {
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getBody(): ByteArray {
                return data.toByteArray()
            }
        }

        requestQueue.add(stringRequest)
    }
}