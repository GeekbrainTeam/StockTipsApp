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

    private var typeOfSort: TypeSort = DEFAULT_TYPE_SORT
    private var directionChoose: Direction = DEFAULT_DIRECTION_SORT

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
                R.id.radio_name_company -> typeOfSort = TypeSort.Name
                R.id.radio_price -> typeOfSort = TypeSort.Price
                R.id.radio_change_price -> typeOfSort = TypeSort.ChangePrice
                R.id.radio_change_percent -> typeOfSort = TypeSort.Percent
            }
        }
        binding.upDown.onItemSelectedListener = this
        binding.bottomAttachSorting.setOnClickListener {
            setFragmentResult(
                KEY,
                bundleOf(TYPE_OF_SORT to typeOfSort, DIRECTION_OF_SORT to directionChoose)
            )
            dismiss()
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        directionChoose = if (p2 == 0) Direction.Up else Direction.Down
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