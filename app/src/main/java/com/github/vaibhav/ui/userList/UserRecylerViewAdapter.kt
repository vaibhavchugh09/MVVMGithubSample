package com.github.vaibhav.ui.userList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.vaibhav.data.responses.Data
import com.github.vaibhav.databinding.AdapterLayoutBinding


class UserRecylerViewAdapter(val userList: List<Data>) :
    RecyclerView.Adapter<UserRecylerViewAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = AdapterLayoutBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }


    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.binding.textViewUsername.text= userList.get(position).first_name
        holder.binding.textViewAddress.text= userList.get(position).email
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ItemViewHolder(val binding: AdapterLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}
