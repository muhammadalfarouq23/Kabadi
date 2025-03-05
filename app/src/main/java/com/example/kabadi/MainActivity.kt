package com.example.kabadi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private lateinit var tvScoreA: TextView
    private lateinit var tvScoreB: TextView
    private lateinit var btnPlus1A: Button
    private lateinit var btnPlus2A: Button
    private lateinit var btnPlus1B: Button
    private lateinit var btnPlus2B: Button
    private lateinit var btnReset: Button

    // Gunakan ViewModel
    private val scoreViewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi View
        tvScoreA = findViewById(R.id.tv_score_a)
        tvScoreB = findViewById(R.id.tv_score_b)
        btnPlus1A = findViewById(R.id.btn_plus1_a)
        btnPlus2A = findViewById(R.id.btn_plus2_a)
        btnPlus1B = findViewById(R.id.btn_plus1_b)
        btnPlus2B = findViewById(R.id.btn_plus2_b)
        btnReset = findViewById(R.id.btn_reset)

        // Observasi skor
        scoreViewModel.scoreTeamA.observe(this, Observer { scoreA ->
            tvScoreA.text = scoreA.toString()
        })

        scoreViewModel.scoreTeamB.observe(this, Observer { scoreB ->
            tvScoreB.text = scoreB.toString()
        })

        // Set tombol untuk update skor
        btnPlus1A.setOnClickListener { scoreViewModel.addScoreToTeamA(1) }
        btnPlus2A.setOnClickListener { scoreViewModel.addScoreToTeamA(2) }
        btnPlus1B.setOnClickListener { scoreViewModel.addScoreToTeamB(1) }
        btnPlus2B.setOnClickListener { scoreViewModel.addScoreToTeamB(2) }
        btnReset.setOnClickListener { scoreViewModel.resetScores() }
    }
}
