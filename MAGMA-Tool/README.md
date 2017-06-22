# MAGMA-Tool
This repository contains the MAGMA-Tool.

## Getting started
### Installation
1. Requirements for using the MAGMA-Tool</br>
1.1 Maven is installed and the M2_HOME variable is set.</br>
1.2 The java development kit is installed.</br>
1.3 Now you can start using the MAGMA-Toll</br>

###Usage of the generated infrastructure
1. The User Management Service and the Authentication Service needs a MySQL database to run properly. The database can be installed localy or via docker with the following command:
```
docker run -p 3306:3306 --name mysqlNew1 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=usermanagement -e MYSQL_ROOT_HOST=172.17.0.1 -d mysql/mysql-server
```
2. If the User Management Service starts up for the first time, the database schema is created automatically. For the usage of the User Management Service a user with the username "admin" is needed. For this purpose run the following command:
```sql
insert into users (id, email, firstname, password, surname, username) VALUES (1, 'admin@demo.de', 'Philip', 'password', 'Alkestis', 'admin');
```
3. Now the Microservice infrastructure is ready to use

4. The Usage of the MAGMA-Tool is shown in the following Youtube-Video:
Youtube-Video: <a href="https://youtu.be/PmvSKZvJtMQ" title="Youtube">youtube_MAGMA-Tool</a>
