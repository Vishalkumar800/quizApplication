package com.rach.ProgrammingQuiz.activty

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.rach.ProgrammingQuiz.R
import com.rach.ProgrammingQuiz.databinding.ActivityQuizjavaBinding
import com.rach.ProgrammingQuiz.databinding.ScoreDailogBinding

import com.rach.ProgrammingQuiz.model.QuestionModel

class QuizjavaActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var binding : ActivityQuizjavaBinding

    companion object {

        var questionModelList : List<QuestionModel> = listOf()
        var time :String = ""

    }

    private var currentQuestionIndex = 0

    private var score = 0

    private var selectedAns = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizjavaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btn0.setOnClickListener(this@QuizjavaActivity)
            btn1.setOnClickListener(this@QuizjavaActivity)
            btn2.setOnClickListener(this@QuizjavaActivity)
            btn3.setOnClickListener(this@QuizjavaActivity)
            nextButton.setOnClickListener(this@QuizjavaActivity)
        }

        loadQuestion()
        startTimer()
        loadAds()

    }

    private fun loadAds() {

        var adView =binding.adView


        val adRequeat = AdRequest.Builder().build()

        adView.loadAd(adRequeat)

    }

    private fun startTimer() {

        val totalTimeInMillsSecond = time.toInt() * 60 * 1000L

        object  : CountDownTimer(totalTimeInMillsSecond,1000L){
            override fun onTick(millsecondUnitilFinshed: Long) {

                val seconds = millsecondUnitilFinshed / 1000
                val minutes = seconds / 60

                val remaingseconds = seconds % 60
                binding.timeIndiactorTextview.text = String.format("%02d:%02d",minutes,remaingseconds)

            }

            override fun onFinish() {

                val intent = Intent(this@QuizjavaActivity , JavaActivity2::class.java)
                Toast.makeText(this@QuizjavaActivity,"Opps ! Time End ",Toast.LENGTH_SHORT).show()
                startActivity(intent)

            }

        }.start()


    }

    private fun loadQuestion() {

        selectedAns = ""

        if (currentQuestionIndex == questionModelList.size){
            finishQuiz()
            return

        }

        binding.apply {

            questionIndiactorTextview.text = "Question ${currentQuestionIndex+1}/${questionModelList.size}"
            quizquestionProgressIndicator.progress =
                (currentQuestionIndex.toFloat() / questionModelList.size*100).toInt()

            questionTextView.text = questionModelList[currentQuestionIndex].question

            btn0.text = questionModelList[currentQuestionIndex].options[0]
            btn1.text = questionModelList[currentQuestionIndex].options[1]
            btn2.text = questionModelList[currentQuestionIndex].options[2]
            btn3.text = questionModelList[currentQuestionIndex].options[3]

        }

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onClick(view: View?) {

        val clickedBtn = view as Button

        binding.apply {

            btn0.setBackgroundColor(getColor(R.color.cardcolor))
            btn1.setBackgroundColor(getColor(R.color.cardcolor))
            btn2.setBackgroundColor(getColor(R.color.cardcolor))
            btn3.setBackgroundColor(getColor(R.color.cardcolor))

        }


        if (clickedBtn.id == R.id.nextButton){

            if (selectedAns == questionModelList[currentQuestionIndex].correct){
                score++
            }

            currentQuestionIndex++
            loadQuestion()

        }else{

            clickedBtn.setBackgroundColor(getColor(R.color.orange))
            selectedAns = clickedBtn.text.toString()
        }
    }
    private fun finishQuiz(){


        val totalQuestion = questionModelList.size
        val percentage = ((score.toFloat() / totalQuestion.toFloat()) * 100 ).toInt()

        val dialogBinding = ScoreDailogBinding.inflate(layoutInflater)
        dialogBinding.apply {

            scoreProgressIndiactor.progress = percentage
            scoreProgressText.text = "${percentage} %"

            if (percentage > 60){
                scoreTitle.text = "Congrats! You have passed"
                scoreTitle.setTextColor(Color.BLUE)
            }else {
                scoreTitle.text = "Oops! You have failed "
                scoreTitle.setTextColor(Color.RED)
            }

            scoreSubtitle.text = "$score out of $totalQuestion are correct"

            finishBtn.setOnClickListener {
                finish()
            }

        }

        AlertDialog.Builder(this)
            .setView(dialogBinding.root)
            .setCancelable(false)
            .show()



    }
}