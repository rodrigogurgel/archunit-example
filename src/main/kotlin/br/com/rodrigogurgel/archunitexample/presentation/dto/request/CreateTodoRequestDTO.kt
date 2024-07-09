package br.com.rodrigogurgel.archunitexample.presentation.dto.request

import java.util.UUID

data class CreateTodoRequestDTO(
    val id: UUID,
    val description: String,
)
