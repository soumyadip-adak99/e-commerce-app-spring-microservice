# 🛒 E-Commerce App – Spring Microservices Architecture

A **production‑grade e‑commerce backend** built using **Spring Boot & Spring Cloud**, following **microservices architecture** principles. The system is designed for **scalability, fault tolerance, asynchronous communication, and cloud‑native deployment**.

---

## 📌 Overview

This project demonstrates a **real‑world Spring MVC microservices architecture** for an e‑commerce platform. It includes independent services for customer management, product catalog, order processing, payments, notifications, service discovery, centralized configuration, and distributed tracing.

The architecture follows **industry best practices** such as:

* API Gateway pattern
* Service Discovery (Eureka)
* Centralized Configuration (Config Server)
* Event‑driven communication (Kafka)
* Distributed Tracing (Zipkin)
* Database‑per‑service

---

## 🧩 System Architecture

### 🔹 High‑Level Architecture Diagram

![System Architecture](https://res.cloudinary.com/dzunlgq2p/image/upload/v1767355690/Untitled-2025-12-23-1303.excalidraw_mepon0.png)

**Key Highlights:**

* Public access via **API Gateway**
* Private internal network for microservices
* Synchronous REST & asynchronous event‑based communication
* Observability using **Zipkin**

---

## 🗄️ Database Design

Each microservice owns its **independent database**, ensuring loose coupling and data isolation.

![Database Design](https://res.cloudinary.com/dzunlgq2p/image/upload/v1767247212/Screenshot_2026-01-01_112834_ezuppg.png)

---

## 🧱 Microservices Breakdown

### 🔸 API Gateway

* Single entry point for all client requests
* Route mapping:

    * `/customers` → Customer Service
    * `/products` → Product Service
    * `/orders` → Order Service
* Handles request routing, security, and filtering

---

### 🔸 Customer Service

* Manages customer profiles and details
* RESTful APIs for CRUD operations
* MongoDB for persistence

---

### 🔸 Product Service

* Manages product catalog
* Product listing, pricing, availability
* MongoDB as the data store

---

### 🔸 Order Service

* Handles order creation and lifecycle
* Communicates with Payment Service
* Publishes order confirmation events to Kafka
* MongoDB for order persistence

---

### 🔸 Payment Service

* Processes payment logic
* Emits **payment confirmation events** asynchronously
* Integrated with Kafka message broker

---

### 🔸 Notification Service

* Consumes Kafka events
* Sends order & payment notifications (email / messaging)
* Uses MongoDB for logs & records

---

## 🔄 Event‑Driven Communication

The system uses **Apache Kafka** for asynchronous communication:

* Order Service → Order Confirmation Event
* Payment Service → Payment Confirmation Event
* Notification Service → Event Consumer

This ensures:

* Loose coupling
* High scalability
* Fault tolerance

---

## 🧭 Service Discovery & Configuration

### 🔹 Eureka Server

* Dynamic service registration
* Load balancing support

### 🔹 Config Server

* Centralized configuration management
* Git‑based configuration repository

---

## 🔍 Observability & Tracing

### 🔹 Zipkin

* Distributed request tracing
* Performance monitoring
* Debugging inter‑service latency

---

## 🛠️ Tech Stack

| Layer            | Technology              |
| ---------------- |-------------------------|
| Backend          | Spring Boot, Spring MVC |
| Cloud            | Spring Cloud            |
| Gateway          | Spring Cloud Gateway    |
| Discovery        | Eureka Server           |
| Config           | Spring Cloud Config     |
| Messaging        | Apache Kafka            |
| Database         | MongoDB  , Postgresql   |
| Tracing          | Zipkin                  |
| Containerization | Docker                  |

---

## 🚀 Getting Started

### Prerequisites

* Java 17+
* Maven
* Docker & Docker Compose
* Kafka & Zookeeper

### Run with Docker Compose

```bash
docker-compose up --build
```

---

[//]: # (## 📂 Project Structure)

[//]: # ()
[//]: # (```)

[//]: # (e-commerce-app-spring-microservice)

[//]: # (├── api-gateway)

[//]: # (├── customer-service)

[//]: # (├── product-service)

[//]: # (├── order-service)

[//]: # (├── payment-service)

[//]: # (├── notification-service)

[//]: # (├── eureka-server)

[//]: # (├── config-server)

[//]: # (└── docker-compose.yml)

[//]: # (```)

---

## 🔐 Architecture Principles Followed

* Single Responsibility Principle
* Database per Service
* Loose Coupling
* High Availability
* Event‑Driven Design
* Cloud‑Native Ready

---

## 📈 Future Enhancements

* Authentication & Authorization (JWT / OAuth2)
* Circuit Breaker (Resilience4j)
* Rate Limiting
* Kubernetes Deployment
* Monitoring with Prometheus & Grafana

---

## 👨‍💻 Author

**Soumyadip Adak**
🌐 Website: [https://soumyadip-adak.pages.dev](https://soumyadip-adak.pages.dev)

