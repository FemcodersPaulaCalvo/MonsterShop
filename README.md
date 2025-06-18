# 🛒 Monster Shop

Monster Shop is an e-commerce web application developed with **Java 21** and **Spring Boot**. It allows users to browse products, view reviews, and write their own opinions. It serves as the backend for an online store whose frontend is implemented in React.

---

## 📑 Table of Contents

- [Features](#-features)
- [Technologies Used](#-technologies-used)
- [Environment Setup](#-environment-setup)
- [How to Run](#-how-to-run)
- [Main Endpoints](#-main-endpoints)
- [Project Structure](#-project-structure)
- [Error Handling](#-error-handling)
- [Additional Notes](#-additional-notes)
- [Author](#-author)
- [Acknowledgments](#-acknowledgments)

---

## 🎯 Features

- Monster Shop management
- CRUD operations for products and reviews
- Custom exception handling
- Input validation at the DTO level
- Layered architecture using Spring Boot
- Structured and controlled API responses
- Ready to be connected to a frontend or tested with Postman

---

## 🚀 Technologies Used

- **Java 21**
- **Spring Boot**
- **Maven**
- **Spring Web (REST API)**
- **Spring Data JPA**
- **MySQL**
- **DTO Pattern**
- **MapStruct** (for mapping between entities and DTOs)
- **Spring CORS Configuration**
- **.env configuration** (environment variables)

---

## 🔧 Environment Setup

### Requirements

- Java 21
- Maven 3.x
- MySQL
- An IDE such as IntelliJ or VSCode

### Environment Variables

Create a `.env` file at the root of the project with the following variables:

```env
DB_URL=jdbc:mysql://localhost:3306/monster_shop
DB_USERNAME=your_username
DB_PASSWORD=your_password
```

---

## ⚙️ How to run

```bash
# Clone the repository
git clone https://github.com/FemcodersPaulaCalvo/MonsterShop.git
cd MonsterShop

# Configure the database and the .env file

# Compile and run
./mvnw spring-boot:run
```

La API estará disponible en: `http://localhost:8080`

---

## 📌 Main Endpoints

> Note: Endpoints are grouped by resource type.

### 📦 Products

- `GET /products` → Listar productos
- `GET /products/{id}` → Obtener producto por ID
- `POST /products` → Crear producto
- `PUT /products/{id}` → Actualizar producto
- `DELETE /products/{id}` → Eliminar producto

### ✍️ Reviews

- `GET /reviews` → Listar reseñas
- `GET /reviews/product/{productId}` → Reseñas por producto
- `POST /reviews` → Crear reseña
- `DELETE /reviews/{id}` → Eliminar reseña

---

## 📁 Project Structure

```
MonsterShop/
├── src/
│   ├── main/
│   │   ├── java/com/MonsterShop/MS/
│   │   │   ├── config/           # Global configurations (CORS, etc.)
│   │   │   ├── controller/       # REST controllers
│   │   │   ├── dto/              # DTO classes and mappers
│   │   │   ├── entity/           # JPA entities
│   │   │   ├── repository/       # JPA repositories
│   │   │   └── service/          # Business logic
│   │   └── resources/
│   │       └── application.properties
├── .env                         # Environment variables
├── pom.xml                      # Maven dependencies
```

---

## 🚨 Error Handling

Includes centralized error management via `GlobalExceptionHandler` and custom exceptions like:

- `EmptyListException`
- `ProductAlreadyExistException`
- `NoIdProductFoundException`

---

## 📌 Additional Notes

- The project implements a clear separation of layers using the MVC pattern (Controller, Service, Repository).
- DTOs are used to avoid directly exposing the entities.
- The database is configured via .env variables and application.properties

---

## 🧑‍💻 Author

Developed by [Paula Calvo](https://github.com/FemcodersPaulaCalvo/MonsterShop.git)

---

## 🙏 Acknowledgments

- Spring Boot Team
- Factoria F5
- Open Source Community

---

Thanks for using MonsterShop! 🚀
