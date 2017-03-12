FROM java
MAINTAINER Philip Wizenty <philipnils.wizenty@fh-dortmund.de>

# update sources
RUN echo "upgrade apt sources"
RUN apt-get update
RUN apt-get upgrade -y

# Add microservice jar-file to container
ADD target/gatewayservice-1.4.2.RELEASE.jar .
CMD /usr/bin/java -Xmx400m -Xms400m -jar gatewayservice-1.4.2.RELEASE.jar

# Expose Port for Docker
EXPOSE 8080