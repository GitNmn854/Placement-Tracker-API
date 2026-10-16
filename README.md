# Placement Application Tracker API

A REST API to track internship and job applications and their progress through each stage (applied, online assessment, interview, offer, rejected). Built with Spring Boot using a layered architecture, with a simple web dashboard on top.

## Tech Stack
- Java, Spring Boot
- Hibernate / JPA (EntityManager)
- MySQL
- Maven
- HTML, CSS, JavaScript (Fetch API) for the web dashboard

## Architecture
```
Web Dashboard (HTML/CSS/JS) -> Controller (rest) -> Service -> DAO -> MySQL
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
{
