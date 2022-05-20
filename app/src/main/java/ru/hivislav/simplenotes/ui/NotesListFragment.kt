package ru.hivislav.simplenotes.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.hivislav.simplenotes.R
import ru.hivislav.simplenotes.data.InMemoryRepoImp
import ru.hivislav.simplenotes.data.Note
import ru.hivislav.simplenotes.data.Repository

class NotesListFragment : Fragment() {

    private val repository: Repository = InMemoryRepoImp

    private val notesAdapter = NotesAdapter(object: OnNoteClickListener {
        override fun onNoteClick(note: Note) {
            activity?.supportFragmentManager?.apply {
                val bundle = Bundle()
                bundle.putParcelable(EditNoteFragment.EDIT_NOTE, note)
                beginTransaction()
                    .replace(R.id.fragment_notes_list_container, EditNoteFragment.getInstance(bundle), EditNoteFragment.EDIT_NOTE)
                    .addToBackStack("")
                    .commit()
            }
        }
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        val notesListRecycler = view.findViewById<RecyclerView>(R.id.notes_list_recycler)

        notesAdapter.setNotes(repository.getAll())

        notesListRecycler.addItemDecoration(DividerItemDecoration(notesListRecycler.context, LinearLayoutManager.VERTICAL))
        notesListRecycler.adapter = notesAdapter
        notesListRecycler.layoutManager = LinearLayoutManager(notesListRecycler.context)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.notes_list_options_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.sort_notes -> Toast.makeText(requireActivity(), "Здесь будет сортировка по алфавиту", Toast.LENGTH_SHORT).show()
            R.id.search_notes -> Toast.makeText(requireActivity(), "Здесь будет поиск заметки", Toast.LENGTH_SHORT).show()
            R.id.add_notes -> activity?.supportFragmentManager?.apply {
                beginTransaction()
                .replace(R.id.fragment_notes_list_container, AddNoteFragment.newInstance(), AddNoteFragment.ADD_NOTE)
                .addToBackStack("")
                .commit()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    interface OnNoteClickListener{
        fun onNoteClick(note: Note)
    }

    companion object {
        const val LIST_FRAGMENT = "LIST_FRAGMENT"
    }
}