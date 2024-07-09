package br.com.rodrigogurgel.archunitexample.presentation.dto.response

import java.time.Instant
import java.util.UUID

data class TodoResponseDTO(
    val id: UUID,
    val description: String,
    val done: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant
)
