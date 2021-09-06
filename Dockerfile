FROM openjdk:8
EXPOSE 8080
ADD target/shop-it-security.jar shop-it-security.jar
ENTRYPOINT ["java", "-jar", "/shop-it-security.jar"]