#FROM openjdk:8-jre-alpine

#COPY target/*.war /app.war

#CMD ["/usr/bin/java", "-jar", "/app.war"]

FROM tomcat:9.0

#RUN rm -fr /usr/local/tomcat/webapps/ROOT

COPY target/messenger*.war /usr/local/tomcat/webapps/messenger.war