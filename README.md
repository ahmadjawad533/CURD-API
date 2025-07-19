# 🛠️ Java CRUD REST API with AWS Lambda, API Gateway & DynamoDB

A serverless CRUD API built with **Java 17**, **AWS Lambda**, **API Gateway**, and **DynamoDB**.  
This project is designed for learning AWS Serverless with Java and is part of my AWS Community Builder content journey.

---

## 📦 Features
- ✅ Create User
- ✅ Read/Get User Details
- ✅ Update User Info
- ✅ Delete User
- ✅ Serverless architecture with DynamoDB

---

## 📝 Project Structure

| File | Purpose |
|---|---|
| `CreateUserFunction.java` | Handles user creation |
| `GetUserFunction.java` | Retrieves user details |
| `UpdateUserFunction.java` | Updates user data |
| `DeleteUserFunction.java` | Deletes a user |

---

## 🛠️ Tech Stack & Tools
- Java 17 (AWS Lambda Runtime)
- AWS SDK for Java v2
- AWS Lambda (Java Handler)
- Amazon API Gateway (HTTP API)
- Amazon DynamoDB (NoSQL)
- (Optional) AWS SAM for Deployment
- Maven for build

---

## 🚀 How to Deploy
1. **Create DynamoDB Table**  
   Table Name: `Users`  
   Partition Key: `userId` (String)

2. **Package & Deploy with AWS SAM (Optional)**  
   ```bash
   sam build
   sam deploy --guided
API Gateway Routes Example
| Method | Endpoint | Lambda Function |
|---|---|---|
| POST | /user | CreateUserFunction |
| GET | /user/{userId} | GetUserFunction |
| PUT | /user | UpdateUserFunction |
| DELETE | /user | DeleteUserFunction |

🧑‍💻 How to Use
✅ Use Postman or CURL to call the API endpoints

✅ Example POST /user body:

json
Copy
Edit
{
  "name": "John Doe"
}
📖 License
MIT License.
Feel free to fork and contribute!


---
