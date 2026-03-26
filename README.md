# Golf Club Tournament & Membership API

## Overview

This project is a **Spring Boot REST API** that manages:

- Golf club **Members**
- Golf **Tournaments**
- Relationships between members and tournaments

The system allows users to:

- Create, update, and delete members and tournaments
- Add and remove members from tournaments
- Perform search operations on members and tournaments
- Run the application using **Docker**
- Connect the application to **AWS RDS**

---

## Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate ORM)
- MySQL
- Docker & Docker Compose
- Maven

---

## Project Structure

```
org.codewithmagret.rest
├── member
│   ├── Member.java
│   ├── MemberRepository.java
│   ├── MemberService.java
│   └── MemberController.java
├── tournament
│   ├── Tournament.java
│   ├── TournamentRepository.java
│   ├── TournamentService.java
│   └── TournamentController.java
```

---

## Data Model

### Member

- id (auto-generated)
- name
- address
- email
- membershipType
- phoneNumber
- membershipStartDate
- membershipDuration

---

### Tournament

- id (auto-generated)
- startDate
- endDate
- location
- entryFee
- prize

---

### Relationship

- Many-to-Many between Members and Tournaments
- Join table: `member_tournament`

---

## API Endpoints

### Members

- POST `/members`
- GET `/members`
- GET `/members/{id}`
- PUT `/members/{id}`
- DELETE `/members/{id}`

---

### Member Search

- GET `/members/search/name?name=`
- GET `/members/search/type?type=`
- GET `/members/search/phone?phone=`
- GET `/members/search/tournament-date?startDate=`

---

### Tournaments

- POST `/tournaments`
- GET `/tournaments`
- GET `/tournaments/{id}`
- PUT `/tournaments/{id}`
- DELETE `/tournaments/{id}`

---

### Tournament Search

- GET `/tournaments/search/date?startDate=`
- GET `/tournaments/search/location?location=`
- GET `/tournaments/search/member/{memberId}`

---

### Relationship Endpoints

- POST `/tournaments/{tournamentId}/members/{memberId}`
- DELETE `/tournaments/{tournamentId}/members/{memberId}`

---

## Business Rules

- A tournament **cannot be created** if one already exists with the same:
  - startDate
  - location

- Enforced using:
  - Service-level validation
  - Database unique constraint

---

## API Testing (Postman)

All endpoints were tested using Postman.

---

## Screenshots

Screenshots are organized in the project:

```
/screenshots
├── postman
├── docker
├── aws
```

### Includes:

- Member CRUD operations
- Tournament CRUD operations
- Search operations
- Add member to tournament
- Docker running containers
- AWS RDS setup and connection

---

## Swagger Documentation

After starting the application:

```
http://localhost:8080/swagger
```

---

## API Documentation

Full Javadoc documentation:
```
https://magret1730.github.io/golfClubTournament/
```

---

## Running with Docker

### Step 1: Run the application

```
docker compose up --build
```

### Step 2: Access API

```
http://localhost:8080
```

---

## Docker Notes

- The project includes both:
  - Application container
  - Database container

- No manual database setup is required

- Another developer can run the project with **one command**

---

## Environment Variables

Create a `.env` file (not committed):

```env
SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/golf_db
SPRING_DATASOURCE_USERNAME=golf_user
SPRING_DATASOURCE_PASSWORD=golf_pass
```

---

## AWS RDS Setup

### Steps Taken

- Created RDS instance
- Configured DB credentials
- Updated Spring Boot datasource
- Allowed inbound access via security groups

---

### Example Configuration

```properties
spring.datasource.url=jdbc:mysql://<RDS-ENDPOINT>:3306/golf_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

## Issues Faced & Solutions

- Docker could not connect to DB  
  → Fixed by using service name (`db`) instead of `localhost`

- AWS RDS connection timeout  
  → Fixed by updating security group inbound rules

- JSON infinite recursion  
  → Fixed using `@JsonIgnore`

- Duplicate tournaments  
  → Fixed using `existsByStartDateAndLocation`

---

## Run Locally (Without Docker)

```bash
git clone <your-repo-url>
cd <project-folder>
./mvnw spring-boot:run
```

---

## Notes

- Dates use ISO format: `YYYY-MM-DD`
- Many-to-many relationship handled properly
- Clean layered architecture used (Controller → Service → Repository)

---

## Optional (CI/CD)

GitHub Action can be added to:

- Build Docker image
- Push to Docker Hub on merge

---

## Deliverables

- GitHub repository
- Postman screenshots
- Docker screenshot
- AWS RDS screenshots
- README documentation

---

## Author

Developed by **Abiodun Magret Oyedele**

---

## Swagger JSON

```
http://localhost:8080/v3/api-docs
```

## Optional CI/CD

A GitHub Actions workflow is included to:

- build the Docker image
- push the image to Docker Hub
- run only when code is merged into the main branch

Workflow file:
- `.github/workflows/docker-publish.yml`