package com.example.awscrud;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DeleteItemRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Lambda function to handle user deletion.
 */
public class DeleteUserFunction implements RequestHandler<Map<String, Object>, String> {
    private final DynamoDbClient dynamoDbClient = DynamoDbClient.create();

    @Override
    public String handleRequest(Map<String, Object> event, Context context) {
        String userId = (String) event.get("userId");

        if (userId == null || userId.isBlank()) {
            return "Invalid input: 'userId' is required.";
        }

        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userId", AttributeValue.fromS(userId));

        dynamoDbClient.deleteItem(DeleteItemRequest.builder()
                .tableName("Users")
                .key(key)
                .build());

        return "User deleted successfully.";
    }
}