package ru.hivislav.simplenotes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.hivislav.simplenotes.R

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_notes_list_container ,NotesListFragment(), LIST_FRAGMENT)
                .commitNow()
        }


    }


    companion object{
        const val LIST_FRAGMENT = "LIST_FRAGMENT"
    }
}