# Streakify

Streakify is a Spring Boot REST API for tracking habits, daily completion logs, and streak progress.

## Features

- User management
- Habit creation and listing by user
- Habit log creation and updates by date
- Streak calculation per habit
- Dashboard summary per user
- Request validation and centralized error handling

## Tech Stack

- Java 17
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Bean Validation (Jakarta Validation)
- PostgreSQL
- Lombok
- Maven

## Project Structure

```text
src/main/java/com/streak/Streakify1/
  controller/    # REST controllers
  DTO/           # Request and response DTOs
  entity/        # JPA entities
  repository/    # Spring Data repositories
  service/       # Business logic
  exception/     # Custom exceptions and global handler
```

## Prerequisites

- JDK 17 installed
- PostgreSQL installed and running
- Maven (or use the included Maven Wrapper)

## Configuration

Current application config is in src/main/resources/application.properties:

- app name: `Streakify`
- server port: `8080`
- datasource URL: `jdbc:postgresql://localhost:5000/streakify_db`
- datasource username: `<your_db_username>`
- datasource password: `<your_db_password>`
- hibernate ddl-auto: `update`

Do not commit real credentials to GitHub. Keep secrets in local-only config or environment variables.

Supported environment variables:

- `DB_URL`
- `DB_USERNAME`
- `DB_PASSWORD`

## Run Locally

### 1) Create database

Use PostgreSQL and create the database:

```sql
CREATE DATABASE streakify_db;
```

### 2) Run the app

On Windows:

```bash
mvnw.cmd spring-boot:run
```

On macOS/Linux:

```bash
./mvnw spring-boot:run
```

App base URL:

```text
http://localhost:8080
```

## API Endpoints

### Users

- `POST /users` - Create user
- `GET /users/{id}` - Get user by id
- `DELETE /users/{id}` - Delete user
- `GET /users/{userId}/dashboard` - Get dashboard for user

### Habits

- `POST /habits` - Create habit
- `GET /users/{userId}/habits` - List habits for a user
- `DELETE /habits/{id}` - Delete habit
- `GET /habits/{habitId}/streak` - Get streak details for a habit

### Habit Logs

- `POST /habits/{habitId}/logs` - Create habit log
- `PUT /habits/{habitId}/logs/{date}` - Update habit log by date
- `GET /habits/{habitId}/logs` - Get all logs for a habit

## Preview

![Streakify Dashboard Preview](image/img.png)

## Sample Request Payloads

Create user:

```json
{
  "name": "Hency",
  "email": "hency@example.com"
}
```

Create habit:

```json
{
  "name": "Read",
  "targetDaysPerWeek": 5,
  "userId": 1
}
```

Create or update habit log:

```json
{
  "logDate": "2026-03-17",
  "completed": true
}
```

## Testing

Run tests with Maven Wrapper:

```bash
mvnw.cmd test
```

## Notes

- The app uses automatic schema updates (`spring.jpa.hibernate.ddl-auto=update`).
- Validation errors and not-found scenarios are handled through custom exception classes.

## License

No license file is currently defined. Add a LICENSE file if you plan to open-source this project.