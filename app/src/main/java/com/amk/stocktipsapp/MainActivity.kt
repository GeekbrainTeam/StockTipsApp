package com.amk.stocktipsapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amk.core.entity.Company
import com.amk.core.repository.Repository
import com.amk.core.repository.View

class MainActivity : AppCompatActivity(), View {

    private val repository = Repository(this)
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_view)
        findViewById<Button>(R.id.button).setOnClickListener {
            repository.getAllCompany()
        }

    }

    override fun showResult(companiesList: MutableList<Company>) {
        runOnUiThread { textView.text = companiesList.toString() }
    }

    override fun showError(error: String) {
        runOnUiThread { Toast.makeText(this, error, Toast.LENGTH_LONG).show() }
    }
}