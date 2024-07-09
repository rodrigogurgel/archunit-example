package br.com.rodrigogurgel.archunitexample.business.service

import br.com.rodrigogurgel.archunitexample.business.model.Todo
import java.util.UUID

interface TodoService {
    fun create(todo: Todo)
    fun update(todo: Todo): Todo
    fun delete(id: UUID)
    fun findById(id: UUID): Todo
    fun findAll(): List<Todo>
}
