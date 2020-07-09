package com.tasktrader.data.mapper

import com.tasktrader.data.persistence.entity.TaskEntity
import com.tasktrader.domain.model.Task
import javax.inject.Inject

class TaskMapper @Inject constructor() {

    //region Entity to Model

    /**
     * Transform a [TaskEntity] into a [Task]
     * @param entity Object to be transformed
     * @return [Task]
     */
    fun transformToModel(entity: TaskEntity): Task {
        return Task(
            id = entity.id,
            name = entity.name,
            completed = entity.completed
        )
    }
    /**
     * Transform a [TaskEntity] collection into a list of [Task]
     * @param entityCollection Object collection to be transformed
     * @return [Task] list
     */
    fun transformToModel(entityCollection: Collection<TaskEntity>): List<Task> {
        return entityCollection.map { transformToModel(it) }
    }

    //endregion

    //region Model to Entity

    /**
     * Transform a [Task] into a [TaskEntity]
     * @param model Object to be transformed
     * @return [TaskEntity]
     */
    fun transformToEntity(model: Task): TaskEntity {
        return TaskEntity(
            id = model.id,
            name = model.name,
            completed = model.completed
        )
    }
    /**
     * Transform a [Task] collection into a list of [TaskEntity]
     * @param modelCollection Object collection to be transformed
     * @return [TaskEntity] list
     */
    fun transformToEntity(modelCollection: Collection<Task>): List<TaskEntity> {
        return modelCollection.map { transformToEntity(it) }
    }

    //endregion


}