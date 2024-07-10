package br.com.rodrigogurgel.archunitexample.business.model

import java.time.Instant
import java.util.UUID

class Todo(
    val id: UUID,
    val description: String,
    val done: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant
)
