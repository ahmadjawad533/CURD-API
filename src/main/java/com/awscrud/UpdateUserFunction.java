package com.example.awscrud;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.UpdateItemRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Lambda function to handle user updates.
 */
public class UpdateUserFunction implements RequestHandler<Map<String, Object>, String> {
    private final DynamoDbClient dynamoDbClient = DynamoDbClient.create();

    @Override
    public String handleRequest(Map<String, Object> event, Context context) {
        String userId = (String) event.get("userId");
        String newName = (String) event.get("name");

        if (userId == null || userId.isBlank() || newName == null || newName.isBlank()) {
            return "Invalid input: Both 'userId' and 'name' are required.";
        }

        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userId", AttributeValue.fromS(userId));

        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":n", AttributeValue.fromS(newName));

        dynamoDbClient.updateItem(UpdateItemRequest.builder()
                .tableName("Users")
                .key(key)
                .updateExpression("SET #name = :n")
                .expressionAttributeNames(Map.of("#name", "name"))
                .expressionAttributeValues(expressionValues)
                .build());

        return "User updated successfully.";
    }
}
