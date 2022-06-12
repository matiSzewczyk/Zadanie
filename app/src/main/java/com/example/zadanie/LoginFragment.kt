package com.example.zadanie

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.zadanie.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment(R.layout.fragment_login){

    private val loginViewModel: LoginViewModel by viewModels()

    private lateinit var binding: FragmentLoginBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        val errorObserver = Observer<String> {
            Toast.makeText(context, loginViewModel.errorMsg.value.toString(), Toast.LENGTH_SHORT)
                .show()
        }

        loginViewModel.errorMsg.observe(viewLifecycleOwner, errorObserver)

        binding.apply {
            loginButton.setOnClickListener {
//                if (passwordInput.text.isEmpty() || usernameInput.text.isEmpty()) {
//                    Toast.makeText(context, "Some fields are empty.", Toast.LENGTH_SHORT)
//                        .show()
//                    return@setOnClickListener
//                }
                lifecycleScope.launch(IO) {
                    loginViewModel.login(
                        passwordInput.text.toString(),
                        usernameInput.text.toString()
                    )
                    if (loginViewModel.loginSuccess) {
                        withContext(Main) {
                            val action = NavGraphDirections.actionGlobalItemsFragment()
                            findNavController().navigate(action)
                        }
                        loginViewModel.loginSuccess = false
                    }
                }
            }
        }
    }
}