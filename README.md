# 🚀 Spring Boot Rate Limiting (Monolithic Application)

## 📌 Overview

This project demonstrates a **production-ready rate limiting implementation** in a **monolithic Spring Boot application** using:

- ✅ Bucket4j (Token Bucket Algorithm)
- ✅ Caffeine Cache (Efficient in-memory storage)
- ✅ Per-IP Rate Limiting
- ✅ Global Rate Limiting
- ✅ HTTP 429 Response Handling
- ✅ Retry-After Header Support

Rate limiting is implemented using a **Servlet Filter**, which executes before controller logic.

---

## 🏗 Architecture
Client Request
↓
RateLimitFilter (Per-IP + Global Check)
↓
Controller
↓
Service
↓
Database


Rate limiting happens **before the request reaches the controller**.

---

## 🔧 Technologies Used

- Java 17+ (or Java 8 compatible)
- Spring Boot
- Bucket4j
- Caffeine Cache
- Jakarta Servlet API

---

## 📦 Dependencies (Maven)
```xml
<dependency>
    <groupId>com.github.vladimir-bukhtoyarov</groupId>
    <artifactId>bucket4j-core</artifactId>
    <version>8.0.1</version>
</dependency>

<dependency>
    <groupId>com.github.ben-manes.caffeine</groupId>
    <artifactId>caffeine</artifactId>
    <version>3.1.8</version>
</dependency>
