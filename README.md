# Seminar Hall Booking Backend

## 📌 Project Overview
The **Seminar Hall Booking Backend** is a robust REST API developed using **Spring Boot**. It facilitates the booking of seminar halls, managing user and admin roles, and ensuring secure access via **JWT (JSON Web Token)** authentication. This backend serves as the core logic for handling booking requests, approvals, and user management.

## 🚀 Features
- **Authentication & Authorization**:
  - Secure Login/Signup for Users and Admins.
  - Role-based access control (RBAC) using Spring Security and JWT.
- **Booking Management**:
  - **Users** can view halls, check availability, and submit booking requests.
  - **Admins** can review, approve, or reject booking requests.
- **User Management**:
  - Profile management for registered users.
  - Admin dashboard capabilities (backend support).
- **Database**:
  - Persistent data storage using **MySQL**.

## 🛠️ Tech Stack
- **Language**: Java 17+
- **Framework**: Spring Boot 3.x
- **Security**: Spring Security, JWT (jjwt)
- **Database**: MySQL
- **Build Tool**: Maven
- **Tools**: Postman (for API testing), Lombok

## 🔌 API Endpoints

### Authentication
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/auth/signup` | Register a new user |
| `POST` | `/auth/login` | Authenticate and get JWT token |

### Admin
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/admin/bookings` | View all booking requests |
| `PUT` | `/admin/approve/{id}` | Approve a booking request |
| `PUT` | `/admin/reject/{id}` | Reject a booking request |

### User
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/user/book` | Submit a booking request |
| `GET` | `/user/my-bookings` | View user's booking history |

*(Note: Ensure you include the `Authorization: Bearer <token>` header for protected endpoints)*

## ⚙️ How to Run Locally

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Maven
- MySQL Server

### Steps
1. **Clone the Repository**
   ```bash
   git clone https://github.com/Thizizmanojkumar-27/Seminar_Hall_Booking_Backend.git
   cd Seminar_Hall_Booking_Backend
   ```

2. **Configure Database**
   - Create a MySQL database named `seminar_db` (or as configured in `application.properties`).
   - Update `src/main/resources/application.properties` with your database credentials.

3. **Build the Project**
   ```bash
   mvn clean install
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```
   The server will start at `http://localhost:8080`.

## 🧪 Postman Testing
1. **Login** as User/Admin to get the `token`.
2. Copy the `token` from the response.
3. For protected routes, go to the **Authorization** tab in Postman.
4. Select **Bearer Token** and paste the token.
5. Hit **Send** to access the API.

