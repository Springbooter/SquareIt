## SquareIt


#### Description

Saves and gets a number from a mySQL database and squares it.

The project have two rest-services which saves and gets a number from
a database. One rest-service saves a number that is supplied
through a front-end. The other rest-service takes an id number from the
front-end, looks in the database for that id, takes that number and
squares it before returning it to the front-end for presentation. 
The project uses an in-memory database (H2) to simulate a database 
during testing and a mySQL database after testing for saving and getting
the numbers. Using Liquibase for making and modifying tables in the 
database.


#### Swagger UI - localhost

http://localhost:8080/swagger-ui.html


#### Problems and installation

:::: What have been done, works, and does not work. ::::

1. Front-end.
  <br>a) Works well. It does not look pretty. It is just there to 
  be functionall. It sends and gets numbers from the back-end. 
  Look at the other repository for it.
  
2. Back-end.
  <br>a) Works well. Does send and get numbers to the front-end 
  when running spring-boot:run.
  <br>b) Does not work well with the real database. Seems to be 
  only using the H2 database at all times.
  
3. Database.
  <br>a) Installation:
    1. Installed mySQL with a full installation.
    2. Has set admin as username "root" and password "root".
    3. In mySQL Workbench user "root" was selected and had
    already all admin roles. Was granted all schema privileges.
    4. Made a database with 'localhost' and called it "squareit"
    at port "3306".
    5. Made a table in "squareit" called "numbers" with two
    columns. First column named "id" as "BIGINT(8)", PK, NN,
    UQ, AI. The second column named "number" as "INT(11)", nn.
    
  b) Works well to start/connect to. Can insert values through 
  queries in mySQL Workbench in the database "squareit" in its 
  table 'numbers'. The columns are being filled with values.
  
  c) Does not work well with the back-end. The database does 
  not take in values from the back-end. Liquibase does not make
  its two own columns. The back-end does not connect to the 
  database.
