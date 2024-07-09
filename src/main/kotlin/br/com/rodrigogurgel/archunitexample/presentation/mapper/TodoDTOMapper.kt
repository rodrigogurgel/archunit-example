package br.com.rodrigogurgel.archunitexample.presentation.mapper

import br.com.rodrigogurgel.archunitexample.business.model.Todo
import br.com.rodrigogurgel.archunitexample.presentation.dto.request.CreateTodoRequestDTO
import br.com.rodrigogurgel.archunitexample.presentation.dto.request.UpdateTodoRequestDTO
import br.com.rodrigogurgel.archunitexample.presentation.dto.response.TodoResponseDTO
import java.time.Instant
import java.util.UUID

fun CreateTodoRequestDTO.toModel(): Todo {
    return Todo(id, description, false, Instant.now(), Instant.now())
}

fun UpdateTodoRequestDTO.toModel(id: UUID): Todo {
    return Todo(id, description, done, Instant.now(), Instant.now())
}

fun Todo.toResponseDTO(): TodoResponseDTO {
    return TodoResponseDTO(id, description, done, createdAt, updatedAt)
}
