FROM openjdk:17
VOLUME /tmp
EXPOSE 9090
COPY demo-1.0.0.jar /demo-1.0.0.jar
ENTRYPOINT ["java","-jar","demo-1.0.0.jar"]