package com.mutsuddi.mypractice.data.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mutsuddi.mypractice.data.model.Characters
import com.mutsuddi.mypractice.databinding.CharactersRowBinding
import com.mutsuddi.mypractice.ui.MainActivity


class CharactersAdapter(private val listener: MainActivity) :
    ListAdapter<Characters, CharactersAdapter.MyViewHolder>(CHARACTER_COMPARATOR) {

    interface OnItemClickListener {
        fun onItemClick(character: Characters)
    }

    inner class MyViewHolder(private val binding: CharactersRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Characters?) {
            binding.textViewNameValue.text = character?.name
            binding.textViewBirthYearValue.text = character?.birth_year
            binding.textViewGenderValue.text = character?.gender

           /* binding.root.setOnClickListener {
                // Invoke the callback method with the clicked item
                listener.onItemClick(character!!)
            }*/


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            CharactersRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character)

    }

    companion object {
        val CHARACTER_COMPARATOR = object : DiffUtil.ItemCallback<Characters>() {
            override fun areItemsTheSame(oldItem: Characters, newItem: Characters): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: Characters, newItem: Characters): Boolean {
                return oldItem == newItem
            }
        }
    }


}