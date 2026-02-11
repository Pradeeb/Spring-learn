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
-->
RateLimitFilter (Per-IP + Global Check)
-->
Controller
-->
Service
-->
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
```
 ---

## ⚙️ Rate Limit Configuration
🔹 Per-IP Limit

10 requests per minute per IP

```code
private Bucket createBucket() {
    Bandwidth limit = Bandwidth.classic(
            10,
            Refill.intervally(10, Duration.ofMinutes(1))
    );
    return Bucket.builder().addLimit(limit).build();
}
```
---
🔹 Global Limit

500 requests per second (entire application)
```code
private final Bucket globalBucket = Bucket.builder()
        .addLimit(Bandwidth.classic(
                500,
                Refill.intervally(500, Duration.ofSeconds(1))
        ))
        .build();
```
---
## 🧠 How It Works (Step-by-Step)
1️⃣ Cache Creation

```code
private final Cache<String, Bucket> cache = Caffeine.newBuilder()
        .expireAfterAccess(10, TimeUnit.MINUTES)
        .maximumSize(10_000)
        .build();
```
✔ Stores bucket per IP
✔ Removes inactive IPs automatically
✔ Prevents memory leak
✔ Supports up to 10,000 active IPs
---

2️⃣ Filter Execution Logic

```code
@Override
public void doFilter(ServletRequest request,
                     ServletResponse response,
                     FilterChain chain)
        throws IOException, ServletException {

    HttpServletResponse httpResponse = (HttpServletResponse) response;
    String ip = request.getRemoteAddr();

    Bucket bucket = cache.get(ip, k -> createBucket());

    if (globalBucket.tryConsume(1) && bucket.tryConsume(1)) {
        chain.doFilter(request, response);
    } else {
        httpResponse.setStatus(429);
        httpResponse.setContentType("application/json");
        httpResponse.setHeader("Retry-After", "60");

        httpResponse.getWriter().write("""
            {
              "status": 429,
              "error": "Too Many Requests",
              "message": "Rate limit exceeded. Try again later."
            }
        """);
    }
}

```

✔ What Happens Internally

Client sends request.

Filter captures request before controller.

System checks:

Global bucket

Per-IP bucket

If tokens available → Request proceeds.

If limit exceeded → HTTP 429 returned.
----

## 📊 Example Behavior
📊 Example Behavior
Scenario 1: Single IP
Request Count	Response
1 - 10	200 OK
11	429 Too Many Requests
Scenario 2: 100 Different IPs

Each IP:

Can make 10 requests per minute

But total application:

Cannot exceed 500 requests per second

If global limit exceeded → 429 returned.

---

## 🔁 HTTP 429 Response Example

```code
{
  "status": 429,
  "error": "Too Many Requests",
  "message": "Rate limit exceeded. Try again later."
}

```
