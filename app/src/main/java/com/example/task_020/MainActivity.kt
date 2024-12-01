package com.example.task_020

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var toolbarMain: Toolbar
    private lateinit var mainExersizeTV: TextView
    private lateinit var imageViewIV: ImageView
    private lateinit var descriptionTV: TextView
    private lateinit var beginSportsBTN: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        setSupportActionBar(toolbarMain)
        title = "Тренировки по фитнесу."

        mainExersizeTV.text = "BOX"
        imageViewIV.setImageResource(R.drawable.male_boxer)
        descriptionTV.text = "Бокс опасный спорт!!!"

        beginSportsBTN.setOnClickListener{
            val intent = Intent(this, TrainActivity::class.java)
            startActivity(intent)
        }
    }

    fun init() {
        toolbarMain = findViewById(R.id.toolbarMain)
        mainExersizeTV = findViewById(R.id.mainExersizeTV)
        imageViewIV = findViewById(R.id.imageViewIV)
        descriptionTV = findViewById(R.id.descriptionTV)
        beginSportsBTN = findViewById(R.id.beginSportsBTN)
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