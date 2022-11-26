package com.odensala.flitig.data

import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@OptIn(ExperimentalCoroutinesApi::class)
@SmallTest
@HiltAndroidTest
class TodoDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    /*@get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()*/

    @Inject
    @Named("test_database")
    lateinit var database: TodoDatabase
    private lateinit var dao: TodoDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.todoDao()
    }

    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun insert_whenTodoIsInserted_todoIsInDatabase() = runTest {
        val todo = Todo("Buy 10 bags of chips", id = 1)
        dao.insert(todo)

        dao.getTodos("").test {
            val listOfTodos = awaitItem()
            assertThat(listOfTodos).contains(todo)
            cancel()
        }
    }

    @Test
    fun delete_whenTodoIsDeleted_todoIsNotInDatabase() = runTest {
        val todo = Todo("Buy 10 bags of chips", id = 1)
        dao.insert(todo)
        dao.delete(todo)

        dao.getTodos("").test {
            val listOfTodos = awaitItem()
            assertThat(listOfTodos).doesNotContain(todo)
            cancel()
        }
    }

    @Test
    fun update_whenTodoNameIsUpdated_updateTodoName() = runTest {
        val todo = Todo("Buy 10 bags of chips", id = 1)
        dao.insert(todo)

        val updatedTodo = Todo("Buy 20 bags of chips", id = 1)
        dao.update(updatedTodo)

        dao.getTodos("").test {
            val listOfTodos = awaitItem()
            assertThat(listOfTodos).contains(updatedTodo)
            cancel()
        }
    }
}