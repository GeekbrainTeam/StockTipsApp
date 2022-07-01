package com.amk.stocktipsapp.model

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.amk.stocktipsapp.R

class DialogSorting : DialogFragment(), View.OnClickListener, AdapterView.OnItemSelectedListener {
    private var addToast = "not sorted"
    var currentChoose = "not direction"
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_sort, null)
            builder.setView(view)
            view.findViewById<Button>(R.id.bottom_attach_sorting).setOnClickListener(this)
            val radioGroup: RadioGroup = view.findViewById(R.id.radio_group_sorting)
            val spinner: Spinner = view.findViewById(R.id.up_down)
            spinner.onItemSelectedListener = this


            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.radio_change_half_year -> addToast = "по половине года"
                    R.id.radio_change_day -> addToast = "по дню"
                    R.id.radio_price -> addToast = "по цене"
                    R.id.radio_name -> addToast = "по названию"
                }
            }


            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onClick(p0: View?) {
        Toast.makeText(activity, "Сортируем  $addToast $currentChoose", Toast.LENGTH_SHORT)
            .show()
        dismiss()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val choose: Array<String> = myFragmentFunction() as Array<String>
        currentChoose = choose[p2]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
    fun myFragmentFunction(): Array<out String>? {
        val context = context
        return context?.resources?.getStringArray(R.array.direction)

    }

}