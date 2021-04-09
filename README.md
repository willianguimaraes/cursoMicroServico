# cursoMicroServico

#Criando e testando containers Docker
#Criar rede docker para sistema ms
```
docker network create ms-course-net
```
#Testando perfil dev com Postgresql no Docker
```
docker pull postgres:12-alpine

docker run -p 5432:5432 --name ms-worker-pg12 --network ms-course-net -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=db_ms_worker postgres:12-alpine &

docker run -p 5433:5432 --name ms-user-pg12 --network ms-course-net -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=db_ms_user postgres:12-alpine &
```
##ms-config-server

```
FROM openjdk:11
VOLUME /tmp
EXPOSE 8888
ADD ./target/ms-config-server-0.0.1-SNAPSHOT.jar ms-config-server.jar
ENTRYPOINT ["java","-jar","/ms-config-server.jar"]
```
```
mvnw clean package
```
```
docker build -t ms-config-server:v1 .
```
```
docker run -p 8888:8888 --name ms-config-server --network ms-course-net -e GITHUB_USER=willianguimaraes -e GITHUB_PASS= ms-config-server:v1
```
#ms-eureka-server
```
FROM openjdk:11
VOLUME /tmp
EXPOSE 8761
ADD ./target/eureka-server-0.0.1-SNAPSHOT.jar ms-eureka-server.jar
ENTRYPOINT ["java","-jar","/ms-eureka-server.jar"]
```
```
mvnw clean package
```
```
docker build -t ms-eureka-server:v1 .

docker run -p 8761:8761 --name ms-eureka-server --network ms-course-net ms-eureka-server:v1
```
#ms-worker
```
FROM openjdk:11
VOLUME /tmp
ADD ./target/ms-worker-0.0.1-SNAPSHOT.jar ms-worker.jar
ENTRYPOINT ["java","-jar","/ms-worker.jar"]
```
```
mvnw clean package -DskipTests
```
```
docker build -t ms-worker:v1 .

docker run -P --network ms-course-net ms-worker:v1
```
#ms-user
```
FROM openjdk:11
VOLUME /tmp
ADD ./target/ms-user-0.0.1-SNAPSHOT.jar ms-user.jar
ENTRYPOINT ["java","-jar","/ms-user.jar"]
```
```
mvnw clean package -DskipTests
```
```
docker build -t ms-user:v1 .

docker run -P --network ms-course-net ms-user:v1
```

#ms-payroll
```
FROM openjdk:11
VOLUME /tmp
ADD ./target/ms-payroll-0.0.1-SNAPSHOT.jar ms-payroll.jar
ENTRYPOINT ["java","-jar","/ms-payroll.jar"]
```
```
mvnw clean package -DskipTests
```
```
docker build -t ms-payroll:v1 .

docker run -P --network ms-couse-net ms-payroll:v1
```
#ms-oauth
```
FROM openjdk:11
VOLUME /tmp
ADD ./target/ms-oauth-0.0.1-SNAPSHOT.jar ms-oauth.jar
ENTRYPOINT ["java","-jar","/ms-oauth.jar"]
```
```
mvnw clean package -DskipTests
```
```
docker build -t ms-oauth:v1 .

docker run -P --network ms-couse-net ms-oauth:v1
```
#ms-api-gateway
```
FROM openjdk:11
VOLUME /tmp
EXPOSE 8765
ADD ./target/api-gateway-0.0.1-SNAPSHOT.jar ms-api-gateway.jar
ENTRYPOINT ["java","-jar","/ms-api-gateway.jar"]
```
```
mvnw clean package -DskipTests
```
```
docker build -t ms-api-gateway:v1 .

docker run -p 8765:8765 --name ms-api-gateway --network ms-course-net ms-api-gateway:v1
```
#Alguns comandos Docker

Criar uma rede Docker
```
docker network create <nome-da-rede>
```
Baixar imagem do Dockerhub
```
docker pull <nome-da-imagem:tag>
```
Ver imagens
```
docker images
```
Criar/rodar um container de uma imagem
```
docker run -p <porta-externa>:<porta-interna> --name <nome-do-container> --network <nome-da-rede> <nome-da-imagem:tag> 
```
Listar containers
```
docker ps

docker ps -a
```
Acompanhar logs do container em execução
```
docker logs -f <container-id>
```
