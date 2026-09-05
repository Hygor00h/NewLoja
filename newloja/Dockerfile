# Etapa 1: Build da aplicação utilizando Maven e Java 21
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copia as dependências e o código fonte
COPY pom.xml .
COPY src ./src

# Compila o projeto ignorando os testes unitários
RUN mvn clean package -DskipTests

# Etapa 2: Execução leve apenas com o JRE Java 21
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Executa o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]