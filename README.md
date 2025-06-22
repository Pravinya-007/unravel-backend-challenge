# 🔧 Unravel Backend Engineering Assignment

This repository presents a production-grade Java implementation of common backend system challenges including session management, deadlock handling, memory safety, prioritized queuing, and connection pool monitoring.

> Built using Java 11 and Maven with modular, readable, and testable code – suitable for high-scale environments.

---

## 📌 Tech Stack

- Java 11
- Maven
- JUnit 5
- HikariCP (Connection Pooling)
- H2 Database (for in-memory testing)
- SLF4J + Logback (Logging)

---

## 📂 Modules Implemented

| Module         | Description |
|----------------|-------------|
| `session/SessionManager.java` | Thread-safe session storage using `ConcurrentHashMap` and `ReadWriteLock`. |
| `memory/MemorySafeCache.java` | Solves memory leak via `WeakHashMap` and `WeakReference`. |
| `concurrency/PriorityJobQueue.java` | Implements a `PriorityBlockingQueue` to support producer-consumer logic with task prioritization. |
| `deadlock/DeadlockFreeResource.java` | Avoids deadlocks using `tryLock()` and consistent lock ordering. |
| `db/HikariCPMonitor.java` | Monitors DB connection pool stats using HikariCP + SLF4J logging. |

---

## 🚀 Getting Started

### 🧱 Prerequisites

- Java 11+
- Maven 3+

---

## 🛠️ Build and Run

### 1. Clone the project

```bash
git clone https://github.com/your-username/unravel-backend-challenge.git
cd unravel-backend-challenge
