<h1>BasicInfrastructure-Archetype</h1>

This component contains the archtype sources which are used to install the Maven archetype into your local Maven repository. It was create from the BasicInfrastrcutre-Sources.

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

3. Install the Maven archetype into your local Maven Repository. Therefore switch into the root-Directory and run the following command.

```
mvn clean install
```

4. Create a project by using the archetype is very simple, you just have to run the following command and enter some information and you are done.

```
mvn archetype:generate 
    -DarchetypeGroupId=Infrastruktur
    -DarchetypeArtifactId=Basiskomponenten-archetype 
    -DgroupId=<Your groupud> 
    -DartifactId=<Your artifactId>
```

5. Open the project with your favorite IDE and have fun.
