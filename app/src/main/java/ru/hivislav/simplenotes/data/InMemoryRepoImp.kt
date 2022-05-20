package ru.hivislav.simplenotes.data

object InMemoryRepoImp: Repository {

    private val notes: MutableList<Note> = ArrayList()
    private var counter: Int = 0

     private fun init() {
        create(Note("Title 1", "Description 1", "default"))
        create(Note("Title 2", "Description 2", "default"))
        create(Note("Title 3", "Description 3", "default"))
        create(Note("Title 4", "Description 4", "default"))
        create(Note("Title 5", "Description 5", "default"))
        create(Note("Title 6", "Description 6", "default"))
        create(Note("Title 7", "Description 7", "default"))
        create(Note("Title 8", "Description 8", "default"))
        create(Note("Title 9", "Description 9", "default"))
        create(Note("Title 10", "Description 10", "default"))
        create(Note("Title 11", "Description 11", "default"))
        create(Note("Title 12", "Description 12", "default"))
        create(Note("Title 13", "Description 13", "default"))
        create(Note("Title 14", "Description 14", "default"))
        create(Note("Title 15", "Description 15", "default"))
    }


    override fun create(note: Note): Int {
        val id: Int = counter++
        note.id = id
        notes.add(note)
        return id
    }

    override fun read(id: Int): Note {
        for (i in notes.indices) {
            if (notes[i].id == id)
                return notes[i]
        }
        return null!!
    }

    override fun update(note: Note) {
        for (i in notes.indices) {
            if (notes[i].id == note.id) {
                notes[i] = note
                break
            }
        }
    }

    override fun delete(id: Int) {
        for (i in notes.indices) {
            if (notes[i].id == id) {
                notes.removeAt(i)
                break
            }
        }
    }

    override fun getAll(): List<Note> {
        if (notes.isEmpty()) {
            init()
        }
        return notes
    }
}