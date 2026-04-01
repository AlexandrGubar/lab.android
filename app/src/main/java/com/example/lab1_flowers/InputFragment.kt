package com.example.lab1_flowers

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import java.io.FileOutputStream

class InputFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false)

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val editTextInput = view.findViewById<EditText>(R.id.editTextInput)
        val radioGroupColor = view.findViewById<RadioGroup>(R.id.radioGroupColor)
        val radioGroupPrice = view.findViewById<RadioGroup>(R.id.radioGroupPrice)
        val buttonOk = view.findViewById<Button>(R.id.buttonOk)
        val buttonOpenHistory = view.findViewById<Button>(R.id.buttonOpenHistory)

        buttonOk.setOnClickListener {
            val name = editTextInput.text.toString().trim()
            val selectedColorId = radioGroupColor.checkedRadioButtonId
            val selectedPriceId = radioGroupPrice.checkedRadioButtonId

            if (name.isEmpty() || selectedColorId == -1 || selectedPriceId == -1) {
                Toast.makeText(requireContext(), "Будь ласка, заповніть всі поля!", Toast.LENGTH_SHORT).show()
            } else {
                val colorRadioButton = view.findViewById<RadioButton>(selectedColorId)
                val priceRadioButton = view.findViewById<RadioButton>(selectedPriceId)

                val result = "Замовлення оформлено!\nЗамовник: $name\nКолір квітів: ${colorRadioButton.text}\nКатегорія: ${priceRadioButton.text}"

                viewModel.orderResult.value = result

                try {
                    val fos: FileOutputStream = requireContext().openFileOutput("orders_history.txt", Context.MODE_APPEND)
                    val textToSave = "$result\n-------------------------\n"
                    fos.write(textToSave.toByteArray())
                    fos.close()

                    Toast.makeText(requireContext(), "Дані успішно збережено у файл!", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(requireContext(), "Помилка при збереженні даних!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        buttonOpenHistory.setOnClickListener {
            val intent = Intent(requireContext(), HistoryActivity::class.java)
            startActivity(intent)
        }

        viewModel.clearFormEvent.observe(viewLifecycleOwner) { shouldClear ->
            if (shouldClear) {
                editTextInput.text.clear()
                radioGroupColor.clearCheck()
                radioGroupPrice.clearCheck()
                viewModel.clearFormEvent.value = false
            }
        }

        return view
    }
}