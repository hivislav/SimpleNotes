package ru.hivislav.simplenotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_edit_note.*
import ru.hivislav.simplenotes.R
import ru.hivislav.simplenotes.data.InMemoryRepoImp
import ru.hivislav.simplenotes.data.Note
import ru.hivislav.simplenotes.data.Repository
import ru.hivislav.simplenotes.databinding.FragmentEditNoteBinding

class EditNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    private val repository: Repository = InMemoryRepoImp
    private var noteId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = arguments?.getParcelable<Note>(EDIT_NOTE)

        note?.also {
            set_title.setText(it.title)
            set_description.setText(it.description)
            set_date.text = it.date
            noteId = note.id
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveNote()
        _binding = null
    }

    private fun saveNote() {
        val editNote: Note = Note(noteId, set_title.text.toString(), set_description.text.toString(), set_date.text.toString())
        repository.update(editNote)
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