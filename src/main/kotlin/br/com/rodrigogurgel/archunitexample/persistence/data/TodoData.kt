package br.com.rodrigogurgel.archunitexample.persistence.data

import java.time.Instant
import java.util.UUID
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey


@DynamoDbBean
data class TodoData(
    @get:DynamoDbPartitionKey
    @get:DynamoDbAttribute("todo_id")
    var id: UUID? = null,
    @get:DynamoDbAttribute("description")
    var description: String? = null,
    @get:DynamoDbAttribute("done")
    var done: Boolean? = null,
    @get:DynamoDbAttribute("created_at")
    var createdAt: Instant? = null,
    @get:DynamoDbAttribute("updated_at")
    var updatedAt: Instant? = null
)
