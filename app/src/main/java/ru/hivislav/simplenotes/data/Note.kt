package ru.hivislav.simplenotes.data



class Note(var id: Int, var title: String, var description: String, var date: String = "default") {

    constructor(title: String, description: String, date: String): this(0 , title, description, date)

    companion object{
        const val NOTE: String  = "NOTE"
    }
}