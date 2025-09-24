package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var tvQuestion: TextView
    private lateinit var rgOptions: RadioGroup
    private lateinit var btnSubmit: Button
    private lateinit var tvFeedback: TextView
    private lateinit var rbOptionA: RadioButton
    private lateinit var rbOptionB: RadioButton
    private lateinit var rbOptionC: RadioButton
    private lateinit var rbOptionD: RadioButton

    // Data Kuis
    private val questions = listOf(
        Question(
            questionText = "Siapakah presiden pertama Indonesia?",
            options = listOf("Soekarno", "Soeharto", "Joko Widodo", "Susilo Bambang Yudhoyono"),
            correctAnswerIndex = 0
        ),
        Question(
            questionText = "Ibu kota negara Indonesia adalah?",
            options = listOf("Surabaya", "Bandung", "Jakarta", "Medan"),
            correctAnswerIndex = 2
        ),
        Question(
            questionText = "Planet terdekat dengan Matahari adalah?",
            options = listOf("Bumi", "Venus", "Mars", "Merkurius"),
            correctAnswerIndex = 3
        )
    )

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hubungkan komponen dari layout ke kode
        tvQuestion = findViewById(R.id.tv_question)
        rgOptions = findViewById(R.id.rg_options)
        btnSubmit = findViewById(R.id.btn_submit)
        tvFeedback = findViewById(R.id.tv_feedback)
        rbOptionA = findViewById(R.id.rb_option_a)
        rbOptionB = findViewById(R.id.rb_option_b)
        rbOptionC = findViewById(R.id.rb_option_c)
        rbOptionD = findViewById(R.id.rb_option_d)

        // Tampilkan pertanyaan pertama
        displayQuestion()

        // Tambahkan aksi saat tombol Jawab diklik
        btnSubmit.setOnClickListener {
            checkAnswer()
        }
    }

    private fun displayQuestion() {
        if (currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex]
            tvQuestion.text = currentQuestion.questionText
            rbOptionA.text = currentQuestion.options[0]
            rbOptionB.text = currentQuestion.options[1]
            rbOptionC.text = currentQuestion.options[2]
            rbOptionD.text = currentQuestion.options[3]

            // Atur ulang RadioGroup agar tidak ada yang terpilih sebelumnya
            rgOptions.clearCheck()
            tvFeedback.text = ""
            btnSubmit.text = "Jawab"
        } else {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("FINAL_SCORE", score)
            intent.putExtra("TOTAL_QUESTIONS", questions.size)
            startActivity(intent)
            finish() // Menutup MainActivity
        }
    }

    private fun checkAnswer() {
        val selectedId = rgOptions.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Pilih salah satu jawaban!", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedRadioButton = findViewById<RadioButton>(selectedId)
        val selectedAnswerIndex = rgOptions.indexOfChild(selectedRadioButton)
        val currentQuestion = questions[currentQuestionIndex]

        if (selectedAnswerIndex == currentQuestion.correctAnswerIndex) {
            score++
            tvFeedback.text = "Jawaban Benar! 🎉"
        } else {
            tvFeedback.text = "Jawaban Salah. 😔"
        }

        // Pindah ke pertanyaan berikutnya atau selesaikan kuis
        btnSubmit.text = "Lanjut"
        btnSubmit.setOnClickListener {
            currentQuestionIndex++
            displayQuestion()
        }
    }
}