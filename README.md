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


1. Creating a User (POST) -> http://localhost:8080/api/create/0/Ivan/Ivanov/2000-12-30/0878495214/shadow@abv.bg
2. Reading one User (GET) -> http://localhost:8080/api/user/0
3. Reading all the Users (sorted first by Last name then by Date of Birth) (GET) -> http://localhost:8080/api/users
4. Search by given word / item (GET) -> http://localhost:8080/api/search/Ivan or http://localhost:8080/api/search/0878495214
5. Updating a User (PUT) -> http://localhost:8080/api/update/0
6. Deleting a User (DELETE) -> http://localhost:8080/api/delete/0



