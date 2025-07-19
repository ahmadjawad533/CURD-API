package com.awscrud;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Lambda function to handle user creation.
 */
public class CreateUserFunction implements RequestHandler<Map<String, Object>, String> {
    private final DynamoDbClient dynamoDbClient = DynamoDbClient.create();

    @Override
    public String handleRequest(Map<String, Object> event, Context context) {
        String userId = UUID.randomUUID().toString();
        String userName = (String) event.get("name");

        if (userName == null || userName.isBlank()) {
            return "Invalid input: 'name' is required.";
        }

        Map<String, AttributeValue> item = new HashMap<>();
        item.put("userId", AttributeValue.fromS(userId));
        item.put("name", AttributeValue.fromS(userName));

        dynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("Users")
                .item(item)
                .build());

        return "User created successfully with ID: " + userId;
    }
}
