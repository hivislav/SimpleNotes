package ru.hivislav.simplenotes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.hivislav.simplenotes.R
import ru.hivislav.simplenotes.data.Note

class NotesAdapter(private var onNoteClickListener: NotesListFragment.OnNoteClickListener?):
                                                RecyclerView.Adapter<NotesAdapter.NoteHolder>() {
    private var notes: List<Note> = listOf()

    fun setNotes(notes: List<Note>){
        this.notes = notes
        notifyDataSetChanged()
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note) {
            itemView.apply {
                findViewById<TextView>(R.id.note_title).text = note.title
                findViewById<TextView>(R.id.note_description).text = note.description
                findViewById<TextView>(R.id.note_date).text = note.date

                setOnClickListener {
                    onNoteClickListener?.onNoteClick(note)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val context = parent.context
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.note_item, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(notes[position])
    }

    override fun getItemCount(): Int {
        return notes.size
    }
}

