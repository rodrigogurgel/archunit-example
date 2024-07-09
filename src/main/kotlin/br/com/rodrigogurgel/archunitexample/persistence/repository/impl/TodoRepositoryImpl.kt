package br.com.rodrigogurgel.archunitexample.persistence.repository.impl

import br.com.rodrigogurgel.archunitexample.persistence.data.TodoData
import br.com.rodrigogurgel.archunitexample.persistence.repository.TodoRepository
import java.util.UUID
import org.springframework.stereotype.Repository
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.Expression
import software.amazon.awssdk.enhanced.dynamodb.Key
import software.amazon.awssdk.enhanced.dynamodb.model.DeleteItemEnhancedRequest
import software.amazon.awssdk.enhanced.dynamodb.model.GetItemEnhancedRequest
import software.amazon.awssdk.enhanced.dynamodb.model.PutItemEnhancedRequest
import software.amazon.awssdk.enhanced.dynamodb.model.UpdateItemEnhancedRequest

@Repository
class TodoRepositoryImpl(
    private val todoTable: DynamoDbTable<TodoData>,
) : TodoRepository {
    private companion object {
        private val NOT_EXISTS_EXPRESSION = Expression.builder().expression("attribute_not_exists(todo_id)").build()
        private val EXISTS_EXPRESSION = Expression.builder().expression("attribute_exists(todo_id)").build()
    }

    override fun create(todo: TodoData) {
        val request = PutItemEnhancedRequest
            .builder(TodoData::class.java)
            .item(todo)
            .conditionExpression(NOT_EXISTS_EXPRESSION)
            .build()

        todoTable.putItem(request)
    }

    override fun update(todo: TodoData): TodoData {
        val request = UpdateItemEnhancedRequest
            .builder(TodoData::class.java)
            .item(todo.copy(createdAt = null))
            .ignoreNulls(true)
            .conditionExpression(EXISTS_EXPRESSION)
            .build()

        return todoTable.updateItem(request)
    }

    override fun delete(id: UUID) {
        val request = DeleteItemEnhancedRequest
            .builder()
            .key(
                Key.builder()
                    .partitionValue(id.toString())
                    .build()
            )
            .conditionExpression(EXISTS_EXPRESSION)
            .build()

        todoTable.deleteItem(request)
    }

    override fun findAll(): List<TodoData> {
        return todoTable.scan().items().toList()
    }

    override fun findById(id: UUID): TodoData {
        val request = GetItemEnhancedRequest
            .builder().key(
                Key.builder()
                    .partitionValue(id.toString())
                    .build()
            )
            .build()

        return todoTable.getItem(request)
    }
}
