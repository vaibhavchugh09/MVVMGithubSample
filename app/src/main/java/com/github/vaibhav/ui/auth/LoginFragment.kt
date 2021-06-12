package com.github.vaibhav.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.github.vaibhav.databinding.FragmentLoginBinding
import com.github.vaibhav.data.network.AuthApi
import com.github.vaibhav.data.network.Resource
import com.github.vaibhav.data.repository.AuthRepository
import com.github.vaibhav.ui.base.BaseFragment
import com.github.vaibhav.ui.enable
import com.github.vaibhav.ui.handleApiError
import com.github.vaibhav.ui.home.HomeActivity
import com.github.vaibhav.ui.startNewActivity
import com.github.vaibhav.ui.visible
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.progressbar.visible(false)
        binding.login.enable(false)
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer {
            binding.progressbar.visible(it is Resource.Loading)
            when (it) {
                is Resource.Success -> {
                    lifecycleScope.launch {
                        viewModel.saveAuthToken(it.value.createdAt)
                        requireActivity().startNewActivity(HomeActivity::class.java)
                    }
                }

                is Resource.Failure -> handleApiError(it) {
                    login()
                }
            }
        })

        binding.editTextPassword.addTextChangedListener {

            val email = binding.editTextEmailAddress.text.toString().trim()
            binding.login.enable(email.isNotEmpty() && it.toString().isNotEmpty())
        }
        binding.login.setOnClickListener {
            login()
        }

    }

    private fun login() {
        val email = binding.editTextEmailAddress.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        viewModel.login(email, password)

    }

    override fun getViewModel() = AuthViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() =
        AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)

}