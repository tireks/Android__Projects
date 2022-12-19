package com.tirex_projs.telegram_clone.ui.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tirex_projs.telegram_clone.R
import com.tirex_projs.telegram_clone.databinding.FragmentEnterAuthPhoneCodeBinding
import com.tirex_projs.telegram_clone.databinding.FragmentEnterPhoneNumBinding
import com.tirex_projs.telegram_clone.utilits.AppTextWatcher
import com.tirex_projs.telegram_clone.utilits.showToast

class EnterAuthPhoneCodeFragment : Fragment(R.layout.fragment_enter_auth_phone_code) {
    private var _binding: FragmentEnterAuthPhoneCodeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterAuthPhoneCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        binding.authInputPassCodeEditText.addTextChangedListener(AppTextWatcher {
            val string = binding.authInputPassCodeEditText.text.toString()
            if (string.length == 6) {
                verificateCode()
            }
        })
    }

    private fun verificateCode() {
        showToast("Ok")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}