FROM openjdk:11
VOLUME /tmp
ADD ./target/ms-payroll-0.0.1-SNAPSHOT.jar ms-payroll.jar
ENTRYPOINT ["java","-jar","/ms-payroll.jar"]