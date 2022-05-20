package ru.hivislav.simplenotes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.toolbar_main.*
import ru.hivislav.simplenotes.R
import ru.hivislav.simplenotes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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

    companion object{
    }
}