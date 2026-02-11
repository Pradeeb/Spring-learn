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


⚙️ Rate Limit Configuration
🔹 Per-IP Limit

10 requests per minute per IP

Bandwidth.classic(
    10,
    Refill.intervally(10, Duration.ofMinutes(1))
);
Global Limit

500 requests per second (total application)

Bandwidth.classic(
    500,
    Refill.intervally(500, Duration.ofSeconds(1))
);

🧠 How It Works (Step-by-Step)
1️⃣ Cache Creation
private final Cache<String, Bucket> cache = Caffeine.newBuilder()
        .expireAfterAccess(10, TimeUnit.MINUTES)
        .maximumSize(10_000)
        .build();


✔ Stores bucket per IP
✔ Automatically removes inactive IPs
✔ Prevents memory leak
✔ Supports up to 10,000 active IPs

2️⃣ Creating Per-IP Bucket
private Bucket createBucket() {
    Bandwidth limit = Bandwidth.classic(
            10,
            Refill.intervally(10, Duration.ofMinutes(1))
    );
    return Bucket.builder().addLimit(limit).build();
}


Each IP address gets its own token bucket.

3️⃣ Global Bucket
private final Bucket globalBucket = Bucket.builder()
        .addLimit(Bandwidth.classic(
                500,
                Refill.intervally(500, Duration.ofSeconds(1))
        ))
        .build();


This protects the entire application from overload.

4️⃣ Filter Execution Logic
if (globalBucket.tryConsume(1) && bucket.tryConsume(1)) {
    chain.doFilter(request, response);
} else {
    httpResponse.setStatus(429);
    httpResponse.setContentType("application/json");
    httpResponse.setHeader("Retry-After", "60");
}


✔ If tokens available → Request proceeds
❌ If tokens exhausted → HTTP 429 returned

📊 Example Behavior
Scenario 1: Single IP
Request Count	Response
1 - 10	200 OK
11	429 Too Many Requests
Scenario 2: 100 Different IPs

Each IP:

10 requests per minute allowed

But global limit:

500 requests per second total

If global limit exceeded → 429 returned.

🔁 HTTP 429 Response Example
{
  "status": 429,
  "error": "Too Many Requests",
  "message": "Rate limit exceeded. Try again later."
}


Header:

Retry-After: 60

▶️ How To Run
1️⃣ Clone Repository
git clone https://github.com/your-username/your-repo.git

2️⃣ Navigate to Project
cd your-repo

3️⃣ Run Application
mvn spring-boot:run


Or:

mvn clean install
java -jar target/demo.jar

🧪 How To Test

Use Postman or curl:

GET http://localhost:8080/api/v1/get


Send more than 10 requests within 1 minute.

Expected result:

HTTP 429 Too Many Requests

🏆 Why This Is Production Ready (Monolithic)

✔ Thread-safe implementation
✔ Memory-efficient cache
✔ Automatic bucket eviction
✔ Global + Per-IP protection
✔ Proper HTTP standards (429 + Retry-After)
✔ No memory leak risk

⚠️ Important Notes

This implementation is suitable for:

✔ Single-instance deployment
✔ Monolithic applications
✔ Moderate traffic systems

If you scale to multiple instances:

❗ In-memory buckets will not be shared
👉 Use Redis-based distributed rate limiting instead

📈 Future Improvements

JWT-based per-user rate limiting

Redis distributed rate limiting

Sliding window algorithm

Nginx rate limiting layer

Monitoring with Micrometer + Prometheus

Grafana dashboard for traffic visualization

👨‍💻 Author

Spring Boot Rate Limiting Demo
Monolithic Architecture Implementation


---

If you want, I can also give:

- 🔥 Professional GitHub badge version  
- 🔥 With architecture diagram image section  
- 🔥 With performance benchmark section  
- 🔥 With test cases section included  

Tell me 👍

give it single file

Here is your complete single-file README.md ready to copy and paste into your project root.

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

# 🏗 Architecture



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

# 📦 Dependencies (Maven)

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

⚙️ Rate Limit Configuration
🔹 Per-IP Limit

10 requests per minute per IP

private Bucket createBucket() {
    Bandwidth limit = Bandwidth.classic(
            10,
            Refill.intervally(10, Duration.ofMinutes(1))
    );
    return Bucket.builder().addLimit(limit).build();
}

🔹 Global Limit

500 requests per second (entire application)

private final Bucket globalBucket = Bucket.builder()
        .addLimit(Bandwidth.classic(
                500,
                Refill.intervally(500, Duration.ofSeconds(1))
        ))
        .build();

🧠 How It Works (Step-by-Step)
1️⃣ Cache Creation
private final Cache<String, Bucket> cache = Caffeine.newBuilder()
        .expireAfterAccess(10, TimeUnit.MINUTES)
        .maximumSize(10_000)
        .build();


✔ Stores bucket per IP
✔ Removes inactive IPs automatically
✔ Prevents memory leak
✔ Supports up to 10,000 active IPs

2️⃣ Filter Execution Logic
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

✔ What Happens Internally

Client sends request.

Filter captures request before controller.

System checks:

Global bucket

Per-IP bucket

If tokens available → Request proceeds.

If limit exceeded → HTTP 429 returned.

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

🔁 HTTP 429 Response Example
{
  "status": 429,
  "error": "Too Many Requests",
  "message": "Rate limit exceeded. Try again later."
}


Response Header:

Retry-After: 60

▶️ How To Run
1️⃣ Clone Repository
git clone https://github.com/your-username/your-repo.git

2️⃣ Navigate to Project
cd your-repo

3️⃣ Run Application
mvn spring-boot:run


Or

mvn clean install
java -jar target/demo.jar

🧪 How To Test

Send multiple requests:

GET http://localhost:8080/api/v1/get


If you send more than 10 requests within 1 minute:

HTTP 429 Too Many Requests

🏆 Why This Is Production Ready (Monolithic)

✔ Thread-safe
✔ Memory-efficient
✔ Automatic bucket eviction
✔ Global + Per-IP protection
✔ Proper HTTP 429 handling
✔ Retry-After support

⚠️ Important Notes

This implementation is suitable for:

✔ Single-instance deployment
✔ Monolithic applications
✔ Moderate traffic systems

If you scale to multiple instances:

❗ In-memory buckets will not be shared
👉 Use Redis-based distributed rate limiting instead

📈 Future Improvements

JWT-based per-user rate limiting

Redis distributed rate limiting

Sliding window algorithm

Nginx rate limiting

Monitoring with Micrometer + Prometheus

Grafana dashboard

👨‍💻 Author

Spring Boot Monolithic Rate Limiting Demo


---

If you want, I can also give a **clean professional GitHub version with badges and project structure section**.


