package com.tirex_projs.telegram_clone.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tirex_projs.telegram_clone.R
import com.tirex_projs.telegram_clone.databinding.FragmentEnterPhoneNumBinding

class EnterPhoneNumFragment : Fragment(R.layout.fragment_enter_phone_num) {
    private var _binding: FragmentEnterPhoneNumBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterPhoneNumBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onStart() {
        super.onStart()
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}