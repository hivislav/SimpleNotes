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

class AddNoteFragment : Fragment() {

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    private val repository: Repository = InMemoryRepoImp

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveNote()
        _binding = null
    }

    private fun saveNote() {
        val newNote: Note = Note(set_title.text.toString(), set_description.text.toString(), set_date.text.toString())
        if (!(set_title.text.toString() == "" && set_description.text.toString() == "")) {
            repository.create(newNote)
        }
    }

    companion object {
        const val ADD_NOTE: String  = "ADD_NOTE"

        fun newInstance(): AddNoteFragment {
            return AddNoteFragment()
        }
    }
}