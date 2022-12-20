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
import com.tirex_projs.telegram_clone.databinding.FragmentChatsBinding
import com.tirex_projs.telegram_clone.databinding.FragmentEnterAuthPhoneCodeBinding
import com.tirex_projs.telegram_clone.databinding.FragmentEnterPhoneNumBinding
import com.tirex_projs.telegram_clone.utilits.AppTextWatcher
import com.tirex_projs.telegram_clone.utilits.showToast

class EnterAuthPhoneCodeFragment : BaseFragment<FragmentEnterAuthPhoneCodeBinding>() {
    override fun getViewBinding() = FragmentEnterAuthPhoneCodeBinding.inflate(layoutInflater)
    // This property is only valid between onCreateView and
    // onDestroyView.
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



}