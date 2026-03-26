# Golf Club Tournament & Membership API

## Overview

This project is a **Spring Boot REST API** that manages:

- Golf club **Members**
- Golf **Tournaments**
- Relationships between members and tournaments

The system allows users to:

- Create, get, update, and delete members and tournaments
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

All endpoints were tested using Postman. Files were imported into postman using `api-docs.yml`.
A complete Postman collection is included:

```
/postman
```

### To use:

1. Open Postman
2. Import collection from `/postman` folder
3. Run requests directly


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
- Add/Delete member to tournament
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

Create a `.env` file using `.env-example` as a template

---

## AWS RDS Setup

### Steps Taken

- Created EC2 instance for testing connectivity
- Created MySQL RDS instance
- Configured DB credentials
- Updated Spring Boot datasource
- Allowed inbound access via security groups

---

### Application Configuration

Create `application.properties` file using `application-properties.example` as a template

---

## Issues Faced & Solutions

- AWS Connection refused
  - Fixed by allowing inbound access in security group

- Docker Hub OAUth authentication issues 
  - Fixed by creating a Personal Access Token and using it for authentication

- JSON infinite recursion  
  - Fixed using `@JsonIgnore`

- Issues Faced While Connecting to RDS
  - Initial connection issues were related to configuration and access setup.
  - After updating the datasource configuration and verifying connectivity through MySQL Workbench, the application connected successfully.

---

## Run Locally (Without Docker)

```
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

## Deliverables

- GitHub repository
- Postman screenshots
- Docker screenshot
- AWS RDS screenshots
- README documentation


---

## Swagger JSON

```
http://localhost:8080/v3/api-docs
```

---
## CI/CD with GitHub Actions

A GitHub Action is included:

```
.github/workflows/maven.yml
```

### What it does:

- Builds the project
- Runs tests
- Runs **only when code is pushed to main (after merge)**

## Optional CI/CD

A GitHub Actions workflow is included to:

- build the Docker image
- push the image to Docker Hub
- run only when code is merged into the main branch
- Secrets used for Docker Hub authentication are stored securely in GitHub Secrets

Workflow file:
- `.github/workflows/docker-publish.yml`

---

## How I Connected the Application to AWS RDS

As part of the deployment preparation, I moved the application database from a local setup to **AWS MySQL RDS**.

### Steps Taken

1. Created an **EC2 instance** for AWS deployment testing.

2. Created a **MySQL RDS instance**.

3. Retrieved the required database connection details from RDS:
   - endpoint
   - username
   - password

4. Added these values to the project configuration files:
   - `application.properties`
   - `.env`

5. Updated the Spring Boot datasource configuration so the application would connect to the RDS instance instead of the local database.

6. Verified the connection by creating a new connection in **MySQL Workbench** using the RDS endpoint and credentials.

7. Built and pushed the Docker image for deployment using:

```
docker buildx build --platform linux/arm64,linux/amd64 -t <image_name> --push .
```

8. Ran the application against the RDS-connected configuration, and the database was successfully populated with data.

---

## Author

Developed by **Abiodun Magret Oyedele**