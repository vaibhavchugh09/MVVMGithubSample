package com.github.vaibhav.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.github.vaibhav.data.network.Resource
import com.github.vaibhav.data.network.UserApi
import com.github.vaibhav.data.repository.UserRepository
import com.github.vaibhav.data.responses.Data
import com.github.vaibhav.databinding.FragmentHomeBinding
import com.github.vaibhav.ui.base.BaseFragment
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding, UserRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUser()

        viewModel.user.observe(viewLifecycleOwner, Observer {

            when (it) {

                is Resource.Success -> {
                    updateUi(it.value.data)
                }
            }
        })


        binding.buttonlogout.setOnClickListener() {

            logout()
        }

        binding.btUserlist.setOnClickListener()
        {
            val action = HomeFragmentDirections.actionHomeFragmentToUserListFragment()
            findNavController().navigate(action)
        }

    }

    fun updateUi(data: Data) {

        Toast.makeText(context, data.email, Toast.LENGTH_SHORT).show()
    }

    override fun getViewModel() = HomeViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHomeBinding.inflate(inflater, container, false)

    override fun getFragmentRepository(): UserRepository {
        val token = runBlocking {
            userPreferences.authToken.first()
        }
        val api = remoteDataSource.buildApi(UserApi::class.java, token)
        return UserRepository(api)
    }

}