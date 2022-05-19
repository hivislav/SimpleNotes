package ru.hivislav.simplenotes.data

interface Repository {
    fun create(note: Note): Int
    fun read(id: Int): Note
    fun update(note: Note)
    fun delete(id: Int)
    fun getAll(): List<Note>
}