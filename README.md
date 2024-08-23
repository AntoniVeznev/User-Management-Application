# User-Management-Application

## Installation:

1. Download the source code.
2. Load the Project in your Integrated Development Environment (IDE). I use INTELIJ IDEA
3. Connect the project with database. (I use MySQL).
4. Press "Run" button.
5. Go to your Postman.

IMPORTANT for DB connection:

1). Check out "application.yml" file for database information. I put there my DB username: root; DB password: 1234;
2). Put your "username" and "password" for the DB in application.yml file.

## REST API endpoints:

1. Creating a user -> http://localhost:8080/api/create/0/Ivan/Ivanov/2000-12-30/0878495214/shadow@abv.bg

2. Reading one user -> http://localhost:8080/api/user/0

3. Reading all the users (sorted first by Last name then by Date of Birth) -> http://localhost:8080/api/users

4. Search by given word / item -> http://localhost:8080/api/search/Ivan or http://localhost:8080/api/search/0878495214


