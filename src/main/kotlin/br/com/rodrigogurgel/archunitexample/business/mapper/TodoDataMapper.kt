package br.com.rodrigogurgel.archunitexample.business.mapper

import br.com.rodrigogurgel.archunitexample.business.model.Todo
import br.com.rodrigogurgel.archunitexample.persistence.data.TodoData

fun Todo.toData(): TodoData {
    return TodoData(id, description, done, createdAt, updatedAt)
}

fun TodoData.toModel(): Todo {
    return Todo(id!!, description!!, done!!, createdAt!!, updatedAt!!)
}
