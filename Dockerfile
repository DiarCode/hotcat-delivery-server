FROM openjdk:17

# Create a non-root user
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser

WORKDIR /app

# Copy the application JAR
COPY /target/hotcat-0.0.1-SNAPSHOT.jar backend.jar

# Set ownership to non-root user
RUN chown -R appuser:appgroup /app

USER appuser

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "backend.jar"]