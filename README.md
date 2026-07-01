# 🚀 HURRYFLEX Backend

A modern, secure REST API built with **Spring Boot** for the HURRYFLEX platform.

HURRYFLEX is designed to provide a scalable backend foundation for user authentication, profile management, role-based authorization, and future social platform features.

---

## 📌 Features

* ✅ User Registration
* ✅ User Login with JWT Authentication
* ✅ Secure Password Encryption (BCrypt)
* ✅ Role-Based Authorization (USER / ADMIN)
* ✅ User Profile Management
* ✅ Admin User Management
* ✅ Global Exception Handling
* ✅ RESTful API Design
* ✅ MySQL Database Integration

---

## 🛠️ Tech Stack

* Java 21
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* MySQL
* JWT (JSON Web Token)
* Maven

---

## 📂 Project Structure

```
src
 └── main
     ├── controller
     ├── dto
     ├── exception
     ├── model
     ├── repository
     ├── security
     ├── service
     │    └── impl
     └── HurryflexApplication.java
```

---

## 🔐 Authentication

Authentication is handled using JWT tokens.

After a successful login, the API returns a JWT token that must be included in future requests:

```
Authorization: Bearer YOUR_JWT_TOKEN
```

---

## 👤 User Features

* Register Account
* Login
* View Profile
* Update Profile

---

## 🛡️ Admin Features

Admins can:

* View all users
* Promote users to ADMIN
* Delete users

---

## 🗄️ Database

Database:

```
MySQL
```

Example connection:

```
jdbc:mysql://localhost:3306/hurryflex_db
```

---

## ▶️ Running the Project

Clone the repository:

```bash
git clone https://github.com/YOUR_USERNAME/hurryflex.git
```

Navigate into the project:

```bash
cd hurryflex
```

Run the application:

```bash
./mvnw spring-boot:run
```

The server starts at:

```
http://localhost:8080
```

---

## 📈 Current Progress

Completed:

* Spring Boot Project Setup
* MySQL Integration
* User Authentication
* JWT Security
* Profile API
* Admin API
* Exception Handling

Planned:

* Refresh Tokens
* Email Verification
* Password Reset
* File Uploads
* API Documentation (Swagger/OpenAPI)
* Docker Support
* Unit & Integration Tests
* CI/CD Pipeline
* Production Deployment

---

## 🎯 Project Goal

The goal of HURRYFLEX is to build a clean, scalable, and production-ready backend following modern Spring Boot best practices and clean architecture principles.

---

## 👨‍💻 Author

**Harrison Chama**

Building HURRYFLEX one feature at a time.
