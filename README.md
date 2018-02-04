## Square

#### Description

Saves and gets a number from an Oracle database and squares it.

The project has two rest-services which saves and gets a number(s) from
a database. One rest-service will save a number that is supplied
through a front-end. The other rest-service takes an id number from the
front-end, looks in the database for that id, takes that number and
squares it. Finally the value is returned to the front-end. The project
uses an in-memory database (H2) to simulate the database during testing.

#### Installation

Installation
A dependency is needed from Oracle at
http://www.oracle.com/technetwork/database/features/jdbc/
jdbc-ucp-122-3110062.html
called ojdbc8.jar. Download it to e.g. "C:\ojdbc8.jar". In the terminal
of the project write
"mvn install:install-file -DgroupId=com.oracle -DartifactId=ojdbc8
-Dpackaging=jar -Dversion=12.2.0.1 -DgeneratePom=true
-Dfile=C:\ojdbc8.jar" to install this dependency separately.

#### Swagger UI - localhost

http://localhost:8080/swagger-ui.html
