package br.com.rodrigogurgel.archunitexample.persistence.config

import br.com.rodrigogurgel.archunitexample.persistence.data.TodoData
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable
import software.amazon.awssdk.enhanced.dynamodb.TableSchema
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.dynamodb.DynamoDbClient
import java.net.URI

@Configuration
class DynamoDBConfig {
    @Bean
    fun dynamoDbClient(): DynamoDbClient {
        return DynamoDbClient
            .builder()
            .endpointOverride(URI.create("http://localhost:8000"))
            .region(Region.SA_EAST_1)
            .credentialsProvider(
                StaticCredentialsProvider.create(
                    AwsBasicCredentials.create("DUMMYID", "DUMMYEXAMPLEKEY")
                )
            )
            .build()
    }

    @Bean
    fun enhancedClient(dynamoDbClient: DynamoDbClient): DynamoDbEnhancedClient {
        return DynamoDbEnhancedClient
            .builder()
            .dynamoDbClient(dynamoDbClient)
            .build()
    }

    @Bean
    fun todoTable(enhancedClient: DynamoDbEnhancedClient): DynamoDbTable<TodoData> {
        return enhancedClient.table("todo", TableSchema.fromBean(TodoData::class.java))
    }
}
