FROM eclipse-temurin:21

WORKDIR /app

# Copy the entire project

COPY . .

# Grant Gradlew execution privileges
RUN chmod +x gradlew

# Build the project inside the container
RUN ./gradlew clean build -x test

EXPOSE 8080

CMD ["java", "-jar", "build/libs/demoo-1.0.0.jar"]
