package com.amk.mylibrary.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.amk.mylibrary.R
import com.amk.mylibrary.databinding.DialogSortBinding
import com.amk.mylibrary.utils.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DialogSorting : BottomSheetDialogFragment(),
    AdapterView.OnItemSelectedListener {
    private lateinit var binding: DialogSortBinding

    private var typeOfSort = ONE_CHOICE
    private var directionChoose = DIRECTION_UP
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSortBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.radioGroupSorting.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radio_name_company -> typeOfSort = ONE_CHOICE
                R.id.radio_price -> typeOfSort = TWO_CHOICE
                R.id.radio_change_price -> typeOfSort = TREE_CHOICE
                R.id.radio_change_percent -> typeOfSort = FOUR_CHOICE
            }
        }
        binding.upDown.onItemSelectedListener = this
        binding.bottomAttachSorting.setOnClickListener{
            setFragmentResult(
                KEY,
                bundleOf(TYPE_OF_SORT to typeOfSort, DIRECTION_OF_SORT to directionChoose)
            )
            dismiss()
        }
    }
    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
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
                    R.id.radio_name_company -> typeOfSort = ONE_CHOICE
                    R.id.radio_price -> typeOfSort = TWO_CHOICE
                    R.id.radio_change_price -> typeOfSort = TREE_CHOICE
                    R.id.radio_change_percent -> typeOfSort = FOUR_CHOICE
                }
            }


            builder.create()
        } ?: throw IllegalStateException(NO_NULL_ACTIVITY)
    }*/

    /*override fun onClick(p0: View?) {
        setFragmentResult(
            KEY,
            bundleOf(TYPE_OF_SORT to typeOfSort, DIRECTION_OF_SORT to directionChoose)
        )
        dismiss()
    }*/

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val choose: Array<String> = myFragmentFunction() as Array<String>
        directionChoose = choose[p2]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun myFragmentFunction(): Array<out String>? {
        val context = context
        return context?.resources?.getStringArray(R.array.direction)

    }

    companion object {
        fun getInstance() = DialogSorting()
    }

}