package br.com.rodrigogurgel.archunitexample.business.service.impl

import br.com.rodrigogurgel.archunitexample.business.model.Todo
import br.com.rodrigogurgel.archunitexample.business.service.TodoService
import br.com.rodrigogurgel.archunitexample.business.mapper.toData
import br.com.rodrigogurgel.archunitexample.business.mapper.toModel
import br.com.rodrigogurgel.archunitexample.persistence.repository.TodoRepository
import java.util.UUID
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository
) : TodoService {
    override fun create(todo: Todo) {
        todoRepository.create(todo.toData())
    }

    override fun update(todo: Todo): Todo {
        return todoRepository.update(todo.toData()).toModel()
    }

    override fun delete(id: UUID) {
        todoRepository.delete(id)
    }

    override fun findById(id: UUID): Todo {
        return todoRepository.findById(id).toModel()
    }

    override fun findAll(): List<Todo> {
        return todoRepository.findAll().map { todo -> todo.toModel() }
    }
}
