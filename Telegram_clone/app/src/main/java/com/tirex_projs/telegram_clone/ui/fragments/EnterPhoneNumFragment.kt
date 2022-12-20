package com.tirex_projs.telegram_clone.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tirex_projs.telegram_clone.R
import com.tirex_projs.telegram_clone.databinding.FragmentEnterPhoneNumBinding

class EnterPhoneNumFragment : BaseFragment<FragmentEnterPhoneNumBinding>() {
    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.authBtnNext.setOnClickListener { sendCode() }
    }

    private fun sendCode() {
        if (binding.authInputPhoneNumEditText.text.toString().isEmpty()){
            Toast.makeText(activity, getString(R.string.auth_passcode_top_label), Toast.LENGTH_SHORT).show()
        } else {
            parentFragmentManager.beginTransaction()
                .replace(R.id.AuthDataContainer, EnterAuthPhoneCodeFragment())
                .addToBackStack(null)
                .commit()
        }
    }
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentEnterPhoneNumBinding {
        return FragmentEnterPhoneNumBinding.inflate(inflater, container, false)    }
}