# User-Management-Application

This program is based on Java language. It is developed with Spring Boot and Maven. For Database I use MySQL. I hope you will like my App. I am still learning REST API-s so there is a room for improvement. 


## IMPORTANT for DB connection:

1). Check out "application.yml" file for database information. I put there my DB username: root; DB password: 1234;

2). Put your "username" and your "password" for the DB in application.yml file.

3). Check out DataBaseInit class. There you can change username, passwords, url and driver so the 2 SQL files can extract the data into your DataBase.


## Installation:

1. Download the source code.
2. Load the Project in your Integrated Development Environment (IDE). I use INTELIJ IDEA
3. Connect the project with database. (I use MySQL).
4. Press "Run" button.
5. Go to your Postman!!!


## REST API endpoints:

* I use 2 SQL files to auto-initialize some data into DataBase. If you run the program once there is no erorrs!
* If you stop it then run it again there is error -> "Entitis have duplicate IDs".
* This is because the 2 SQL files and the setting in "application.yml" -> "ddl-auto: none". So... the program is not broken. It works fine.

LINK Swagger REST API Documentation -> http://localhost:8080/swagger-ui/index.html

1. Reading all Users (GET) (NOT SORTED) -> http://localhost:8080/api/get/users
2. Reading all Users (GET) (SORTED - first by lastname, then by birth date) -> http://localhost:8080/api/get/sorted/users
3. Reading one User (GET) -> http://localhost:8080/api/get/user/1
4. Deleting a User (DELETE) -> http://localhost:8080/api/delete/user/1
5. Creating a User (POST) -> http://localhost:8080/api/post/create/user
6. Updating a User (PATCH) -> http://localhost:8080/api/patch/update/user/1
7. Search by given word (GET) -> http://localhost:8080/api/get/search/Ivanov 








