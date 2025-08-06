# Sample Weather App

This project is intended to display the capabilites of Spring Boot as a back end for api calls.
The Spring Boot app uses api.weather.gov as its data source.
A React front end provides a website to display the data Spring Boot collects 
in a comprehensible and aesthetic way for users. The website is also scaled with desktop and 
mobile users in mind, utilising native device properties.

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Sources](#sources)
- [Assumptions](#assumptions)
- [Contributing](#contributing)

## Installation

Instructions on how to install and run the project.

clone or download the github repository.

```bash
git clone https://github.com/asoorooj/sampleWeatherApp.git
cd project-name
npm install   # or pip install -r requirements.txt, etc.
```

The project is made up of two separate applications. The Back-end spring boot app handles api calls 
and the Front-end React website provides a way for users to interact with the data. Due to the purposes
of this project, they only run on localhost.

## Usage

1. Start the Spring Boot app by opening it in an IDE or code editor that supports Java and Spring Boot.

in the terminal run

```bash
mvn spring-boot:run
```

running the program will allow multiple urls to be accessed which will provide the data necessary for the React website.
Users can access it by checking these links which use their devices localhost.

```aiignore
http://localhost:8080/api/weather/now?latitude=40.7306&longitude=-73.9352
http://localhost:8080/api/weather/today?latitude=40.7306&longitude=-73.9352
http://localhost:8080/api/weather/location?latitude=40.7306&longitude=-73.9352
```

each link provides a different set of information used by the React web app.

2. Start the React web app by opening it in an IDE or code editor that supports JavaScriptXML and React.

in the terminal run

```bash
npm run dev
```

or run
```bash
npx vite --host
```
if you want to test the app using other devices.

The devices must be connected to the same network and use the hosting device's ipv4 in place of localhost.

3. Once both applications are running, you can interact with the sample web app using the React web app link.
ex.
```aiignore
http://localhost:5173
```

If any of these steps are performed incorrectly, various error messages will let the user know that something went wrong
and may direct the user to fixing the error.

## Features

- Displays weather information in New York, NY
- Summary of pertinent information
- More details section that provides nuanced information
- Light and Dark mode for user accessibility
- Error handling functionality to safely pass data and run application

## Sources

- https://api.weather.gov: to use their collection of weather data
- https://www.vantajs.com/: to use their animated website background
- https://www.davidhu.io/react-spinners: to use their live loading bars

## Assumptions

- This app is only for the NYC metropolitan area
- Web app that is comprehensible from mobile devices or desktops
- Simple weather information to be observed at a glance
- Made with Scalability in mind
- API groups similar/necessary information
- Accessibility for certain conditions (i.e. lighting or information processing)

## Contributing

Pull requests are welcome. For major changes, open an issue first to discuss what you’d like to change.

