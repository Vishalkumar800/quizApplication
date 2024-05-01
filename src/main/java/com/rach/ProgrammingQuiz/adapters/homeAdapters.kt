package com.rach.ProgrammingQuiz.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rach.ProgrammingQuiz.databinding.HomeItemLayoutBinding

import com.rach.ProgrammingQuiz.model.home_item_model


class homeAdapters(val context: Context, val list: ArrayList<home_item_model>) :
    RecyclerView.Adapter<homeAdapters.homeViewHolder>() {

    inner class homeViewHolder(val binding: HomeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): homeViewHolder {
        val view = HomeItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return homeViewHolder(view)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: homeViewHolder, position: Int) {
        val data = list[position]

        holder.binding.image.setImageResource(data.image)
        holder.binding.title.text = data.title

        holder.itemView.setOnClickListener {

            val dialog = CustomDialog(context,data)
            dialog.show()

        }

    }

}