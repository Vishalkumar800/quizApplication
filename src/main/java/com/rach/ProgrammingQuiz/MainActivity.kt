package com.rach.ProgrammingQuiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rach.ProgrammingQuiz.adapters.homeAdapters
import com.rach.ProgrammingQuiz.databinding.ActivityMainBinding
import com.rach.ProgrammingQuiz.model.home_item_model

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapters()

    }

    private fun setAdapters() {

        val list = ArrayList<home_item_model>()

        list.add(home_item_model(R.drawable.java,"Java Quiz","1","Explore Java Fundamentals in a concise Quiz , This question Definately Enhance Your Konowledge","We wish for your coding journey ❤️❤️ "))

        list.add(home_item_model(R.drawable.phyton,"C++ Quiz","2","Explore Java Fundamentals in a concise Quiz , This question Definately Enhance Your Konowledge","We wish for your coding journey ❤️❤️"))

        list.add(home_item_model(R.drawable.phyton,"Phyton Quiz","3","Explore Java Fundamentals in a concise Quiz , This question Definately Enhance Your Konowledge","We wish for your coding journey ❤️❤️"))


        binding.recyclerView.adapter = homeAdapters(this , list)
    }
}