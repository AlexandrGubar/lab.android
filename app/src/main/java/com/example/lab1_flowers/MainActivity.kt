package com.example.lab1_flowers

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextInput = findViewById<EditText>(R.id.editTextInput)
        val radioGroupColor = findViewById<RadioGroup>(R.id.radioGroupColor)
        val radioGroupPrice = findViewById<RadioGroup>(R.id.radioGroupPrice)
        val buttonOk = findViewById<Button>(R.id.buttonOk)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonOk.setOnClickListener {
            val name = editTextInput.text.toString().trim()

            val selectedColorId = radioGroupColor.checkedRadioButtonId
            val selectedPriceId = radioGroupPrice.checkedRadioButtonId

            if (name.isEmpty() || selectedColorId == -1 || selectedPriceId == -1) {
                Toast.makeText(this, "Будь ласка, заповніть всі поля та зробіть вибір!", Toast.LENGTH_SHORT).show()
            } else {
                val colorRadioButton = findViewById<RadioButton>(selectedColorId)
                val priceRadioButton = findViewById<RadioButton>(selectedPriceId)

                val colorText = colorRadioButton.text.toString()
                val priceText = priceRadioButton.text.toString()

                val result = "Замовлення оформлено!\nЗамовник: $name\nКолір квітів: $colorText\nКатегорія: $priceText"

                textViewResult.text = result
            }
        }
    }
}