package com.odensala.flitig.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.odensala.flitig.data.TodoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    todoDao: TodoDao
) : ViewModel() {
    val searchQuery = MutableStateFlow("")
    private val todoFlow = searchQuery.flatMapLatest {
        todoDao.getTodos(it)
    }
    val todo = todoFlow.asLiveData()
}