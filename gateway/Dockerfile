FROM maven:3.8.3-openjdk-17 as builder
WORKDIR /build
COPY . .
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:17
COPY --from=builder /build/target/gateway-0.0.1-SNAPSHOT.jar gateway-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "gateway-0.0.1-SNAPSHOT.jar"]
