# Weather App

The Weather App is a simple application that provides weather information based on the user's location or a search for a specific city.

## Table of Contents

- [Installation](#installation)
- [Requirements](#requirements)
- [Features](#features)
- [API](#api)
- [License](#license)
- [Contact](#contact)

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/allyssonmast/weatherapp.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd weather-app
   ```

3. **Create the `apikey.properties` file at the root of the project and add your WeatherStack API key as follows:**

   ```properties
   API_KEY=your_api_key_here
   ```

   > **Note:** Make sure not to share your API key publicly. Add `apikey.properties` to your `.gitignore` file to ensure it is not included in commits.

4. **Compile and run the application:**

   ```bash
   ./gradlew assembleDebug
   ./gradlew installDebug
   ```

## Requirements

- Kotlin 1.9.0
- Java 17

## Features

- Automatically checks internet connectivity and user's location permission.
- Automatically fetches weather forecast based on the user's current location.
- Allows the user to search for a specific city.
- Manual refresh option available.


<video width="250" controls>
  <source src="video_exemple/exemple.webm" type="video/webm">
  Your browser does not support the video tag.
</video>


## API

This application utilizes the WeatherStack API to fetch weather data. You can sign up for a free API key on the [official website](https://weatherstack.com/documentation).

## License

This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

If you have any questions, suggestions, or issues, feel free to contact us at allysson-m@hotmail.com.

Stay updated on the project by following [our GitHub page](https://github.com/allyssonmast/weatherapp).