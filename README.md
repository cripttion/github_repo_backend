# GitHub Repository Searcher - Spring Boot Project

A backend application built using Spring Boot that enables searching GitHub repositories via GitHub REST APIs, stores results in PostgreSQL, and provides REST endpoints to query stored data.

---

## 🚀 Features

- Search GitHub repositories by keyword, language, and sorting order
- Store and update repository details in PostgreSQL
- RESTful endpoints for data retrieval
- Exception handling and validation
- Postman & cURL-ready testing support

---

## 🛠 Prerequisites

Ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.3+**
- **PostgreSQL 12+**
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code)
- **Postman** (Api testing)

---

## ⚙️ Installation & Configuration

### 1. Clone the repository
```bash
git clone https://github.com/cripttion/github_repo_backend.git
cd github_repo_backend
```

### 2. Configure your database & GitHub API
Edit `src/main/resources/application.properties`:
```properties
# Server port
server.port=8080

# PostgreSQL Database
#jdbc:postgresql://localhost:5432/github_db
spring.datasource.url= <add your database URL here>
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```

---

## ▶️ Run the Application

### Option 1: Via Maven
```bash
mvn spring-boot:run
```

### Option 2: Build & Run JAR
```bash
mvn clean package
java -jar target/github-repo-searcher-1.0.0.jar
```

---

## 📌 API Endpoints

### Base URL
```
http://localhost:8080/api/github
```

### 1. Search & Store Repositories
**POST** `/search`
```json
Request Body:
{
  "query": "spring boot",
  "language": "Java",
  "sort": "stars"
}
```
```bash
curl -X POST http://localhost:8080/api/github/search \
  -H "Content-Type: application/json" \
  -d '{
    "query": "spring boot",
    "language": "Java",
    "sort": "stars"
  }'
```

### 2. Retrieve Stored Repositories
**GET** `/repositories`
```bash
curl -X GET "http://localhost:8080/api/github/repositories?language=Java&minStars=100&sort=forks"
```
Query Parameters:
- `language` (optional): Filter by language
- `minStars` (optional): Minimum star count
- `sort` (optional): Sort by `stars`, `forks`, or `updated`

---

## 🧪 Manual Testing with Postman

You can test the API using Postman:
1. Create a new collection with base URL: `http://localhost:8080/api/github`
2. Add the two endpoints: `POST /search`, `GET /repositories`
3. Send test requests with appropriate JSON bodies or query parameters

---



---

## ✅ Project Status
Working and tested. Includes exception handling, and clean layered architecture.
Watch the demo video here:  
👉 [GitHub Repo Searcher - Demo click to watch the working video](https://drive.google.com/file/d/1kt_ECh52hJUPu7m2B2ly-j1UBCewQsvv/view?usp=sharing)
---

**By Pulak Raj (cripttion)** ☁️
