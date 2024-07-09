package br.com.rodrigogurgel.archunitexample.presentation.dto.request

import java.util.UUID

data class UpdateTodoRequestDTO(
    val description: String,
    val done: Boolean,
)
