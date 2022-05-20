package ru.hivislav.simplenotes.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_edit_note.*
import ru.hivislav.simplenotes.R
import ru.hivislav.simplenotes.data.InMemoryRepoImp
import ru.hivislav.simplenotes.data.Note
import ru.hivislav.simplenotes.data.Repository
import ru.hivislav.simplenotes.databinding.FragmentEditNoteBinding
import ru.hivislav.simplenotes.ui.interfaces.DatePickerListener
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddNoteFragment : Fragment() {

    private var date = ""

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

        setHasOptionsMenu(true)

        if (savedInstanceState == null) {
            val currentDate = Date()
            // Форматирование времени как "день.месяц.год"
            val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            date = dateFormat.format(currentDate)
            set_date.text = date
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
        val newNote = Note(set_title.text.toString(), set_description.text.toString(), set_date.text.toString())
        if (!(set_title.text.toString() == "" && set_description.text.toString() == "")) {
            repository.create(newNote)
        }
    }

    fun setDate(date: String) {
        set_date.text = date
    }

    companion object {
        const val ADD_NOTE: String  = "ADD_NOTE"

        fun newInstance(): AddNoteFragment {
            return AddNoteFragment()
        }
    }
}