# Failure Simulation & Resilience Testing Platform

## 1. Problem:
Modern distributed systems fail in unpredictable ways.  
How do engineers validate resilience before production?

This project simulates real-world failures (latency, errors, service downtime) and evaluates system behavior under stress.

## 2. Architecture: 
Client → Core API → Service A → Service B → Chaos Service
                    ↓
             Resilience4j (Retry + Circuit Breaker)
                    ↓
         Prometheus → Grafana

## 3. Tech Stack:
- Spring Boot (Core API, Service A)
- FastAPI (Service B, Chaos Service)
- Resilience4j (Retry, Circuit Breaker)
- Prometheus (Metrics)
- Grafana (Visualization)
- Docker Compose (Multi-service orchestration)
- k6 (Load testing)

## 4. What This Demonstrates:
- Cascading failure simulation across services
- Retry behavior under failure conditions
- Circuit breaker state transitions (CLOSED → OPEN → HALF_OPEN)
- Observability with real-time metrics
- System recovery after failures stop

## 5. Results:
#### Normal Operation
Pic1
#### Retry Spike Under Failure
Pic2
#### Circuit Breaker Open
Pic3
#### Recovery
Pic4

## 6. Key Insight:
Under high failure rates, retries increase rapidly, leading to potential overload.
The circuit breaker prevents cascading failures by halting requests, allowing the system to recover.
This mirrors real-world resilience strategies used in large-scale systems (e.g., Uber, Netflix).

## 7. What I’d Do At Scale:
- Deploy on Kubernetes with service mesh (Istio)
- Add distributed tracing (Jaeger)
- Implement rate limiting + backpressure
- Introduce chaos testing via sidecar proxies
