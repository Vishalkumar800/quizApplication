package com.rach.ProgrammingQuiz.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rach.ProgrammingQuiz.activty.QuizjavaActivity
import com.rach.ProgrammingQuiz.databinding.JavaQuizItemLayoutBinding
import com.rach.ProgrammingQuiz.model.QuizModel


class QuizListAdapter(val context: Context, val list: List<QuizModel>) :
    RecyclerView.Adapter<QuizListAdapter.QuizViewHolder>() {

    inner class QuizViewHolder(val binding: JavaQuizItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = JavaQuizItemLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return QuizViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val data = list[position]

        holder.binding.quizTitleText.text = data.title
        holder.binding.quizSubtitleText.text = data.subtitle
        holder.binding.quizTimeText.text = data.time+" min"

        holder.itemView.setOnClickListener {
            val intent = Intent (context, QuizjavaActivity::class.java)
            QuizjavaActivity.questionModelList = data.questionList
            QuizjavaActivity.time = data.time
            context.startActivity(intent)
        }

    }

}