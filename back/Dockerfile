FROM eclipse-temurin:17-jdk-alpine AS build

LABEL authors="titouan-aclr"

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw package -DskipTests

FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build /app/target/GameShelf-0.0.1-SNAPSHOT.jar /app/application.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/application.jar"]