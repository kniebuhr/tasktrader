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
class GetTaskListTest {

    @Mock
    private lateinit var repository: TaskRepository

    private val useCase by lazy { GetTaskList(repository) }

    @Test
    fun buildUseCase() {
        runBlocking {
            val taskList = listOf(Task(0L, "Teste", 100, true))

            whenever(repository.getListTask()).thenReturn(taskList)

            val result = useCase.execute(Unit)

            assert(result.getResult() == taskList)
            verify(repository).getListTask()
        }
    }

    @Test
    fun buildUseCaseError() {
        runBlocking {
            val throwable = RuntimeException("Teste")

            whenever(repository.getListTask()).thenThrow(throwable)

            val result = useCase.execute(Unit)

            assert(result.getException() == throwable)
            verify(repository).getListTask()
        }
    }

    @Test(expected = RuntimeException::class)
    fun buildUseCaseThrowsWhenGettingResultFromError() {
        runBlocking {
            val throwable = RuntimeException("Teste")

            whenever(repository.getListTask()).thenThrow(throwable)

            useCase.execute(Unit).getResult()

            verify(repository).getListTask()
        }
    }

    @Test(expected = RuntimeException::class)
    fun buildUseCaseThrowsWhenGettingErrorFromSuccess() {
        runBlocking {
            val taskList = listOf(Task(0L, "Teste", 100, true))

            whenever(repository.getListTask()).thenReturn(taskList)

            useCase.execute(Unit).getException()

            verify(repository).getListTask()
        }
    }
}