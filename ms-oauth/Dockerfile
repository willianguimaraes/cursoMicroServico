FROM openjdk:11
VOLUME /tmp
ADD ./target/oauth-0.0.1-SNAPSHOT.jar ms-oauth.jar
ENTRYPOINT ["java","-jar","/ms-oauth.jar"]