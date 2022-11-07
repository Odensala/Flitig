package com.odensala.flitig.ui

import androidx.lifecycle.ViewModel
import com.odensala.flitig.data.TodoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor (
    private val todoDao: TodoDao
): ViewModel() {
}