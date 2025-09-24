package com.example.myfirstapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvFinalScore: TextView = findViewById(R.id.tv_final_score)
        val btnBack: Button = findViewById(R.id.btn_back)


        val score = intent.getIntExtra("FINAL_SCORE", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0)


        tvFinalScore.text = "Skor Anda: $score/$totalQuestions"

        btnBack.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("FINAL_SCORE", score)
            resultIntent.putExtra("TOTAL_QUESTIONS", totalQuestions)

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}