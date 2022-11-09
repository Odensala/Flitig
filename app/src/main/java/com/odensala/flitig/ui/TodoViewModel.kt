package com.odensala.flitig.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.odensala.flitig.data.TodoDao
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    todoDao: TodoDao
) : ViewModel() {
    val todos = todoDao.getTodos().asLiveData()
}