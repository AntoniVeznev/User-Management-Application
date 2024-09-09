
# User-Management-Application

## What I use in the project:
1. This program is based on Java language.
2. It is developed with Spring Boot and Maven. 
3. For Database I use MySQL.
4. There is a Swagger API Documentation link below.

## Installation:
1. Download the source code.
2. Load the Project in your Integrated Development Environment (IDE). I use INTELIJ IDEA.
3. Connect the project with your DATABASE. (I use MySQL). Check out "application.yml" file for DB connection info. I put my DB username: root; DB password: 1234;
Put your "username" and "password" for your DB in application.yml file.
4. After you setup your DB go and check out folder - "initialization" and class - "DataBaseInit".
There you MUST change Username, Password, Url and DriverClassName so the 2 SQL files - "data.sql" & "schema.sql" can extract the data correctly into your DB when you start the Application.
5. Press "Run" button and the Application will start. If you are missing the run button go to the main class - "UserManagementApplication" and run it from there.
6. Go to your Postman OR use the Swagger LINK below!!!

## Important:
When you start the program for the first time it's working fine. If you re-run OR stop and run the program for the second time there is an error. This error is normal to be shown so don't panic! :) The error that is printed at the console is "Duplicate entry '1' for key 'users.PRIMARY'". This is caused by the 2 SQL files that insert automatically data in the DB when you start the program for the first time. So if you start the program again, it trys to insert the same data again but we already have the data in our DB. 
So i fix this error with a simple trick. Just drop table "users" everytime before you start or rerun the Application. :)

## REST API endpoints:
LINK Swagger REST API Documentation -> http://localhost:8080/swagger-ui/index.html

1. Reading all Users (GET) (NOT SORTED) -> http://localhost:8080/api/get/users
2. Reading all Users (GET) (SORTED - first by lastname, then by birth date) -> http://localhost:8080/api/get/sorted/users
3. Reading one User (GET) -> http://localhost:8080/api/get/user/1
4. Deleting a User (DELETE) -> http://localhost:8080/api/delete/user/1
5. Creating a User (POST) -> http://localhost:8080/api/post/create/user
6. Updating a User (PATCH) -> http://localhost:8080/api/patch/update/user/1
7. Search by given word (GET) -> http://localhost:8080/api/get/search/Iveta OR http://localhost:8080/api/get/search/0894256784 OR http://localhost:8080/api/get/search/zori_1995@abv.bg OR http://localhost:8080/api/get/search/Ivanov








