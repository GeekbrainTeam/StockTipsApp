package com.amk.stocktipsapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.amk.core.entity.Company
import com.amk.core.entity.toStringU
import com.amk.core.repository.Repository
import com.amk.core.repository.View

class MainActivity : AppCompatActivity(), View {

    private val repository = Repository(this)
    private lateinit var textView: TextView
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text_view)
        editText = findViewById(R.id.edit_text)
        findViewById<Button>(R.id.button_list).setOnClickListener {
            repository.getCompanies()
        }
        findViewById<Button>(R.id.button_candles).setOnClickListener {
            repository.getCompanyCandles(editText.text.toString())
        }
    }

    override fun showResult(result: MutableList<Company>) {
        val text = StringBuilder("SIZE: ${result.size}\n\n")
        result.forEach { text.append("${it.shortName} ${it.secId} ${it.tradeDate.toStringU()} ${it.open} ${it.low} ${it.high} ${it.close}\n") }
        runOnUiThread { textView.text = text }
    }

    override fun showError(error: String) {
        runOnUiThread { Toast.makeText(this, error, Toast.LENGTH_LONG).show() }
    }
}