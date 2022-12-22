package com.tirex_projs.telegram_clone.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.tirex_projs.telegram_clone.R
import com.tirex_projs.telegram_clone.databinding.FragmentEnterPhoneNumBinding
import com.tirex_projs.telegram_clone.utilits.replaceFragment
import com.tirex_projs.telegram_clone.utilits.showToast

class EnterPhoneNumFragment : BaseFragment<FragmentEnterPhoneNumBinding>() {
    private lateinit var mPhoneNumber: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.authBtnNext.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (binding.authInputPhoneNumEditText.text.toString().isEmpty()){
            showToast(getString(R.string.auth_passcode_top_label))
        } else {
           //authUser()
            replaceFragment(EnterAuthPhoneCodeFragment(), R.id.AuthDataContainer)
        }
    }

    /*private fun authUser() {
        var authOptions = PhoneAuthOptions.newBuilder(
            //mPhoneNumber
        )
        mPhoneNumber= binding.authInputPhoneNumEditText.text.toString()
        PhoneAuthProvider.verifyPhoneNumber(
        )
    }*/

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEnterPhoneNumBinding {
        return FragmentEnterPhoneNumBinding.inflate(inflater, container, false)    }
}