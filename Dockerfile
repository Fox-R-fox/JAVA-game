# Dockerfile
FROM openjdk:17
COPY FlappyBird.java /
RUN javac FlappyBird.java
CMD ["java", "FlappyBird"]
