# 🚏 Uber Gateway Service

The **Uber Gateway Service** is a Spring Cloud Gateway–based microservice that serves as the **API Gateway** for the Uber clone system.  
It centralizes request handling, routing, and security across all backend microservices.

---

## 🔑 Key Responsibilities

- **Single Entry Point**  
  All requests from mobile apps or frontend clients pass through the gateway.

- **Request Routing**  
  Routes traffic to microservices like:
  - Auth Service  
  - Booking Service  
  - Driver Service  
  - Passenger Service  
  - Notification Service  

- **Authentication & Authorization**  
  - Extracts and validates **JWT tokens** in headers.  
  - Blocks unauthorized access before reaching downstream services.  

- **Cross-cutting Concerns**  
  - Request logging  
  - Security filtering  
  - Metrics collection  
  - Header manipulation (e.g., remove/forward headers)  

---

## 🛠️ Tech Stack

- **Spring Boot** (Java 17+)  
- **Spring Cloud Gateway** – for routing and filters  
- **JWT (JSON Web Token)** – authentication mechanism  
- **Eureka / Service Discovery** (optional) – for locating microservices  
- **Resilience4j / Circuit Breaker** (optional) – fault tolerance  

---

## ⚡ How It Works

1. Client sends a request (e.g., `/api/v1/booking/create`).  
2. Gateway intercepts and applies filters:
   - Validate **Authorization: Bearer <token>** header.  
   - Reject request if token is missing/invalid.  
3. Routes request to appropriate service (e.g., `UberBookingService`).  
4. Downstream service processes and sends response back through the gateway.  

---

## 📌 Example Routes

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: UberProject-AuthService
          uri: lb://UberProject-AuthService
          predicates:
            - Path=/api/v1/auth/**
          filters:
            - RemoveRequestHeader=Cookie

        - id: UberBookingService
          uri: lb://UberBookingService
          predicates:
            - Path=/api/v1/booking/**
          filters:
            - JwtAuthFilter
