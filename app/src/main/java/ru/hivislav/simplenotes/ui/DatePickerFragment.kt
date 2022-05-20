package ru.hivislav.simplenotes.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.date_picker.*
import ru.hivislav.simplenotes.R
import ru.hivislav.simplenotes.ui.interfaces.DatePickerListener

class DatePickerFragment: DialogFragment() {

    @SuppressLint("InflateParams")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.date_picker, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val datePickerListener: DatePickerListener = requireActivity() as DatePickerListener
        val buttonOk = view.findViewById<TextView>(R.id.date_picker_button_ok)
        val buttonCancel = view.findViewById<TextView>(R.id.date_picker_button_cancel)


        buttonOk.setOnClickListener {
            datePickerListener.sendDatePicker(setDate())
            dismiss()
        }

        buttonCancel.setOnClickListener { dismiss() }
    }

    private fun setDate(): String {
        val day =
            (if (datePicker.dayOfMonth > 9) datePicker.dayOfMonth else "0" + datePicker.dayOfMonth).toString()
        val month =
            (if (datePicker.month > 8) datePicker.month + 1 else "0" + (datePicker.month + 1)).toString()
        val year = datePicker.year
        return "$day.$month.$year"
    }

    companion object {
        const val DATE_PICKER = "DATE_PICKER"

    }

}