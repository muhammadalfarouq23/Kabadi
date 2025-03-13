package com.example.kabadi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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

    private val scoreViewModel: ScoreViewModel by viewModels()
    private val WINNING_SCORE = 25

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvScoreA = findViewById(R.id.tv_score_a)
        tvScoreB = findViewById(R.id.tv_score_b)
        btnPlus1A = findViewById(R.id.btn_plus1_a)
        btnPlus2A = findViewById(R.id.btn_plus2_a)
        btnPlus1B = findViewById(R.id.btn_plus1_b)
        btnPlus2B = findViewById(R.id.btn_plus2_b)
        btnReset = findViewById(R.id.btn_reset)

        scoreViewModel.scoreTeamA.observe(this, Observer { scoreA ->
            tvScoreA.text = scoreA.toString()
            checkWinner()
        })

        scoreViewModel.scoreTeamB.observe(this, Observer { scoreB ->
            tvScoreB.text = scoreB.toString()
            checkWinner()
        })

        btnPlus1A.setOnClickListener { scoreViewModel.addScoreToTeamA(1) }
        btnPlus2A.setOnClickListener { scoreViewModel.addScoreToTeamA(2) }
        btnPlus1B.setOnClickListener { scoreViewModel.addScoreToTeamB(1) }
        btnPlus2B.setOnClickListener { scoreViewModel.addScoreToTeamB(2) }
        btnReset.setOnClickListener {
            scoreViewModel.resetScores()
            enableButtons()
        }
    }

    private fun checkWinner() {
        val scoreA = scoreViewModel.scoreTeamA.value ?: 0
        val scoreB = scoreViewModel.scoreTeamB.value ?: 0

        when {
            scoreA >= WINNING_SCORE -> {
                showToast("Tim A Menang!")
                disableButtons()
            }
            scoreB >= WINNING_SCORE -> {
                showToast("Tim B Menang!")
                disableButtons()
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun disableButtons() {
        btnPlus1A.isEnabled = false
        btnPlus2A.isEnabled = false
        btnPlus1B.isEnabled = false
        btnPlus2B.isEnabled = false
    }

    private fun enableButtons() {
        btnPlus1A.isEnabled = true
        btnPlus2A.isEnabled = true
        btnPlus1B.isEnabled = true
        btnPlus2B.isEnabled = true
    }
}
