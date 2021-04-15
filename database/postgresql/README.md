# Postgres Course Setup

## Setup the database
1. pull docker image
```
docker pull postgres
```
2. create docker container
```
docker create --name postgres-demo -e POSTGRES_PASSWORD=pass -p 5432:5432 postgres:11.5-alpine
```
3. start the container
```
docker start postgres-demo
```
4. copy table creation sql to docker
```
docker cp create_tables.sql postgres-demo:/create_tables.sql
```
5. run table creation sql
```
docker exec -it postgres-demo psql -d conference_app -f create_tables.sql -U postgres
```
6. copy data insert sql to docker
```
docker cp insert_data.sql postgres-demo:/insert_data.sql
```
7. run data insert sql
```
docker exec -it postgres-demo psql -d conference_app -f insert_data.sql -U postgres
```


## Docker Postgres Setup

Create Docker container with Postgres database:

    docker create --name postgres-demo -e POSTGRES_PASSWORD=Welcome -p 5432:5432 postgres:11.5-alpine

Start container:

    docker start postgres-demo

Stop container:

    docker stop postgres-demo

Connection Info:

    JDBC URL: `jdbc:postgresql://localhost:5432/conference_app`

    Username: `postgres`

    Password: `Welcome`

Note: This stores the data inside the container - when you delete the container, the data is deleted as well.

Connect to PSQL prompt from docker:
   docker exec -it postgres-demo psql -U postgres

## Application Database Setup

Create the Database:

    psql> create database conference_app;

Setup the Tables:

    psql -d conference_app -f create_tables.sql

Install the Data:

    psql -d conference_app -f insert_data.sql
