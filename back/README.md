# GameShelf backend

This application is a Java Spring Boot REST API that constitutes the backend of your GameShelf application.

## Initialization

First, create a new `.env` file at the root of this project and add the following properties :

```dotenv
DATABASE_URL=localhost:port/gameshelf
DATABASE_USERNAME=your_database_username
DATABASE_PASSWORD=your_database_password

SERVER_PORT=8081

JWT_SECRET_KEY=your_jwt_secret
```

Then you should be able to run the app.

## Documentation

Once you run the application, you can access the Swagger UI to view all endpoints with documentation and test them : [localhost:8081/api/v1/swagger-ui](http://localhost:8081/api/v1/swagger-ui)

## Testing

This application defines 62 integration tests to automatically test all endpoints to ensure code quality et maintainability.

You can run those tests by using the command :

```bash
./mvnw test
```

Those tests are using an H2 in-memory database so you do not need to have an existing database to run them.
