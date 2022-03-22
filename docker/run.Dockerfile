FROM openjdk:16-slim
ARG JAR
COPY $JAR app.jar
ENV JVM_OPTS=""
ENV APP_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JVM_OPTS -jar /app.jar $APP_OPTS"]
