﻿# Spring Boot Polling Application

A full-featured polling application built using Spring Boot, Spring Security, MySQL, and JWT authentication. This application allows users to create polls, vote on them, and manage their profiles securely.

## Features

- User registration and login with JWT authentication
- Role-based authorization with Spring Security
- Email verification and password reset
- Create and manage polls
- Vote on polls
- View poll results and statistics
- User profile management
- Responsive design

## Technologies

- **Spring Boot 3.x** - Server side framework
- **Spring Security** - Authentication and authorization
- **Spring Data JPA** - Data persistence
- **JWT** - Token based authentication
- **MySQL** - Database (can be configured for other databases)
- **Maven** - Dependency management and build

## Getting Started

### Prerequisites

- JDK 17 or later
- Maven 3.6+
- MySQL 8.0+

### Configuration

1. **Clone the repository**

   ```bash
   git clone https://github.com/yourusername/polling-app.git
   cd polling-app
   ```

2. **Configure MySQL database**

   ```properties
   # src/main/resources/application.properties
   spring.datasource.url=jdbc:mysql://localhost:3306/polling_app?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The application will be available at `http://localhost:8080`

### Building for Production

```bash
mvn clean package
```

This will create a JAR file in the `target` directory.

## API Documentation

Once the application is running, you can access the API documentation at:

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- API Docs: `http://localhost:8080/v3/api-docs`

## Security

- Uses JWT (JSON Web Token) for authentication
- Implements Role based authorization
- Password encryption using BCrypt
- Input validation and sanitization

## Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Spring Boot and Spring Security documentation
- JWT Authentication best practices
- Modern polling application architecture
