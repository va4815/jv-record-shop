# Record Shop

The Record Shop is a RESTful API application to manage a collection of music albums, artists and songs! 

## Project Description

The Record Shop API is designed to provide a robust backend for managing a record shop's catalog. It supports operations like creating, reading, updating, and deleting albums and artists. The application is built using Java and Spring Boot, with a focus on clean architecture and best practices.

## Technologies Used

> * Java 21
> * Spring Boot 3.4
> * Hibernate
> * Spring Data JPA
> * H2 Database (for development and testing)
> * JUnit 5 and Mockito (for testing)
> * Swagger (for API documentation)

## Features
Manage albums and artists with full CRUD operations.
Search and filter albums by various criteria.
Secure API with authentication and authorization.
Exception handling for robust error management.

## API Documentation
API documentation is available through Swagger UI at http://localhost:8080/swagger-ui/index.html.

## API Endpoints

#### Albums

* GET /albums - Retrieves a list of albums
* GET /albums/{id} - Retrieves details of a specific album by ID.
* POST /albums - Create a new album. If song is not currently in the database it will also create a new song.
* PUT /albums - Updates an existing album by ID.
* DELETE /albums/{id} - Delete an album by ID.

#### Artists

* GET /artists - Retrieves a list of artists
* GET /artists/{id} - Retrieves details of a specific artists by ID.
* POST /artists - Create a new artist
* PUT /artists - Updates an existing artist by ID.
* DELETE /artists/{id} - Delete an artist by ID.

#### Song

* GET /song - Retrieves a list of songs
* GET /song/{id} - Retrieves details of a specific song by ID.
* PUT /song - Updates an existing song by ID.
* DELETE /song/{id} - Delete a song by ID.
