package com.example.lab1_flowers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[SharedViewModel::class.java]

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.inputFragmentContainer, InputFragment())
                .commit()
        }

        viewModel.orderResult.observe(this) { result ->
            if (result != null) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.resultFragmentContainer, ResultFragment())
                    .commit()
            } else {
                val fragment = supportFragmentManager.findFragmentById(R.id.resultFragmentContainer)
                if (fragment != null) {
                    supportFragmentManager.beginTransaction()
                        .remove(fragment)
                        .commit()
                }
            }
        }
    }
}