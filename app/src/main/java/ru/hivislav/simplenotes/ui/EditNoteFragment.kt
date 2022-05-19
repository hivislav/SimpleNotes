package ru.hivislav.simplenotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.hivislav.simplenotes.R
import ru.hivislav.simplenotes.data.Note

class EditNoteFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = arguments?.getParcelable<Note>(EDIT_NOTE)

        note?.also {
            view.findViewById<TextView>(R.id.set_title).text = it.title
            view.findViewById<TextView>(R.id.set_description).text = it.description
            view.findViewById<TextView>(R.id.set_date).text = it.date
        }


    }

    companion object {
        const val EDIT_NOTE: String = "EDIT_NOTE"

        fun getInstance(bundle: Bundle): EditNoteFragment {
            val fragment = EditNoteFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}