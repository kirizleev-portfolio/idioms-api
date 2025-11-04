# Idioms API · Java / Spring Boot Backend

A small REST API built with **Java 21** and **Spring Boot 3.5** that serves English idioms from a PostgreSQL database.  
The project demonstrates a clean 3-layer backend architecture with controller, service, and repository pattern, plus OpenAPI documentation and automatic CSV import at startup.

---

## Tech Stack
- Java 21  
- Spring Boot 3.5  
- Gradle 8  
- PostgreSQL 15  
- Swagger / OpenAPI  

---

## Purpose
The application loads thousands of English idioms into a PostgreSQL database and provides endpoints for searching or retrieving random idioms.  
It was created as a **learning and portfolio project** to practice backend design, JPA, and REST principles.

---

## Architecture

| Layer | Responsibility |
|-------|----------------|
| **Controller** | Handles HTTP requests & responses |
| **Service** | Business logic and validation |
| **Repository** | Database access using JPA |
| **Entity (Idiom)** | Data model for idioms table |

---

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| `GET /api/idioms/random` | Returns random idioms (optional filtering by `frequency`, `count`) |
| `GET /api/idioms/search` | Searches idioms by text or meaning (`query`) |

---

## Example Request

```bash
GET http://localhost:8080/api/idioms/search?query=cat
```

Response:
```json
[
  {
    "id": 42,
    "text": "let the cat out of the bag",
    "meaning": "to reveal a secret",
    "example": "She let the cat out of the bag about the surprise party.",
    "frequency": 5
  }
]
```

---

## Key Learning Points
- Layered architecture in Spring Boot  
- JPA queries and native SQL  
- CSV import on startup via CommandLineRunner  
- OpenAPI documentation for REST endpoints  
- Data validation and exception handling  

---

## Run locally

```bash
./gradlew bootRun
```
Access Swagger UI:  
👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Project Structure

```
src/
 ├─ main/
 │   ├─ java/com/idioms/api/
 │   │   ├─ controller/
 │   │   ├─ service/
 │   │   ├─ repository/
 │   │   └─ model/
 │   └─ resources/
 │       ├─ application.yml
 │       └─ data/ (CSV – not included)
 └─ test/
```

---

## Author
**Vladimir Kirizleev**  
Java / Spring Boot Backend Development · Data Logic · API Design  
[GitHub Portfolio](https://github.com/kirizleev-portfolio)

---

### 🇩🇪 **Deutsche Kurzbeschreibung**

Ein kleines Java-Backend zum Verwalten und Abrufen englischer Idioms.  
Das Projekt entstand als Lern-API, um eine saubere Spring-Boot-Architektur mit REST-Endpoints, Service-Layer und Datenbankanbindung zu zeigen.  
Schwerpunkte: **API-Design, Datenlogik, Validierung und Dokumentation mit OpenAPI.**
