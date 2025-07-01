# 💱 CurrencyXchange

A high-performance Spring Boot microservice engineered to deliver real-time currency and cryptocurrency conversion using RESTful APIs, external financial data providers, and persistent caching through PostgreSQL. This system showcases enterprise-grade integration, robust backend design, and clean separation of concerns.

---

## 🛠 Technologies Used

| Component             | Stack                                |
| --------------------- | ------------------------------------ |
| Programming Language  | Java 17+                             |
| Backend Framework     | Spring Boot 3.2.0                    |
| API Development       | Spring Web (RESTful API)             |
| ORM and Persistence   | Spring Data JPA                      |
| Relational Database   | PostgreSQL / H2 (in-memory dev mode) |
| HTTP Communication    | java.net.HttpURLConnection           |
| JSON Parsing          | org.json (2023-02-27)                |
| Boilerplate Reduction | Project Lombok 1.18.30               |

---

## 📁 Project Architecture

```
currencyXchange/
├── controller/          # Exposes REST endpoints
│   └── ExchangeController.java
├── service/             # Business logic layer
│   └── ExchangeService.java
├── model/               # Entity classes
│   └── ExchangeRate.java
├── repository/          # Spring Data JPA repository
│   └── ExchangeRateRepository.java
├── CurrencyXchangeApplication.java
├── resources/
│   └── application.properties
├── pom.xml              # Project configuration
└── README.md
```

---

## ⚙️ System Functionality

1. `ExchangeController`: Serves as the REST interface for frontend or API clients.
2. `ExchangeService`: Implements service logic, integrating with external currency APIs, parsing real-time data, and maintaining cache validity.
3. `ExchangeRateRepository`: Enables high-performance DB access using JPA.
4. PostgreSQL persists all rate records keyed by currency-pair identifiers.

The application ensures:

* Idempotent exchange rate refreshes
* On-demand fetching when rates are stale or missing
* Encapsulation of HTTP calls and JSON processing

---

## 🚀 Step-by-Step Deployment

### Step 1: PostgreSQL Configuration

Launch PostgreSQL and execute:

```sql
CREATE DATABASE currencydb;
CREATE USER currency_user WITH ENCRYPTED PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE currencydb TO currency_user;
```

### Step 2: Modify `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/currencydb
spring.datasource.username=currency_user
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> For H2 development:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

### Step 3: Build and Run

```bash
mvn clean install
mvn spring-boot:run
```

Application runs at: `http://localhost:8080`

---

## 📡 API Usage Examples

### 1. Convert Currency

```http
GET /convert?from=USD&to=INR&amount=100
```

**Sample JSON Response:**

```json
{
  "from": "USD",
  "to": "INR",
  "amount": 100,
  "convertedAmount": 8325.12,
  "rate": 83.2512
}
```

### 2. Refresh Cached Rates

```http
GET /refresh-cache
```

Returns a success JSON status after pulling the latest exchange data from the upstream provider.

---

## 📌 Technical Highlights

* Seamless integration of external APIs via robust HTTP request handling
* Efficient caching strategy to avoid redundant external calls
* RESTful endpoint design following best practices
* Atomic and thread-safe updates to the exchange rate database
* Modular architecture adhering to SOLID principles

---

## 🔭 Future Enhancements

* Frontend integration using React or Angular
* Add historical exchange rate analytics via scheduled data collection
* Integrate multi-source exchange APIs for fault tolerance
* Introduce Redis-based distributed cache for performance scaling
* Dockerize service with Kubernetes deployment blueprint

---

## 🪪 License

Open for educational, research, and non-commercial demonstration purposes. Forks and contributions are welcome!
