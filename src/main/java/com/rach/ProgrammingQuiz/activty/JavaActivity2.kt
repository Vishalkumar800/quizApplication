package com.rach.ProgrammingQuiz.activty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.database.FirebaseDatabase
import com.rach.ProgrammingQuiz.adapters.QuizListAdapter
import com.rach.ProgrammingQuiz.databinding.ActivityJava2Binding

import com.rach.ProgrammingQuiz.model.QuizModel


class JavaActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityJava2Binding

    private lateinit var quizModelList : MutableList<QuizModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityJava2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        quizModelList = mutableListOf()

        getFirebaseData()

    }

    private fun getFirebaseData() {

//        val listOfQestionModel = mutableListOf<QuestionModel>()
//
//        listOfQestionModel.add(QuestionModel("What is Java", mutableListOf("language","English","Programming Language","None"),"Programming Language"))
//        listOfQestionModel.add(QuestionModel("What is Android", mutableListOf("Language","Os","None","Nobe2"),"Os"))
//
//        quizModelList.add(QuizModel("1","Java Quiz","Java Basic Quiz of 20 Question","8",listOfQestionModel))
////        quizModelList.add(QuizModel("2","Java Quiz Part 2","Java Quiz of 20 Question","5",listOfQestionModel))
////        quizModelList.add(QuizModel("3","Java Quiz Part 3","Java Quiz of 20 Question","5"))
////        quizModelList.add(QuizModel("4","Java Quiz Part 4","Java Quiz of 20 Question","5"))
////        quizModelList.add(QuizModel("5","Java Quiz Part 5","Java Quiz of 20 Question","5"))
////
//
//        binding.recyclerView.adapter=QuizListAdapter(this,quizModelList)

        binding.progressbar.visibility = View.VISIBLE

        FirebaseDatabase.getInstance().reference
            .get()
            .addOnSuccessListener {datasnapshot ->

                if (datasnapshot.exists()){
                    for (snapshot in datasnapshot.children){
                        val data = snapshot.getValue(QuizModel::class.java)
                        if (data != null){
                            quizModelList.add(data)
                        }
                    }
                    binding.progressbar.visibility=View.GONE
                    binding.recyclerView.adapter = QuizListAdapter(this,quizModelList)
                }
            }

    }


}