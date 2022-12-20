package com.tirex_projs.telegram_clone.ui.fragments

import android.os.Bundle
import android.view.View
import com.tirex_projs.telegram_clone.R
import com.tirex_projs.telegram_clone.databinding.FragmentChatsBinding

class ChatsFragment : BaseFragment<FragmentChatsBinding>() {
    override fun getViewBinding() = FragmentChatsBinding.inflate(layoutInflater)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = "123Chats"
    }
    override fun onResume() {
        super.onResume()
    }


}