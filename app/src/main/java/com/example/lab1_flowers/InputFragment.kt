package com.example.lab1_flowers

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

        buttonOk.setOnClickListener {
            val name = editTextInput.text.toString().trim()
            val selectedColorId = radioGroupColor.checkedRadioButtonId
            val selectedPriceId = radioGroupPrice.checkedRadioButtonId

            if (name.isEmpty() || selectedColorId == -1 || selectedPriceId == -1) {
                Toast.makeText(requireContext(), "Будь ласка, заповніть всі поля та зробіть вибір!", Toast.LENGTH_SHORT).show()
            } else {
                val colorRadioButton = view.findViewById<RadioButton>(selectedColorId)
                val priceRadioButton = view.findViewById<RadioButton>(selectedPriceId)

                val result = "Замовлення оформлено!\nЗамовник: $name\nКолір квітів: ${colorRadioButton.text}\nКатегорія: ${priceRadioButton.text}"
                viewModel.orderResult.value = result
            }
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