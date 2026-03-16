# 🐛 DevOps Bug Tracker

A full-stack **Bug Tracking System** integrated with modern DevOps tools — built as a final year mini project for the course **DevOps (23CSE1663)** at **B.N.M. Institute of Technology**.

---

## 👩‍💻 Team Members

| USN | Name |
|-----|------|
| 1BG23CS067 | Kusuma R |
| 1BG23CS077 | Meghana R |

**Guide:** Prof. Manjushree K

---

## 📌 Project Overview

This project demonstrates the complete **DevOps infinity loop** — Plan → Code → Build → Test → Release → Deploy → Operate → Monitor — through a functional Bug Tracking System with CI/CD automation, containerized deployment, and real-time Slack notifications.

---

## 🛠️ Tools & Technologies

| Category | Tool |
|----------|------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| Database | H2 In-memory Database |
| Build Tool | Maven |
| Testing | JUnit 5 + Mockito |
| Version Control | Git & GitHub |
| CI/CD | Jenkins |
| Containerization | Docker |
| Cloud | AWS EC2 |
| Notifications | Slack Webhooks |
| IDE | IntelliJ IDEA |

---

## 📁 Project Structure

```
bugtracker/
├── src/
│   ├── main/
│   │   ├── java/com/devops/bugtracker/
│   │   │   ├── BugTrackerApplication.java
│   │   │   ├── controller/BugController.java
│   │   │   ├── model/Bug.java
│   │   │   ├── repository/BugRepository.java
│   │   │   └── service/
│   │   │       ├── BugService.java
│   │   │       └── SlackNotificationService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/index.html
│   └── test/
│       └── java/com/devops/bugtracker/
│           └── BugControllerTest.java
├── Dockerfile
├── Jenkinsfile
├── pom.xml
└── README.md
```

---

## 🚀 Features

- ✅ Create, update, assign and monitor bugs
- ✅ REST API with full CRUD operations
- ✅ Beautiful dark-themed dashboard UI
- ✅ Real-time dashboard auto-refresh every 5 seconds
- ✅ AI-powered bug priority predictor
- ✅ Slack real-time alerts for CRITICAL bugs
- ✅ Jenkins CI/CD pipeline with 5 stages
- ✅ Docker containerization
- ✅ JUnit automated testing
- ✅ H2 database with web console

---

## 🔁 DevOps CI/CD Pipeline

```
Developer → GitHub → Jenkins CI → Maven Build → JUnit Tests → Docker Build → Deploy → Slack Alert
```

### Jenkins Pipeline Stages

| Stage | Description |
|-------|-------------|
| Checkout | Pulls latest code from GitHub |
| Build | Runs `mvn clean package` |
| Test | Runs JUnit tests, records results |
| Docker Build | Builds Docker image |
| Deploy | Stops old container, starts new one |

---

## 📡 REST API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| GET | `/api/bugs` | Get all bugs |
| GET | `/api/bugs/{id}` | Get bug by ID |
| POST | `/api/bugs` | Create new bug |
| PUT | `/api/bugs/{id}` | Update bug |
| DELETE | `/api/bugs/{id}` | Delete bug |
| GET | `/api/bugs/status/{status}` | Filter by status |
| GET | `/api/bugs/priority/{priority}` | Filter by priority |

---

## ▶️ How to Run the Project

### Step 1 — Start the Spring Boot App

Open the project in IntelliJ IDEA and run `BugTrackerApplication.java`. Wait for:

```
Tomcat started on port 8082
```

### Step 2 — Start Jenkins

In the IntelliJ terminal run:

```bash
docker start jenkins
```

Jenkins will be available at `http://localhost:8090`

### Step 3 — Open the Dashboard

```
http://localhost:8082
```

---

## 🐞 Ways to Insert a Bug

### 1. Through the UI Dashboard

- Open `http://localhost:8082`
- Click **+ New Bug**
- Fill in Title, Description, Priority, Status, Assign To
- Click **Save Bug**
- Bug appears on dashboard instantly

### 2. Through Postman API

- Method → `POST`
- URL → `http://localhost:8082/api/bugs`
- Body → raw → JSON

```json
{
    "title": "Database connection lost",
    "description": "Production DB is down",
    "assignedTo": "Meghana R",
    "priority": "CRITICAL",
    "status": "OPEN"
}
```

### 3. Through H2 Database Console

- Open `http://localhost:8082/h2-console`
- JDBC URL → `jdbc:h2:mem:bugdb`
- Username → `sa`
- Click **Connect**

```sql
INSERT INTO BUGS (TITLE, DESCRIPTION, ASSIGNED_TO, PRIORITY, STATUS, CREATED_AT, UPDATED_AT)
VALUES ('Payment gateway timeout', 'Users unable to complete payments', 'Kusuma R',
'CRITICAL', 'OPEN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
```

Then verify:

```sql
SELECT * FROM BUGS;
```

---

## 🔔 Slack Notifications

When a **CRITICAL** bug is created through any method — UI, Postman, or H2 console — an automatic alert is sent to the `#new-channel` Slack channel:

```
🚨 CRITICAL BUG REPORTED
Title: Database connection lost
Description: Production DB is down
Assigned To: Meghana R
Status: OPEN
```

This demonstrates the **MONITOR** phase of the DevOps infinity loop.

---

## 🐳 Docker Commands

```bash
# Build the JAR
mvn clean package

# Build Docker image
docker build -t bug-tracker:latest .

# Run container
docker run -d -p 8082:8082 --name bug-tracker bug-tracker:latest

# Check running containers
docker ps
```

---

## ✅ JUnit Tests

Two automated tests are included:

| Test | Description |
|------|-------------|
| `testGetAllBugs()` | Verifies GET `/api/bugs` returns bugs correctly |
| `testCreateBug()` | Verifies POST `/api/bugs` creates a bug correctly |

Run tests in IntelliJ by right-clicking `BugControllerTest` → Run, or automatically via Jenkins pipeline.

---

## 📊 Application Ports

| Service | Port | URL |
|---------|------|-----|
| Bug Tracker App | 8082 | http://localhost:8082 |
| Jenkins | 8090 | http://localhost:8090 |
| H2 Console | 8082 | http://localhost:8082/h2-console |

---

## 📝 Synopsis

**Aim:** Design and implement a Bug Tracking System integrated with modern DevOps tools to enable continuous integration, automated testing, containerized deployment, and cloud-based application hosting.

**Expected Outcome:** A fully functional Bug Tracking System with an automated CI/CD pipeline where every code push triggers automatic build, test, and deployment — with real-time Slack monitoring for critical issues.
