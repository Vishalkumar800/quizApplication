package com.rach.ProgrammingQuiz.adapters

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.rach.ProgrammingQuiz.R

import com.rach.ProgrammingQuiz.activty.CplusActivity
import com.rach.ProgrammingQuiz.activty.JavaActivity2
import com.rach.ProgrammingQuiz.activty.phytonActivity
import com.rach.ProgrammingQuiz.model.home_item_model


class CustomDialog(val appcontext:Context ,private val model : home_item_model):Dialog(appcontext) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dilog_bg)

        val title : TextView = findViewById(R.id.programtitle)

        val des : TextView = findViewById(R.id.progressdes)

        val sloagn :TextView = findViewById(R.id.slogan)

        val button :AppCompatButton = findViewById(R.id.nextbtn)

        title.text = model.title

        des.text = model.des

        sloagn.text = model.love

        button.setOnClickListener {

            if (model.id == "1"){

                neviagte(JavaActivity2::class.java)

            }else if (model.id == "2"){
                neviagte(CplusActivity::class.java)
            }else if (model.id =="3"){
                neviagte(phytonActivity::class.java)
            }

        }



    }
    private fun neviagte(activty : Class<*>){

        val intent = Intent (appcontext ,activty)
        appcontext.startActivity(intent)

    }

}