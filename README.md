# GUIDANCE


Step to build and test this demo:
* [Create postgres database](#create-postgres-databse)
* [Build demo](#build-demo)
* [Run servers](#run-servers)
* [Run client test code](#run-client-test-code)

## Create postgres databse
* Create new demo user:
    * Login as postgres: # su postgres
    * Enter command:     # createuser springbootdemo --no-createdb --encrypted --login --pwprompt --no-superuser 
        * take note your password then update file application.properties
        * make sure your pg_hba.conf configuration allow remote connection 
* Create demo databse
    * Launch psql:       # psql
    * Issue sql command: create database springbootdemodb;
    * Allow user springbootdemo manipulate database springbootdemodb:
        GRANT ALL PRIVILEGES ON DATABASE springbootdemodb to springbootdemo;

## Build demo
* Launch: # mvn package

## Run servers
* Launch: # java -jar target/authentication_server-0.0.1.jar

## Run client test code
* Launch: # ./get_oauth2_token.py

# APPENDIX
T.B.D

