<p align="center">
  <img src=".github/header.svg" alt="Sendly Java OTP Example" />
</p>


# Sendly Java OTP Verification Example

A complete Spring Boot application demonstrating phone number verification using Sendly's OTP service.

## Features

- Send OTP verification codes to phone numbers
- Verify OTP codes
- Clean, modern web interface
- RESTful API endpoints
- Spring Boot 3.2
- Sendly Java SDK integration

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Sendly API key ([Get one here](https://sendly.live))

## Setup

1. Clone or download this project

2. Configure your Sendly API key in `src/main/resources/application.properties`:
```properties
sendly.api-key=your_sendly_api_key_here
```

3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

5. Open your browser to `http://localhost:8080`

## API Endpoints

### Send OTP
```http
POST /api/send-otp
Content-Type: application/json

{
  "phone": "+1234567890"
}
```

Response:
```json
{
  "success": true,
  "verificationId": "ver_xxx",
  "message": "OTP sent successfully"
}
```

### Verify OTP
```http
POST /api/verify-otp
Content-Type: application/json

{
  "verificationId": "ver_xxx",
  "code": "123456"
}
```

Response:
```json
{
  "success": true,
  "message": "Phone number verified successfully"
}
```

## Project Structure

```
src/
├── main/
│   ├── java/com/example/otp/
│   │   ├── OtpApplication.java       # Main Spring Boot application
│   │   ├── OtpController.java        # REST API endpoints
│   │   └── SendlyConfig.java         # Sendly client configuration
│   └── resources/
│       ├── static/
│       │   ├── index.html            # Phone input page
│       │   └── verify.html           # OTP verification page
│       └── application.properties    # Configuration
└── pom.xml                           # Maven dependencies
```

## How It Works

1. User enters their phone number on the home page
2. Application calls Sendly's `verify().send()` API to send an OTP
3. User is redirected to the verification page
4. User enters the received OTP code
5. Application calls Sendly's `verify().check()` API to verify the code
6. Success or error message is displayed

## Dependencies

- Spring Boot 3.2.0
- Sendly Java SDK 3.8.1
- Spring Web

## Learn More

- [Sendly Documentation](https://docs.sendly.live)
- [Sendly Java SDK](https://github.com/sendly-so/sendly-java)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)

## License

MIT
