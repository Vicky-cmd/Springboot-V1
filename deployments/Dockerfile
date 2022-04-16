FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine    
EXPOSE 8080:8081 
RUN addgroup -S spring && adduser -S spring -G spring 
USER spring:spring  
COPY SpringbootV1-0.0.1-SNAPSHOT.war SpringbootV1-0.0.1-SNAPSHOT.war ENTRYPOINT ["java","-jar","/SpringbootV1-0.0.1-SNAPSHOT.war"]