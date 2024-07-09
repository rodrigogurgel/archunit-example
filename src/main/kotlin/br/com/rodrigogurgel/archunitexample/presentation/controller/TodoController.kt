package br.com.rodrigogurgel.archunitexample.presentation.controller

import br.com.rodrigogurgel.archunitexample.business.service.TodoService
import br.com.rodrigogurgel.archunitexample.presentation.dto.request.CreateTodoRequestDTO
import br.com.rodrigogurgel.archunitexample.presentation.dto.request.UpdateTodoRequestDTO
import br.com.rodrigogurgel.archunitexample.presentation.dto.response.TodoResponseDTO
import br.com.rodrigogurgel.archunitexample.presentation.mapper.toModel
import br.com.rodrigogurgel.archunitexample.presentation.mapper.toResponseDTO
import java.util.UUID
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/todos")
class TodoController(
    private val todoService: TodoService
) {
    @PostMapping
    fun create(@RequestBody createTodoRequestDTO: CreateTodoRequestDTO): TodoResponseDTO {
        val todo = createTodoRequestDTO.toModel()
        todoService.create(todo)
        return todo.toResponseDTO()
    }

    @PutMapping("/{todoId}")
    fun update(
        @PathVariable todoId: UUID,
        @RequestBody updateTodoRequestDTO: UpdateTodoRequestDTO
    ): TodoResponseDTO {
        val todo = updateTodoRequestDTO.toModel(todoId)
        return todoService.update(todo).toResponseDTO()
    }

    @DeleteMapping("/{todoId}")
    fun delete(@PathVariable todoId: UUID) {
        todoService.delete(todoId)
    }

    @GetMapping("/{todoId}")
    fun findById(@PathVariable todoId: UUID): TodoResponseDTO {
        return todoService.findById(todoId).toResponseDTO()
    }

    @GetMapping
    fun findAll(): List<TodoResponseDTO> {
        return todoService.findAll().map { it.toResponseDTO() }
    }
}
