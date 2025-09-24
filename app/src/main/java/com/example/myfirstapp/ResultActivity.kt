package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvFinalScore: TextView = findViewById(R.id.tv_final_score)

        // Ambil data skor dari Intent
        val score = intent.getIntExtra("FINAL_SCORE", 0)
        val totalQuestions = intent.getIntExtra("TOTAL_QUESTIONS", 0)

        // Tampilkan skor di TextView
        tvFinalScore.text = "Skor Anda: $score/$totalQuestions"
    }
}