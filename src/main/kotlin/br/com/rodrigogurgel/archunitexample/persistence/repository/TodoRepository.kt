package br.com.rodrigogurgel.archunitexample.persistence.repository

import br.com.rodrigogurgel.archunitexample.persistence.data.TodoData
import java.util.UUID

interface TodoRepository {
    fun create(todo: TodoData)
    fun update(todo: TodoData): TodoData
    fun delete(id: UUID)
    fun findAll(): List<TodoData>
    fun findById(id: UUID): TodoData
}
