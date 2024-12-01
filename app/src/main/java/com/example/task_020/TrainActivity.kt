package com.example.task_020

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class TrainActivity : AppCompatActivity() {

    private lateinit var toolbarMain: Toolbar
    private lateinit var titleTV: TextView
    private lateinit var exerciseTV: TextView
    private lateinit var descriptionTV: TextView
    private lateinit var startButtonBTN: Button
    private lateinit var completedButtonBTN: Button
    private lateinit var timerTV: TextView
    private lateinit var imageViewIV: ImageView

    val exercises = ExersizeDataBase.exercises
    private var exerciseIndex = 0
    private lateinit var currentExersize: Exersize
    private lateinit var timer: CountDownTimer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_train)

        init()

        setSupportActionBar(toolbarMain)
        title = "Тренировки по фитнесу."

        startButtonBTN.setOnClickListener{
            startWorkout()
        }
        completedButtonBTN.setOnClickListener{
            completedExercise()
        }
    }

    fun init() {
        toolbarMain = findViewById(R.id.toolbarMain)
        titleTV = findViewById(R.id.titleTV)
        exerciseTV = findViewById(R.id.exerciseTV)
        descriptionTV = findViewById(R.id.descriptionTV)
        startButtonBTN = findViewById(R.id.startButtonBTN)
        completedButtonBTN = findViewById(R.id.completedButtonBTN)
        timerTV = findViewById(R.id.timerTV)
        imageViewIV = findViewById(R.id.imageViewIV)
    }

    private fun completedExercise() {
        timer.cancel()
        completedButtonBTN.isEnabled = false
        startNextExercise()
    }

    private fun startWorkout() {
        exerciseIndex = 0
        titleTV.text = "Начало тренировки"
        startButtonBTN.isEnabled = false
        startButtonBTN.text = "Процесс тренировки"
        startNextExercise()
    }

    private fun startNextExercise() {
        if (exerciseIndex < exercises.size) {
            currentExersize = exercises[exerciseIndex]
            exerciseTV.text = currentExersize.name
            descriptionTV.text = currentExersize.descripton
            imageViewIV.setImageResource(exercises[exerciseIndex].gifImage)
            timerTV.text = formatTime(currentExersize.durationInSeconds)
            timer = object : CountDownTimer(currentExersize.durationInSeconds*1000L, 1000)
            {
                override fun onTick(millisUntilFinished: Long) {
                    timerTV.text = formatTime((millisUntilFinished/1000).toInt())
                }
                override fun onFinish() {
                    timerTV.text = "Упражнение завершено"
                    imageViewIV.visibility = View.VISIBLE
                    completedButtonBTN.isEnabled = true
                    imageViewIV.setImageResource(0)
                }
            }.start()
            exerciseIndex++
        } else {
            exerciseTV.text = "Тренировка завершена"
            descriptionTV.text = ""
            timerTV.text = ""
            completedButtonBTN.isEnabled = false
            startButtonBTN.isEnabled = true
            startButtonBTN.text = "Начать снова"
        }
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return  String.format("%02d:%02d", minutes, remainingSeconds)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenuMain->{
                moveTaskToBack(true);
                exitProcess(-1)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}