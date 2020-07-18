package com.tasktrader.domain.usecases

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.tasktrader.domain.model.Task
import com.tasktrader.domain.repository.TaskRepository
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetTaskTest {

    @Mock
    private lateinit var repository: TaskRepository

    private val useCase by lazy { GetTask(repository) }

    @Test
    fun buildUseCase() {
        runBlocking {
            val taskId = 1L
            val task = Task(taskId, "Nome", 10, false)
            whenever(repository.getTask(taskId)).thenReturn(task)

            val result = useCase.execute(taskId)

            assert(result.getResult() == task)
            verify(repository).getTask(taskId)
        }
    }
}