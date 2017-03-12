FROM java
MAINTAINER Philip Wizenty <philipnils.wizenty@fh-dortmund.de>

# update sources
RUN echo "upgrade apt sources"
RUN apt-get update
RUN apt-get upgrade -y

# Add microservice jar-file to container
ADD target/<Name of Jar File>.jar .
CMD /usr/bin/java -Xmx400m -Xms400m -jar <Name of Jar File>.jar

# Expose Port for Docker
EXPOSE <Used Ports in Microservice>