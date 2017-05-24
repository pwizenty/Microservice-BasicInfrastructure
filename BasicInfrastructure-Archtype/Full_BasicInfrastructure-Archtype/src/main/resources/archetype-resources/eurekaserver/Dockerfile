FROM java
MAINTAINER Philip Wizenty <philipnils.wizenty@fh-dortmund.de>

# update sources
RUN echo "upgrade apt sources"
RUN apt-get update
RUN apt-get upgrade -y

# Add microservice jar-file to container
ADD target/eurekaserver-1.0-SNAPSHOT.jar .
CMD /usr/bin/java -Xmx400m -Xms400m -jar eurekaserver-1.0-SNAPSHOT.jar

# Expose Port for Docker
EXPOSE 8761