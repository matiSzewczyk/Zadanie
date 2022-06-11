package com.example.zadanie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.zadanie.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment(R.layout.fragment_login){

    private val itemsViewModel: ItemsViewModel by viewModels()

    private lateinit var binding: FragmentLoginBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        binding.apply {
            loginButton.setOnClickListener {
                if (passwordInput.text.isEmpty() || usernameInput.text.isEmpty()) {
                    Toast.makeText(context, "Some fields are empty.", Toast.LENGTH_SHORT)
                        .show()
                    return@setOnClickListener
                }
                lifecycleScope.launch(IO) {
                    itemsViewModel.login(
                        passwordInput.text.toString(),
                        usernameInput.text.toString()
                    )
                    withContext(Main) {
                        val action = NavGraphDirections.actionGlobalItemsFragment()
                        findNavController().navigate(action)
                    }
                }
            }
        }
    }
}