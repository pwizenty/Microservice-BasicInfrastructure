<h1>BasicInfrastructure-Sources</h1>

This component contains the Sources which are used in the Maven Archetype. They should be runnable from the get go. A short instruction is given below.

<h2>Getting Started</h2>

1. Install MySQL Database on your system or use the following Docker command.

```
docker run -p 3306:3306 --name mysqlNew1 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=usermanagement -e MYSQL_ROOT_HOST=172.17.0.1 -d mysql/mysql-server
```

If you use the Docker command the username is __root__ , the password is __password__ and the database is called __usermanagement__.

2. Create the basic Usermanagement user in the Database. For this purpose run the following command.

```sql
insert into users (id, email, firstname, password, surname, username) VALUES (1, 'admin@demo.de', 'Philip', 'password', 'Alkestis', 'admin');
```

3. Open the Maven project with your favorite IDE.