package com.example.awscrud;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Lambda function to handle fetching user details.
 */
public class GetUserFunction implements RequestHandler<Map<String, Object>, Map<String, Object>> {
    private final DynamoDbClient dynamoDbClient = DynamoDbClient.create();

    @Override
    public Map<String, Object> handleRequest(Map<String, Object> event, Context context) {
        String userId = (String) event.get("userId");

        Map<String, Object> response = new HashMap<>();

        if (userId == null || userId.isBlank()) {
            response.put("error", "Invalid input: 'userId' is required.");
            return response;
        }

        Map<String, AttributeValue> key = new HashMap<>();
        key.put("userId", AttributeValue.fromS(userId));

        Map<String, AttributeValue> item = dynamoDbClient.getItem(GetItemRequest.builder()
                .tableName("Users")
                .key(key)
                .build()).item();

        if (item != null && !item.isEmpty()) {
            response.put("userId", item.get("userId").s());
            response.put("name", item.get("name").s());
        } else {
            response.put("message", "User not found.");
        }

        return response;
    }
}