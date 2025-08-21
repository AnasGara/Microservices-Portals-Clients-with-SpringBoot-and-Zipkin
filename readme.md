# Microservices: Portals & Clients with Spring Boot and Zipkin
[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/AnasGara/Microservices-Portals-Clients-with-SpringBoot-and-Zipkin)

This project is a demonstration of a microservices architecture built using the Spring ecosystem. It features several services for managing clients and their associated portals, orchestrated with Spring Cloud components for configuration, service discovery, and routing. The application stack is containerized with Docker and includes distributed tracing with Zipkin to monitor inter-service communication.

## Architecture Overview

The system is composed of the following services:

*   **Config Server**: Manages externalized configuration for all microservices from a central location.
*   **Discovery Server (Eureka)**: Allows services to register themselves and discover other services dynamically.
*   **API Gateway**: Provides a single entry point for all client requests and routes them to the appropriate backend service. It also handles cross-cutting concerns.
*   **Client Service**: A microservice responsible for CRUD operations on client data.
*   **Portal Service**: A microservice responsible for CRUD operations on portal data, which is associated with clients.
*   **Zipkin**: A distributed tracing system used to troubleshoot latency problems and visualize request flows across services.
*   **PostgreSQL**: The relational database used by both the `client` and `portal` services.
*   **PGAdmin**: A web-based administration tool for managing the PostgreSQL database.

## Features

*   **Centralized Configuration** with Spring Cloud Config.
*   **Service Registration and Discovery** using Netflix Eureka.
*   **Declarative REST Client** for inter-service communication with OpenFeign.
*   **Centralized Entry Point** and routing with Spring Cloud Gateway.
*   **Distributed Tracing** and monitoring with Micrometer and Zipkin.
*   **Containerized Infrastructure** using Docker Compose (PostgreSQL, PGAdmin, Zipkin).
*   **Database per Service Pattern** implemented with separate databases for the Client and Portal services.

## Prerequisites

Before you begin, ensure you have the following installed on your machine:
*   Java Development Kit (JDK) 17 or later
*   Apache Maven
*   Docker and Docker Compose

## Getting Started

Follow these steps to get the application running.

### 1. Clone the Repository
```bash
git clone https://github.com/AnasGara/microservices-portals-clients-with-springboot-and-zipkin.git
cd microservices-portals-clients-with-springboot-and-zipkin
```

### 2. Start Infrastructure Services
Run the following command from the root of the project to start the necessary infrastructure (PostgreSQL, PGAdmin, and Zipkin) in Docker containers.

```bash
docker-compose up -d
```

### 3. Run the Microservices
The Spring Boot applications must be started in a specific order to ensure proper dependency resolution and service registration. Open a new terminal for each service.

1.  **Start Config Server:**
    ```bash
    cd config-server
    ./mvnw spring-boot:run
    ```

2.  **Start Discovery Server:**
    ```bash
    cd discovery
    ./mvnw spring-boot:run
    ```

3.  **Start Portal Service:**
    ```bash
    cd portal
    ./mvnw spring-boot:run
    ```

4.  **Start Client Service:**
    ```bash
    cd client
    ./mvnw spring-boot:run
    ```

5.  **Start API Gateway:**
    ```bash
    cd gateway
    ./mvnw spring-boot:run
    ```

## Accessing Services

Once all services are running, you can access their UIs and APIs at the following locations:

| Service               | Port | URL                                           |
| --------------------- | ---- | --------------------------------------------- |
| API Gateway           | 8222 | `http://localhost:8222`                       |
| Discovery Server      | 8761 | `http://localhost:8761`                       |
| Config Server         | 8888 | `http://localhost:8888`                       |
| Zipkin UI             | 9411 | `http://localhost:9411`                       |
| PGAdmin               | 5050 | `http://localhost:5050`                       |
| **API via Gateway**   |      |                                               |
| Client Service API    | 8222 | `http://localhost:8222/api/v1/clients`        |
| Portal Service API    | 8222 | `http://localhost:8222/api/v1/portals`        |

**PGAdmin Credentials:**
*   **Email:** `pgadmin4@pgadmin.org`
*   **Password:** `admin`

## API Usage Examples

All API requests should be sent through the API Gateway on port `8222`.

### 1. Create a New Client
This will create a new client in the `clients` database. The response will include the generated `id`.

```bash
curl -X POST http://localhost:8222/api/v1/clients \
-H "Content-Type: application/json" \
-d '{
    "clientName": "Test Corp",
    "emailContact": "contact@testcorp.com",
    "mobileAppLink": "https://testcorp.com/app",
    "logo": "base64-encoded-logo-string",
    "extraInformation": "This is a test client."
}'
```

### 2. Create a Portal for the Client
Use the `clientId` from the previous step to associate this portal with the client.

```bash
curl -X POST http://localhost:8222/api/v1/portals \
-H "Content-Type: application/json" \
-d '{
    "URL": "https://portal.testcorp.com",
    "comment": "Main client portal.",
    "clientId": 1
}'
```

### 3. Get Client with All Associated Portals
This endpoint demonstrates inter-service communication. The Client Service fetches its data and then calls the Portal Service via Feign to retrieve all associated portals.

```bash
curl http://localhost:8222/api/v1/clients/with-portals/1
```

Expected Response:
```json
{
    "id": 1,
    "clientName": "Test Corp",
    "emailContact": "contact@testcorp.com",
    "mobileAppLink": "https://testcorp.com/app",
    "portals": [
        {
            "URL": "https://portal.testcorp.com",
            "comment": "Main client portal."
        }
    ]
}
```

### 4. View Traces in Zipkin
After making a few API calls, navigate to the Zipkin UI at `http://localhost:9411` to see the distributed traces. You can click the "Run Query" button to find recent traces and visualize how a request flows from the API Gateway to the backend services.
