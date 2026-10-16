# Placement Application Tracker API

A REST API to track internship and job applications and their progress through each stage (applied, online assessment, interview, offer, rejected). Built with Spring Boot using a layered architecture.

## Tech Stack
- Java, Spring Boot
- Hibernate / JPA (EntityManager)
- MySQL
- Maven

## Architecture
```
Controller (rest)  ->  Service  ->  DAO  ->  MySQL
        \
         GlobalExceptionHandler (@ControllerAdvice)
```
- **rest**: REST controller exposing the endpoints
- **service**: business logic and transaction handling (`@Transactional`)
- **dao**: database access using JPA `EntityManager`
- **entity**: `JobApplication` entity and `JobStatus` enum
- **exception**: custom not-found exception and a global handler returning JSON errors

## Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/applications` | List all applications (newest first) |
| GET | `/api/applications/{id}` | Get one application |
| POST | `/api/applications` | Create an application |
| PUT | `/api/applications` | Update an application |
| DELETE | `/api/applications/{id}` | Delete an application |

Status values: `APPLIED`, `OA`, `INTERVIEW`, `OFFER`, `REJECTED`. A new application defaults to `APPLIED` with today's date.

### Example request
```json
POST /api/applications
{ "company": "Accenture", "jobTitle": "Associate Intern", "notes": "OA next week" }
```

### Example error (unknown id)
```json
{ "status": 404, "message": "Application id not found - 999", "timestamp": 1792149542871 }
```

## How to Run
1. Create the database and a user in MySQL:
```sql
   CREATE DATABASE placement_tracker;
   CREATE USER 'trackeruser'@'localhost' IDENTIFIED BY 'your_password_here';
   GRANT ALL PRIVILEGES ON placement_tracker.* TO 'trackeruser'@'localhost';
```
2. Copy `src/main/resources/application.properties.example` to `application.properties` and set your password.
3. Run the app: `./mvnw spring-boot:run`
4. The API is available at `http://localhost:8080/api/applications`.
