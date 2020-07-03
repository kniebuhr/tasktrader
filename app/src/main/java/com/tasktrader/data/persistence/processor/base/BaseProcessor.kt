package com.tasktrader.data.persistence.processor.base

import com.tasktrader.data.persistence.dao.base.BaseDao

abstract class BaseProcessor<T>(private val baseDao: BaseDao<T>) {

    suspend fun insert(entity: T): Boolean = baseDao.insert(entity) > 0

    suspend fun delete(entity: T): Boolean = baseDao.delete(entity) > 0

}