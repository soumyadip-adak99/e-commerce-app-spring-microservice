# 🛒 E-Commerce Microservices Platform

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.1-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2025.1.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io/projects/spring-cloud)
[![Docker](https://img.shields.io/badge/Docker-Compose-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)
[![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-7.4.0-231F20?style=for-the-badge&logo=apache-kafka&logoColor=white)](https://kafka.apache.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](LICENSE)

A **production-grade e-commerce backend** built with **Spring Boot 4 & Spring Cloud**, implementing enterprise-level **microservices architecture** with event-driven communication, centralized configuration, and comprehensive observability.

---

## 📑 Table of Contents

- [Overview](#-overview)
- [Architecture](#-architecture)
- [Microservices](#-microservices)
- [Tech Stack](#-tech-stack)
- [Getting Started](#-getting-started)
- [API Endpoints](#-api-endpoints)
- [Project Structure](#-project-structure)
- [Infrastructure Services](#-infrastructure-services)
- [Design Principles](#-design-principles)
- [Future Roadmap](#-future-roadmap)
- [Contributing](#-contributing)
- [Author](#-author)
- [License](#-license)

---

## 📌 Overview

This project demonstrates a **real-world microservices architecture** for an e-commerce platform featuring:

- **8 Independent Microservices** — Customer, Product, Order, Payment, Notification, API Gateway, Discovery Server & Config Server
- **Event-Driven Architecture** — Asynchronous communication via Apache Kafka
- **Service Discovery** — Dynamic registration with Netflix Eureka
- **Centralized Configuration** — Spring Cloud Config Server with Git-based configs
- **Database per Service** — PostgreSQL & MongoDB for different use cases
- **API Gateway Pattern** — Single entry point with intelligent routing

---

## 🧩 Architecture

### High-Level System Design

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                              CLIENT APPLICATIONS                             │
│                        (Web, Mobile, Third-Party APIs)                       │
└──────────────────────────────────┬──────────────────────────────────────────┘
                                   │
                                   ▼
┌─────────────────────────────────────────────────────────────────────────────┐
│                           ╔═══════════════════╗                             │
│                           ║   API GATEWAY     ║  ◄─── Port: 8222            │
│                           ║ (Spring Cloud GW) ║                             │
│                           ╚═════════╦═════════╝                             │
│                                     │                                       │
│  ┌──────────────────────────────────┼──────────────────────────────────┐    │
│  │           PRIVATE MICROSERVICES NETWORK (Eureka Discovery)          │    │
│  │                                  │                                  │    │
│  │    ┌─────────────┐    ┌─────────────┐    ┌─────────────┐           │    │
│  │    │  Customer   │    │   Product   │    │    Order    │           │    │
│  │    │   Service   │◄──►│   Service   │◄──►│   Service   │           │    │
│  │    │ (Port 8090) │    │ (Port 8050) │    │ (Port 8070) │           │    │
│  │    └──────┬──────┘    └──────┬──────┘    └──────┬──────┘           │    │
│  │           │                  │                  │                   │    │
│  │           ▼                  ▼                  ▼                   │    │
│  │    ┌──────────────┐   ┌───────────────┐   ┌────────────────┐       │    │
│  │    │   MongoDB    │   │  PostgreSQL   │   │   PostgreSQL   │       │    │
│  │    │  (Customers) │   │   (Products)  │   │    (Orders)    │       │    │
│  │    └──────────────┘   └───────────────┘   └────────────────┘       │    │
│  │                                                                     │    │
│  │    ┌─────────────┐    ┌────────────────────────────────────────┐   │    │
│  │    │   Payment   │    │           APACHE KAFKA                 │   │    │
│  │    │   Service   │───►│  ┌─────────────┐  ┌─────────────────┐  │   │    │
│  │    │ (Port 8060) │    │  │Order Events │  │ Payment Events  │  │   │    │
│  │    └──────┬──────┘    │  └──────┬──────┘  └────────┬────────┘  │   │    │
│  │           │           │         │                  │           │   │    │
│  │           ▼           │         ▼                  ▼           │   │    │
│  │    ┌──────────────┐   │  ┌────────────────────────────────┐    │   │    │
│  │    │  PostgreSQL  │   │  │     Notification Service       │    │   │    │
│  │    │  (Payments)  │   │  │        (Port 8040)             │    │   │    │
│  │    └──────────────┘   │  │   📧 Email via Thymeleaf       │    │   │    │
│  │                       │  └────────────────────────────────┘    │   │    │
│  │                       └────────────────────────────────────────┘   │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
│                                                                             │
│  ┌─────────────────────────────────────────────────────────────────────┐    │
│  │                    INFRASTRUCTURE SERVICES                           │    │
│  │  ┌─────────────────┐  ┌─────────────────┐  ┌─────────────────────┐  │    │
│  │  │  Config Server  │  │ Discovery Server│  │      Zipkin         │  │    │
│  │  │   (Port 8888)   │  │  (Port 8761)    │  │  (Distributed Trace)│  │    │
│  │  │  Git-based Cfg  │  │  Eureka Server  │  │                     │  │    │
│  │  └─────────────────┘  └─────────────────┘  └─────────────────────┘  │    │
│  └─────────────────────────────────────────────────────────────────────┘    │
└─────────────────────────────────────────────────────────────────────────────┘
```

### Architecture Diagram

![System Architecture](https://res.cloudinary.com/dzunlgq2p/image/upload/v1767355690/Untitled-2025-12-23-1303.excalidraw_mepon0.png)

### Database Schema Design

![Database Design](https://res.cloudinary.com/dzunlgq2p/image/upload/v1767247212/Screenshot_2026-01-01_112834_ezuppg.png)

---

## 🔷 Microservices

### Business Services

| Service | Port | Database | Description |
|---------|------|----------|-------------|
| **Customer Service** | 8090 | MongoDB | Customer profiles, registration, and management |
| **Product Service** | 8050 | PostgreSQL | Product catalog with Flyway migrations |
| **Order Service** | 8070 | PostgreSQL | Order lifecycle management with OpenFeign |
| **Payment Service** | 8060 | PostgreSQL | Payment processing and transaction handling |
| **Notification Service** | 8040 | MongoDB | Event-driven notifications via email (Thymeleaf) |

### Infrastructure Services

| Service | Port | Description |
|---------|------|-------------|
| **API Gateway** | 8222 | Spring Cloud Gateway MVC with load balancing |
| **Discovery Server** | 8761 | Netflix Eureka for service registration |
| **Config Server** | 8888 | Centralized configuration with Git backend |

---

### 🔸 Customer Service
- RESTful APIs for customer CRUD operations
- MongoDB for flexible document storage
- Spring Data MongoDB integration
- Bean validation with Jakarta Validation

### 🔸 Product Service
- Product catalog management
- PostgreSQL with Flyway database migrations
- Spring Data JPA for data persistence
- Category and inventory tracking

### 🔸 Order Service
- Complete order lifecycle management
- **OpenFeign** for synchronous inter-service communication
- Kafka producer for order confirmation events
- Transaction management with Spring Data JPA

### 🔸 Payment Service
- Payment processing logic
- Kafka producer for payment confirmation events
- PostgreSQL for transaction records
- Integration with ecommerce-common shared library

### 🔸 Notification Service
- **Kafka consumer** for order & payment events
- **Thymeleaf** for HTML email templates
- **Spring Mail** integration with MailDev for development
- MongoDB for notification logs and audit trails

---

## ⚡ Event-Driven Communication

```
┌──────────────┐     Order Confirmation      ┌────────────────────┐
│ Order Service │ ─────────────────────────► │                    │
└──────────────┘                             │   Apache Kafka     │
                                             │                    │
┌────────────────┐   Payment Confirmation    │  ┌──────────────┐  │
│ Payment Service │ ───────────────────────► │  │  Topics:     │  │
└────────────────┘                           │  │  - orders    │  │
                                             │  │  - payments  │  │
                                             │  └──────┬───────┘  │
                                             │         │          │
                                             └─────────┼──────────┘
                                                       │
                                                       ▼
                                             ┌─────────────────────┐
                                             │ Notification Service │
                                             │   (Event Consumer)   │
                                             │   📧 Send Emails      │
                                             └─────────────────────┘
```

**Benefits:**
- ✅ Loose coupling between services
- ✅ Horizontal scalability
- ✅ Fault tolerance & resilience
- ✅ Asynchronous processing

---

## 🛠️ Tech Stack

### Core Framework
| Technology | Version | Purpose |
|------------|---------|---------|
| Java | 21 (LTS) | Programming Language |
| Spring Boot | 4.0.1 | Application Framework |
| Spring Cloud | 2025.1.0 | Microservices Infrastructure |
| Maven | 3.9+ | Build Tool |

### Spring Cloud Components
| Component | Usage |
|-----------|-------|
| Spring Cloud Gateway MVC | API Gateway & Routing |
| Spring Cloud Config | Centralized Configuration |
| Netflix Eureka | Service Discovery |
| Spring Cloud OpenFeign | Declarative REST Clients |
| Spring Cloud LoadBalancer | Client-side Load Balancing |

### Data Layer
| Technology | Usage |
|------------|-------|
| PostgreSQL 15 | Orders, Products, Payments |
| MongoDB 7 | Customers, Notifications |
| Spring Data JPA | PostgreSQL ORM |
| Spring Data MongoDB | MongoDB Integration |
| Flyway | Database Migrations |

### Messaging & Communication
| Technology | Usage |
|------------|-------|
| Apache Kafka 7.4.0 | Event Streaming |
| Zookeeper | Kafka Coordination |
| Spring Kafka | Kafka Integration |

### Observability & Monitoring
| Technology | Usage |
|------------|-------|
| Zipkin | Distributed Tracing |
| Spring Boot Actuator | Health Checks & Metrics |
| Micrometer | Application Metrics |

### DevOps & Infrastructure
| Technology | Usage |
|------------|-------|
| Docker | Containerization |
| Docker Compose | Multi-container Orchestration |
| MailDev | Email Testing (Dev) |
| pgAdmin | PostgreSQL Admin UI |
| Mongo Express | MongoDB Admin UI |

---

## 🚀 Getting Started

### Prerequisites

- **Java 21+** (JDK)
- **Maven 3.9+**
- **Docker & Docker Compose**
- **Git**

### Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/soumyadip-adak99/e-commerce-app-spring-microservice.git
   cd e-commerce-app-spring-microservice
   ```

2. **Start infrastructure services**
   ```bash
   docker-compose up -d
   ```

3. **Wait for services to be healthy** (check with `docker-compose ps`)

4. **Start services in order**
   ```bash
   # 1. Start Config Server first
   cd services/config-server && mvn spring-boot:run
   
   # 2. Start Discovery Server
   cd services/discovery-server && mvn spring-boot:run
   
   # 3. Start business services (in any order)
   cd services/customer-service && mvn spring-boot:run
   cd services/product-service && mvn spring-boot:run
   cd services/order-service && mvn spring-boot:run
   cd services/payment-service && mvn spring-boot:run
   cd services/notification-service && mvn spring-boot:run
   
   # 4. Start API Gateway
   cd services/api-gateway-service && mvn spring-boot:run
   ```

### Service URLs

| Service | URL |
|---------|-----|
| API Gateway | http://localhost:8222 |
| Eureka Dashboard | http://localhost:8761 |
| Config Server | http://localhost:8888 |
| pgAdmin | http://localhost:5050 |
| Mongo Express | http://localhost:8081 |
| MailDev (SMTP UI) | http://localhost:1080 |
| Zipkin (if enabled) | http://localhost:9411 |

---

## 📡 API Endpoints

All APIs are accessible through the **API Gateway** at `http://localhost:8222`

### Customer Service
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/customers` | List all customers |
| `GET` | `/api/v1/customers/{id}` | Get customer by ID |
| `POST` | `/api/v1/customers` | Create new customer |
| `PUT` | `/api/v1/customers/{id}` | Update customer |
| `DELETE` | `/api/v1/customers/{id}` | Delete customer |

### Product Service
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/products` | List all products |
| `GET` | `/api/v1/products/{id}` | Get product by ID |
| `POST` | `/api/v1/products` | Create new product |
| `POST` | `/api/v1/products/purchase` | Purchase products |

### Order Service
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/orders` | List all orders |
| `GET` | `/api/v1/orders/{id}` | Get order by ID |
| `POST` | `/api/v1/orders` | Create new order |

### Payment Service
| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/payments` | Process payment |
| `GET` | `/api/v1/payments/{id}` | Get payment by ID |

---

## 📂 Project Structure

```
e-commerce-app-spring-microservice/
├── 📁 services/
│   ├── 📁 api-gateway-service/     # Spring Cloud Gateway MVC
│   ├── 📁 config-server/           # Spring Cloud Config Server
│   ├── 📁 discovery-server/        # Netflix Eureka Server
│   ├── 📁 customer-service/        # Customer Management (MongoDB)
│   ├── 📁 product-service/         # Product Catalog (PostgreSQL)
│   ├── 📁 order-service/           # Order Processing (PostgreSQL)
│   ├── 📁 payment-service/         # Payment Handling (PostgreSQL)
│   └── 📁 notification-service/    # Event-Driven Notifications
├── 📁 resource/                    # Shared resources & configs
├── 📁 diagram/                     # Architecture diagrams
├── 📄 docker-compose.yml           # Infrastructure setup
├── 📄 LICENSE                      # MIT License
└── 📄 README.md                    # Project documentation
```

---

## 🐳 Infrastructure Services

The `docker-compose.yml` provisions the following infrastructure:

| Container | Image | Ports | Purpose |
|-----------|-------|-------|---------|
| `ms_pg_sql` | postgres:15 | 5432 | PostgreSQL Database |
| `ms_pgadmin` | dpage/pgadmin4 | 5050 | PostgreSQL Admin UI |
| `ms_mongo_db` | mongo:7 | 27017 | MongoDB Database |
| `ms_mongo_express` | mongo-express | 8081 | MongoDB Admin UI |
| `zookeeper` | confluentinc/cp-zookeeper | 22181 | Kafka Coordination |
| `ms_kafka` | confluentinc/cp-kafka:7.4.0 | 9092 | Message Broker |
| `ms_mail_dev` | maildev/maildev | 1080, 1025 | Email Testing Server |

---

## 🏗️ Design Principles

This project adheres to industry best practices:

- ✅ **Single Responsibility Principle** — Each service handles one domain
- ✅ **Database per Service** — Independent data stores for loose coupling
- ✅ **API Gateway Pattern** — Unified entry point for clients
- ✅ **Event-Driven Design** — Asynchronous communication via Kafka
- ✅ **12-Factor App** — Cloud-native application methodology
- ✅ **Externalized Configuration** — Config Server with Git backend
- ✅ **Service Discovery** — Dynamic registration with Eureka
- ✅ **Health Checks** — Spring Boot Actuator endpoints

---

## 🔮 Future Roadmap

- [ ] **Security** — OAuth2 / JWT Authentication
- [ ] **Resilience** — Circuit Breaker with Resilience4j
- [ ] **Rate Limiting** — API throttling at Gateway level
- [ ] **Kubernetes** — Container orchestration deployment
- [ ] **Monitoring** — Prometheus & Grafana dashboards
- [ ] **API Documentation** — OpenAPI 3.0 / Swagger UI
- [ ] **Distributed Caching** — Redis integration
- [ ] **CQRS Pattern** — Command Query Responsibility Segregation

---
<!-- 
## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

--- -->

## 👨‍💻 Author

<p align="center">
  <b>Soumyadip Adak</b><br>
  <a href="https://soumyadip-adak.pages.dev">🌐 Portfolio</a> •
  <a href="https://github.com/soumyadip-adak99">GitHub</a>
</p>

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

<p align="center">
  <sub>⭐ If you found this project helpful, please consider giving it a star!</sub>
</p>
