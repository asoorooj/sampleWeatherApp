import { useState, useEffect } from 'react'
import './App.css'
import temperatureToColor from "./color.jsx";
import VantaBackground from "./vantaClouds.jsx";
import {BeatLoader} from "react-spinners";

function App() {
  const [time, setTime] = useState(new Date());

    //Updates the time
    useEffect(() => {
        const intervalId = setInterval(() => {
            setTime(new Date());
        }, 1000); // Update every second

        // Cleanup function to clear the interval
        return () => clearInterval(intervalId);
    }, []);

    //useState variables
    const [weatherNow, setWeatherNow] = useState(null); //Records current weather
    const [weatherToday, setWeatherToday] = useState(null); //Records current for today
    const [weatherLocation, setWeatherLocation] = useState(null); //Records current for today
    const [errorMessage, setErrorMessage] = useState(""); //Records current error message
    const [error, setError] = useState(""); //Records current error
    const [showPopup, setShowPopup] = useState(false);

    //toggle useStates
    const [showCoordinates, setShowCoordinates] = useState(false);
    const [showMilitaryTime, setShowMilitaryTime] = useState(false);
    const [showCelcius, setShowCelcius] = useState(false);
    const [colorScheme, setColorScheme] = useState("light");

    const toggleTheme = () => {
        setColorScheme(prev => (prev === "light" ? "dark" : "light"));
    };


    const latitude = 40.7306; //given lat and lon
    const longitude = -73.9352;
    const baseURL = "http://localhost:8080/api/weather";

    //performs initial calls
    useEffect(() => {

        let retryTimeout = null;

        const fetchWeather = async () => {
            try {
                const resNow = await fetch(baseURL+`/now?latitude=${latitude}&longitude=${longitude}`); //fetches weather from api
                if (!resNow.ok) {
                    const dataNow = await resNow.json();
                    console.log(resNow)
                    console.log(dataNow)
                    setError(dataNow);
                    throw new Error("Weather data fetch failed");
                }
                const dataNow = await resNow.json();
                    console.log(resNow)
                    console.log(dataNow)
                    console.log(dataNow.name)
                setWeatherNow(dataNow);

                const resToday = await fetch(baseURL+`/today?latitude=${latitude}&longitude=${longitude}`); //fetches weather from api
                if (!resToday.ok) {
                    const dataToday = await resToday.json();
                    console.log(resToday)
                    console.log(dataToday)
                    setError(dataToday);
                    throw new Error("Weather data fetch failed");
                }
                const dataToday = await resToday.json();
                console.log(resToday)
                console.log(dataToday)
                console.log(dataToday.name)
                setWeatherToday(dataToday);

                const resLocation = await fetch(baseURL+`/location?latitude=${latitude}&longitude=${longitude}`); //fetches weather from api
                if (!resLocation.ok) {
                    const dataLocation = await resLocation.json();
                    console.log(resLocation)
                    console.log(dataLocation)
                    setError(dataLocation);
                    throw new Error("Weather data fetch failed");
                }
                const dataLocation = await resLocation.json();
                console.log(resLocation)
                console.log(dataLocation)
                console.log(dataLocation.name)
                setWeatherLocation(dataLocation);

            } catch (err) {
                console.log(err)
                setErrorMessage(err.message);
                retryTimeout = setTimeout(fetchWeather, 3000); // Retry after 3 seconds if there's an error
            }
        };

        fetchWeather();

        //resets retry timeout
        return () => {
            if (retryTimeout) clearTimeout(retryTimeout);
        };

    }, []);

    //function to convert farenheit to celcius
    function farenheitToCelcius(farenheit){
        return (farenheit - 32)*(5/9);
    }
    //function to convert farenheit to celcius
    function celciusToFarenheit(celcius){
        return (celcius/(5/9))+32;
    }


    //performs clock calculations
    let hours = time.getHours();
    let isAm = true;
        if(hours > 12){
            hours = hours-12;
            isAm = false;
        }
        if(hours == 12){
            isAm = false;
        }
    let minutes = time.getMinutes();
        if(minutes <= 9){
            minutes = "0"+minutes;
        }

  return (
    <>
        {weatherNow && weatherToday && weatherLocation ? //normal screen

            <div style={{ position: "relative", height: "90vh", width: "auto", display: "flex", justifyContent: "center", alignItems: "center", border:"0px solid black" }}>
                <div style={{ position: "fixed", top: 0, left: 0, zIndex: -1 }}>
                    <VantaBackground colorScheme={colorScheme}/>
                </div>

                <div className="card" style={{ position: "relative", zIndex: 1, padding: "2em", color: colorScheme === "dark" ? "#fff9f0" : "#fff", top: "-5%",
                    "--hover-shadow-color": `${temperatureToColor(weatherNow.temperature)}`}}>
                    <div>
                        <div style={{marginBottom: "2em"}}>
                            <div>
                                <div className={colorScheme === "dark" ? "darkModeText" : "individualText"} style={{fontWeight: "bold", fontSize: "48px", cursor: "pointer"}} onClick={() => setShowCoordinates(!showCoordinates)}>
                                    {showCoordinates ? `[${weatherLocation.coordinates[1]}, ${weatherLocation.coordinates[0]}]`: `${weatherLocation.city}, ${weatherLocation.state}` }
                                </div>
                            </div>
                            <div>
                                <div className={colorScheme === "dark" ? "darkModeText" : "individualText"} style={{fontWeight: "bold", fontSize: "54px", cursor:"pointer"}} onClick={() => setShowMilitaryTime(!showMilitaryTime)}>
                                    {showMilitaryTime ? `${time.getHours()}:${minutes}` : `${hours}:${minutes} ${isAm ? "am" : "pm"}` }
                                </div>
                            </div>
                            <div>
                                <div className={colorScheme === "dark" ? "darkModeText" : "individualText"} style={{fontSize: "28px"}}>{weatherNow.startTime.substring(0,10)}</div>
                            </div>
                        </div>
                        <div style={{justifyContent: "center", marginBottom: "1rem"}}>
                            <div style={{marginBottom: "1em"}}>
                                <div className={colorScheme === "dark" ? "darkModeText" : "individualText"} style={{fontSize: "28px"}}>{weatherNow.dayTime ? "Day Time ☀️" : "Night Time 🌙"}</div>
                            </div>
                            <div style={{marginBottom: "1em"}}>
                                <img src={weatherNow.icon} alt="react.svg" style={{borderRadius: "10px", width: "100px", height: "auto"}}/>
                            </div>
                            <div style={{marginBottom: ""}}>
                                <div className={colorScheme === "dark" ? "darkModeText" : "individualText"} style={{fontWeight: "500", fontSize: "34px"}}>{weatherNow.shortForecast}</div>
                            </div>
                            <div style={{marginBottom: "1em"}}>
                                <div className={colorScheme === "dark" ? "darkModeText" : "individualText"} style={{fontWeight: "400", fontSize: "36px", cursor:"pointer"}} onClick={() => setShowCelcius(!showCelcius)}>
                                    {showCelcius ? `${Math.round(farenheitToCelcius(weatherNow.temperature))}° C`: `${weatherNow.temperature}° ${weatherNow.temperatureUnit}`}
                                </div>
                            </div>
                            <div style={{width: "50%", margin: "auto"}}>
                                <div className={colorScheme === "dark" ? "darkModeText" : "individualText"} style={{fontWeight: "300", fontSize: "20px"}}>{weatherNow.detailedForecast}</div>
                            </div>
                        </div>
                    </div>
                    <div style={{ display: "flex", flexDirection: "column", gap: "10px", justifyContent: "center", alignItems: "center" }}>
                        <button className={colorScheme === "dark" ? "darkButton" : ""} onClick={() => setShowPopup(true)}>More Info </button>
                        <button className={colorScheme === "dark" ? "darkButton" : ""} onClick={toggleTheme}>
                            Switch to {colorScheme === "light" ? "Dark" : "Light"} Mode
                        </button>
                    </div>
                </div>

                {/* Popup Modal */}
                {showPopup && (
                    <div className="popup-overlay">
                        <div className={colorScheme === "dark" ? "darkPopup-box": "popup-box"}>
                            <div>
                                <h3 style={{border:" 0px solid black", color: colorScheme === "dark" ? "#fff9f0" : ""}}>More Weather Info</h3>
                                <div style={{border:" 0px solid gray", display:"flex", justifyContent:"center", alignItems:"center"}}>
                                    <p style={{border:" 0px solid black", width: "80%", color: colorScheme === "dark" ? "#fff9f0" : ""}}> <b>Today's Summary: </b> {weatherToday.detailedForecast}</p>
                                </div>
                                <div style={{display: "grid", gridTemplateColumns: "repeat(2, 150px)", gridTemplateRows: "repeat(2, 150px)",
                                    gap: "10px", justifyContent: "center", alignItems: "center", border: "0px solid black"}}>
                                    <div className={colorScheme === "dark" ? "darkBox": "box"}>
                                        <div className="label">Humidity</div>
                                        <div className="content">{weatherNow.relativeHumidityValue}%</div>
                                    </div>
                                    <div className={colorScheme === "dark" ? "darkBox": "box"}>
                                        <div className="label">Winds</div>
                                        <div className="content">{weatherNow.windSpeed}</div>
                                        <div className="subContent">{weatherNow.windDirection}</div>
                                    </div>
                                    <div className={colorScheme === "dark" ? "darkBox": "box"}>
                                        <div className="label">Percipitation</div>
                                        <div className="content">{weatherNow.probabilityOfPercipitation.probabilityOfPercipitationValue}%</div>
                                    </div>
                                    <div className={colorScheme === "dark" ? "darkBox": "box"}>
                                        <div className="label">Dew Point</div>
                                        <div className="content">{Math.round(celciusToFarenheit(weatherNow.dewpointValue))}° F</div>
                                        <div style={{fontSize: "20px"}}>{weatherNow.dewpointValue}° C</div>
                                    </div>
                                </div>
                            </div>
                            <button className={colorScheme === "dark" ? "darkButton" : ""} onClick={() => setShowPopup(false)} style={{marginTop: "10px"}}>Close</button>
                        </div>
                    </div>
                )}

            </div>

                :

                errorMessage ? //an error is returned

                    <div style={{ position: "relative", height: "100vh", overflow: "hidden", display: "flex", justifyContent: "center", alignItems: "center" }}>
                        <div style={{ position: "fixed", top: 0, left: 0, zIndex: -1 }}>
                            <VantaBackground />
                        </div>
                        <div>
                            <div style={{margin: "2rem", display: "flex", flexDirection: "column", alignItems: "center"}}>
                                <div style={{fontSize: "24px", color: "white"}}>{errorMessage} (try reloading)</div>
                                <div style={{fontSize: "36px", color: "white", fontWeight:"bold"}}>{error.status} {error.message}</div>
                                <div  style={{margin: "2rem"}}>
                                    <BeatLoader color="white" size={25}/>
                                </div>
                            </div>
                        </div>
                    </div>

                    : //no error or weather is returned (continuously loading)

                    <div style={{ position: "relative", height: "100vh", overflow: "hidden", display: "flex", justifyContent: "center", alignItems: "center" }}>
                        <div style={{ position: "fixed", top: 0, left: 0, zIndex: -1 }}>
                            <VantaBackground />
                        </div>
                        <div>
                            <div style={{margin: "2rem"}}>
                                {/*<text style={{fontSize: "24px", color: "white"}}>Loading... (check internet connection)</text>*/}
                            </div>
                            <BeatLoader color="white" size={25}/>
                        </div>
                    </div>

            }
    </>
  )
}

export default App
