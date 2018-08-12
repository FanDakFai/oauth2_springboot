# INTRODUCTION
An authorization server (oauth2) implemented by Spring Boot. This authorization server supports:
* Let user (Resource owner) login to obtain access token key
* Let url_management (Resource Server) do authorization by retreiving database via access token key


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

## Get access token or code from browser
* Open url by browser (Firefox or Chrome...):
    * For admin scope code: https://localhost:8081/oauth/authorize?response_type=code&client_id=adminclient
    * For advance scope code: https://localhost:8081/oauth/authorize?response_type=code&client_id=dnsclient
    * For guest access token (auto approve): https://localhost:8081/oauth/authorize?response_type=token&client_id=guest

## To get access token from access code (client secret code is required)
* Launch: convert_code2token.py <GrantedCode> <clientId> <clientPassword>
* Example:
    * For advance scope token: convert_code2token.py <GrantedCode> dnsclient secret
    * For standard scope token: convert_code2token.py <GrantedCode> guest guest
    * For admin scope token: convert_code2token.py <GrantedCode>

## To manage accounts (accounts C.R.U.D usecases)
* Login with user 'system' password harded code in class SystemUser.password via url: https://localhost:8081/accounts

# APPENDIX
T.B.D

