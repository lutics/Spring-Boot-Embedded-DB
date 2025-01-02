# Spring-Boot-Embedded-DB

## 요구사항

- Docker Desktop 설치 및 실행

## 실행

- build.gradle.kts 에서 Spring Boot 주석을 활성화/비활성화 하면서 환경 별 실행가능 (3.4.1, 3.0.1, 2.7.1)

#### 예제 1. Embedded Mongo

```curl
curl "http://localhost:8080/mongo"
```

#### 예제 2. Embedded Redis

```curl
curl "http://localhost:8080/redis"
```