package ru.hivislav.simplenotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.hivislav.simplenotes.R
import ru.hivislav.simplenotes.data.InMemoryRepoImp
import ru.hivislav.simplenotes.data.Repository

class NotesListFragment : Fragment() {
    private val repository: Repository = InMemoryRepoImp


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val notesListRecycler = view.findViewById<RecyclerView>(R.id.notes_list_recycler)
        val notesAdapter = NotesAdapter()


        notesAdapter.setNotes(repository.getAll())

        notesListRecycler.addItemDecoration(DividerItemDecoration(notesListRecycler.context, LinearLayoutManager.VERTICAL))
        notesListRecycler.adapter = notesAdapter
        notesListRecycler.layoutManager = LinearLayoutManager(notesListRecycler.context)
    }

    companion object {
    }
}