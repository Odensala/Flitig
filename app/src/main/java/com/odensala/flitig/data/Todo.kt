package com.odensala.flitig.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Todo(
    val name: String,
    val important: Boolean = false,
    val completed: Boolean = false,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}