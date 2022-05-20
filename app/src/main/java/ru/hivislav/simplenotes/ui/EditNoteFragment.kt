package ru.hivislav.simplenotes.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_edit_note.*
import ru.hivislav.simplenotes.R
import ru.hivislav.simplenotes.data.InMemoryRepoImp
import ru.hivislav.simplenotes.data.Note
import ru.hivislav.simplenotes.data.Repository
import ru.hivislav.simplenotes.databinding.FragmentEditNoteBinding
import ru.hivislav.simplenotes.ui.interfaces.DatePickerListener

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

        setHasOptionsMenu(true)

        val note = arguments?.getParcelable<Note>(EDIT_NOTE)

        note?.also {
            set_title.setText(it.title)
            set_description.setText(it.description)
            set_date.text = it.date
            noteId = note.id
        }

        set_date.setOnClickListener { (requireActivity() as DatePickerListener).callDatePicker() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        saveNote()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_note_options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.share_note -> Toast.makeText(requireActivity(), "Здесь будет возможность поделиться", Toast.LENGTH_SHORT).show()
            R.id.add_multimedia_note -> Toast.makeText(requireActivity(), "Здесь будет добавление файла", Toast.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun saveNote() {
        val editNote = Note(noteId, set_title.text.toString(), set_description.text.toString(), set_date.text.toString())
        repository.update(editNote)
    }

    fun setDate(date: String) {
        set_date.text = date
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