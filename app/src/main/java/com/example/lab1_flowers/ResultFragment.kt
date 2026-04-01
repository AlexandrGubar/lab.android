package com.example.lab1_flowers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ResultFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val textViewResult = view.findViewById<TextView>(R.id.textViewResult)
        val buttonCancel = view.findViewById<Button>(R.id.buttonCancel)

        viewModel.orderResult.observe(viewLifecycleOwner) { result ->
            textViewResult.text = result
        }

        buttonCancel.setOnClickListener {
            viewModel.orderResult.value = null
            viewModel.clearFormEvent.value = true
        }

        return view
    }
}