package ru.hivislav.simplenotes.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(var id: Int, var title: String, var description: String, var date: String = "default"): Parcelable {

    constructor(title: String, description: String, date: String): this(0 , title, description, date)

    companion object{
        const val NOTE: String  = "NOTE"
    }
}