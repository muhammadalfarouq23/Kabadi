package com.example.kabadi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var scoreTeamA = 4
    private var scoreTeamB = 0

    private lateinit var tvScoreA: TextView
    private lateinit var tvScoreB: TextView
    private lateinit var btnPlus1A: Button
    private lateinit var btnPlus2A: Button
    private lateinit var btnPlus1B: Button
    private lateinit var btnPlus2B: Button
    private lateinit var btnReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi view
        tvScoreA = findViewById(R.id.tv_score_a)
        tvScoreB = findViewById(R.id.tv_score_b)
        btnPlus1A = findViewById(R.id.btn_plus1_a)
        btnPlus2A = findViewById(R.id.btn_plus2_b)
        btnPlus1B = findViewById(R.id.btn_plus1_b)
        btnPlus2B = findViewById(R.id.btn_plus2_b)
        btnReset = findViewById(R.id.btn_reset)

        // Menampilkan nama pengguna dari LoginActivity (jika ada)
        val userName = intent.getStringExtra("USER_NAME")
        if (userName != null) {
            // Anda bisa menampilkan nama pengguna di TextView lain jika diperlukan
            // Misalnya: findViewById<TextView>(R.id.tv_welcome).text = "Welcome, $userName!"
        }

        // Set listener untuk tombol-tombol
        btnPlus1A.setOnClickListener {
            scoreTeamA += 1
            updateScore()
        }

        btnPlus2A.setOnClickListener {
            scoreTeamA += 2
            updateScore()
        }

        btnPlus1B.setOnClickListener {
            scoreTeamB += 1
            updateScore()
        }

        btnPlus2B.setOnClickListener {
            scoreTeamB += 2
            updateScore()
        }

        btnReset.setOnClickListener {
            resetScore()
        }

        // Update tampilan awal
        updateScore()
    }

    private fun updateScore() {
        tvScoreA.text = scoreTeamA.toString()
        tvScoreB.text = scoreTeamB.toString()
    }

    private fun resetScore() {
        scoreTeamA = 0
        scoreTeamB = 0
        updateScore()
    }
}