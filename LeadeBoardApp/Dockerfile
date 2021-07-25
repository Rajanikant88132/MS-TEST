FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
# Copy the project source
USER root
RUN mkdir -p /upload-dir
RUN chown spring:spring /upload-dir
COPY upload-dir/demodb.mv.db /upload-dir/demodb.mv.db
COPY upload-dir/demodb.trace.db /upload-dir/demodb.trace.db
USER spring
ENTRYPOINT ["java","-jar","/app.jar"]