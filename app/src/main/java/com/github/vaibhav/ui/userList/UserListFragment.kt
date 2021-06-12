package com.github.vaibhav.ui.userList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.vaibhav.data.network.Resource
import com.github.vaibhav.data.network.UserApi
import com.github.vaibhav.data.repository.UserListRepository
import com.github.vaibhav.data.responses.Data
import com.github.vaibhav.databinding.FragmentUserListBinding
import com.github.vaibhav.ui.base.BaseFragment

/**
 * A simple [Fragment] subclass.
 * Use the [UserListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserListFragment :
    BaseFragment<UserListViewModel, FragmentUserListBinding, UserListRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserList()

        viewModel.user.observe(viewLifecycleOwner, Observer {

            when (it) {

                is Resource.Success -> {
                    updateUi(it.value.data)
                }
            }
        })
    }

    private fun updateUi(data: List<Data>) {
        //adding a layoutmanager
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        //creating our adapter
        val adapter = UserRecylerViewAdapter(data)
        //now adding the adapter to recyclerview
        binding.recyclerView.adapter = adapter
    }

    override fun getViewModel() = UserListViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentUserListBinding.inflate(
        inflater, container, false
    )

    override fun getFragmentRepository(): UserListRepository {

        val api = remoteDataSource.buildApi(UserApi::class.java)
        return UserListRepository(api)
    }


}