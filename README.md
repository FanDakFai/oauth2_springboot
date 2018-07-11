************
* GUIDANCE *
************
0. Step to build and test this demo
    . Create postgres database
    . Build demo: authentication_server & 
    . Run servers: authentication_serve &
    . Run client test code:

1. Create postgres databse
    . Create new demo user:
        . Login as postgres: # su postgres
        . Enter command:     # createuser springbootdemo --no-createdb --encrypted --login --pwprompt --no-superuser 
          -> take note your password then update file application.properties
          -> make sure your pg_hba.conf configuration allow remote connection 
    . Create demo databse
        . Launch psql:       # psql
        . Issue sql command: create database springbootdemodb;
        . Allow user springbootdemo manipulate database springbootdemodb:
                             GRANT ALL PRIVILEGES ON DATABASE springbootdemodb to springbootdemo;

************
* APPENDIX *
************
1. 
