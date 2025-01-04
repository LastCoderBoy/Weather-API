# 🌤️ WeatherApp Backend

A robust Spring Boot application providing real-time weather data with a focus on simplicity and reliability.

## 🌟 Overview

WeatherApp Backend is designed to deliver accurate weather information through a clean REST API interface. Whether you need weather data for a specific city or want to explore weather conditions in random locations worldwide, our application has got you covered.

## 🛠️ Features

### Core Functionality
- 🎲 **Random City Weather**: Get weather updates from random cities across the globe
- 🔍 **City Search**: Fetch weather data for any specific city
- 🌎 **Global Coverage**: Access to worldwide city database
- 📊 **Comprehensive Data**: Detailed weather metrics including temperature, humidity, and wind speed

### Weather Information Provided
- Temperature readings
- Humidity levels
- Wind speed measurements
- City and country identification

## 💻 Technology Stack

- **Framework**: Spring Boot
- **Language**: Java
- **API Integration**: RestTemplate
- **Data Processing**: Jackson JSON Parser

## ⚙️ Configuration

### Application Properties Setup

Add the following to your `application.properties`:
```properties
weather.api.key=your_weatherstack_api_key
weather.api.url=http://api.weatherstack.com/
```

## 📡 API Documentation

### 1. Random City Weather Endpoint

#### Request
```http
GET /api/v1/weather
```

#### Response
```json
{
    "temperature": 25.0,
    "humidity": 60.0,
    "windSpeed": 12.0,
    "country": "USA",
    "city": "New York"
}
```

### 2. City Search Endpoint

#### Request
```http
GET /api/v1/weather/city?location={city_name}
```

#### Parameters
| Parameter | Type   | Description        | Required |
|-----------|--------|--------------------|----------|
| location  | String | Name of the city   | Yes      |

#### Response
```json
{
    "temperature": 18.0,
    "humidity": 75.0,
    "windSpeed": 10.0,
    "country": "UK",
    "city": "London"
}
```

## 🛡️ Error Handling

The API implements robust error handling:

| Status Code | Description           | Scenario                    |
|-------------|--------------------|---------------------------|
| 400         | Bad Request        | Invalid city name         |
| 404         | Not Found          | City data unavailable     |
| 500         | Internal Error     | Server processing failure |

## 🚀 Getting Started

1. Clone the repository
2. Configure application.properties with your API credentials
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

## 🤝 Contributing

We welcome contributions! Please feel free to submit a Pull Request.

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.
