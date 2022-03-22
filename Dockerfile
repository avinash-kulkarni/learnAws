FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD target/learnAws-1.0.0.jar learnAws-1.0.0.jar 
ENTRYPOINT java -jar /learnAws-1.0.0.jar
