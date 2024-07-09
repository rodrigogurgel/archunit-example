package br.com.rodrigogurgel.archunitexample.business.model

import java.time.Instant
import java.util.UUID

class Todo(
    val id: UUID,
    description: String,
    done: Boolean,
    val createdAt: Instant,
    updatedAt: Instant
) {
    var updatedAt: Instant = updatedAt
        private set

    var done: Boolean = done
        set(value) {
            this.updatedAt = Instant.now()
            field = value
        }

    var description: String = description
        set(value) {
            field = value
            check(description.isNotBlank()) { "TODO description cannot be blank" }
            check(done) { "TODO done can't have description updated" }
            updatedAt = Instant.now()
        }
}
