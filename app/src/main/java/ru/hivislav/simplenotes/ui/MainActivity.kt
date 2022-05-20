package ru.hivislav.simplenotes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.toolbar_main.*
import ru.hivislav.simplenotes.R
import ru.hivislav.simplenotes.databinding.ActivityMainBinding
import ru.hivislav.simplenotes.ui.interfaces.DatePickerListener

class MainActivity : AppCompatActivity(), DatePickerListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initToolbar()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_notes_list_container ,NotesListFragment(), NotesListFragment.LIST_FRAGMENT)
                .commitNow()
        }
    }

    private fun initToolbar() {
        val toolbar: Toolbar = toolbar_main
        setSupportActionBar(toolbar)
    }

    override fun callDatePicker() {
        DatePickerFragment().show(supportFragmentManager, DatePickerFragment.DATE_PICKER)
    }

    override fun sendDatePicker(date: String) {
        if (supportFragmentManager.findFragmentByTag(AddNoteFragment.ADD_NOTE) != null) {
            val addNoteFragment =
                supportFragmentManager.findFragmentByTag(AddNoteFragment.ADD_NOTE) as AddNoteFragment
            addNoteFragment.setDate(date)
        }
        if (supportFragmentManager.findFragmentByTag(EditNoteFragment.EDIT_NOTE) != null) {
            val editNoteFragment =
                supportFragmentManager.findFragmentByTag(EditNoteFragment.EDIT_NOTE) as EditNoteFragment
            editNoteFragment.setDate(date)
        }
    }

    companion object{
    }
}