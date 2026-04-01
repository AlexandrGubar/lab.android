package com.example.lab1_flowers

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val textViewHistory = findViewById<TextView>(R.id.textViewHistory)
        val buttonCloseHistory = findViewById<Button>(R.id.buttonCloseHistory)

        try {
            val fis: FileInputStream = openFileInput("orders_history.txt")
            val isr = InputStreamReader(fis)
            val bufferedReader = BufferedReader(isr)
            val stringBuilder = java.lang.StringBuilder()
            var text: String?

            while (bufferedReader.readLine().also { text = it } != null) {
                stringBuilder.append(text).append("\n")
            }
            fis.close()

            val historyText = stringBuilder.toString()

            if (historyText.trim().isEmpty()) {
                textViewHistory.text = "Дані відсутні (сховище пусте)."
            } else {
                textViewHistory.text = historyText
            }

        } catch (e: Exception) {
            textViewHistory.text = "Дані відсутні (сховище пусте)."
        }

        buttonCloseHistory.setOnClickListener {
            finish()
        }
    }
}