# 🏌️ Golf Club Tournament & Membership API

## 📌 Overview

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

## 🧱 Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate ORM)
- MySQL / PostgreSQL
- Docker & Docker Compose
- Maven

---

## 🗂️ Project Structure

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

## 🧩 Data Model

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

## 🚀 API Endpoints

### 🔹 Members

- POST `/members` → Create member
- GET `/members` → Get all members
- GET `/members/{id}` → Get member by ID
- PUT `/members/{id}` → Update member
- DELETE `/members/{id}` → Delete member

---

### 🔹 Member Search

- GET `/members/search/name?name=` → Search by name
- GET `/members/search/type?type=` → Search by membership type
- GET `/members/search/phone?phone=` → Search by phone number
- GET `/members/search/tournament-date?startDate=` → Search by tournament start date

---

### 🔹 Tournaments

- POST `/tournaments` → Create tournament
- GET `/tournaments` → Get all tournaments
- GET `/tournaments/{id}` → Get tournament by ID
- PUT `/tournaments/{id}` → Update tournament
- DELETE `/tournaments/{id}` → Delete tournament

---

### 🔹 Tournament Search

- GET `/tournaments/search/date?startDate=` → Search by start date
- GET `/tournaments/search/location?location=` → Search by location
- GET `/tournaments/search/member/{memberId}` → Search by member

---

### 🔹 Relationship Endpoints

- POST `/tournaments/{tournamentId}/members/{memberId}` → Add member to tournament
- DELETE `/tournaments/{tournamentId}/members/{memberId}` → Remove member from tournament

---

## ⚠️ Business Rules

- A tournament **cannot be created** if one already exists with the same:
    - startDate
    - location

- This is enforced using:
    - Service-level validation
    - Database-level unique constraint

---

## API Testing (Postman)

All endpoints were tested using Postman.

### Required Screenshots (included in submission)

- Create Member
- Get Members
- Create Tournament
- Add Member to Tournament
- Search Members
- Search Tournaments
- Update Member
- Update Tournament
- Delete Member
- Delete Tournament

---

# Swagger Documentation

After starting the application:

```
http://localhost:8080/swagger
```

---

## API Documentation

Full Javadoc documentation can be found here:

[View Javadoc Documentation](https://magret1730.github.io/golfClubTournament/)

---

## Running with Docker

### Step 1: Build and Run

```bash
docker-compose up --build
```

### Step 2: Access API

```
http://localhost:8080
```

---

## Example docker-compose.yml

```
version: "3.8"

services:
  db:
    image: mysql:8
    container_name: golf-db
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: golf_db
    ports:
      - "3306:3306"

  app:
    build: .
    container_name: golf-api
    depends_on:
      - db
    ports:
      - "8080:8080"
```

---

## AWS RDS Setup

### Steps Taken

- Created RDS instance (MySQL/PostgreSQL)
- Configured database name, username, and password
- Updated Spring Boot configuration
- Allowed inbound access via security groups

---

### Example Configuration

```properties
spring.datasource.url=jdbc:mysql://<RDS-ENDPOINT>:3306/golf_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

---

## ⚠️ Issues Faced

- Connection timeout due to security group restrictions
- Fixed by allowing inbound access from local machine

---

## 📸 Deployment Evidence

Screenshots included:

- RDS instance creation
- Database connection
- Application running in Docker
- API responses in Postman

---

## 🛠️ How to Run Locally

1. Clone the repository:

```bash
git clone <your-repo-url>
cd <project-folder>
```

2. Run the application:

```bash
./mvnw spring-boot:run
```

3. Access API:

```
http://localhost:8080
```

---

## 📌 Notes

- Dates use ISO format: `YYYY-MM-DD`
- JSON recursion handled using `@JsonIgnore`
- Many-to-many relationship properly synchronized in service layer

---

## 🎯 Optional (CI/CD)

A GitHub Action can be added to:

- Build Docker image
- Push to Docker Hub on merge to main

---

## 📎 Deliverables

- GitHub repository link
- Postman screenshots
- Docker running screenshot
- AWS RDS setup screenshots
- README documentation

---

## 👨‍💻 Author
Developed by Abiodun Magret Oyedele.

---
## Swagger Endpoint imported into postman
http://localhost:8080/v3/api-docs