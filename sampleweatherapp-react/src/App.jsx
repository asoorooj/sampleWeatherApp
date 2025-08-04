import { useState, useEffect } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import temperatureToColor from "./color.jsx";'./color.jsx'
import VantaBackground from "./vantaClouds.jsx";
import {BeatLoader} from "react-spinners";

function App() {
  const [count, setCount] = useState(0)

    const [weather, setWeather] = useState(null);
    const [error, setError] = useState("");

    const latitude = 40.7306;
    const longitude = -73.9352;

    // const latitude = 40.7306;
    // const longitude = -173.9352;

    useEffect(() => {
        const fetchWeather = async () => {
            try {
                const res = await fetch(`http://localhost:8080/api/weather?latitude=${latitude}&longitude=${longitude}`);
                if (!res.ok) {
                    throw new Error("Weather data fetch failed");
                }
                const data = await res.json();
                    console.log(res)
                    console.log(data)
                    console.log(data.name)
                setWeather(data);
            } catch (err) {
                console.log(err)
                setError(err.message);
            }
        };

        fetchWeather();
    }, []);

  return (
    <>
        {weather ?
            <div style={{ position: "relative", height: "100vh", overflow: "hidden", display: "flex", justifyContent: "center", alignItems: "center" }}>
                <div style={{ position: "fixed", top: 0, left: 0, zIndex: -1 }}>
                    <VantaBackground />
                </div>

                <div className="card"  style={{ position: "relative", zIndex: 1, padding: "2rem", color: "#fff", top: "-5%",
                    "--hover-shadow-color": `${temperatureToColor(weather.temperature)}`}}>

                    <div>
                        <div style={{marginBottom: "2em"}}>
                            <div>
                                <text style={{fontWeight: "bold", fontSize: "54px"}}>{weather.name}</text>
                            </div>
                            <div>
                                <text style={{fontSize: "28px"}}>{weather.startTime.substring(0,10)}</text>
                            </div>
                        </div>
                        <div style={{justifyContent: "center", marginBottom: "1rem"}}>
                            <div style={{marginBottom: "1em"}}>
                                <text style={{fontSize: "28px"}}>{weather.dayTime ? "Day Time ☀️" : "Night Time 🌙"}</text>
                            </div>
                            <div style={{marginBottom: "1em"}}>
                                <img src={weather.icon} alt="react.svg" style={{borderRadius: "10px"}}/>
                            </div>
                            <div style={{marginBottom: ""}}>
                                <text style={{fontWeight: "500", fontSize: "34px"}}>{weather.shortForcast}</text>
                            </div>
                            <div style={{marginBottom: "1em"}}>
                                <text style={{fontWeight: "400", fontSize: "36px"}}>{weather.temperature}° {weather.temperatureUnit}</text>
                            </div>
                            <div style={{width: "50%", margin: "auto"}}>
                                <text style={{fontWeight: "300", fontSize: "20px"}}>{weather.detailedForcast}</text>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

                :

                error ?

                    <div style={{ position: "relative", height: "100vh", overflow: "hidden", display: "flex", justifyContent: "center", alignItems: "center" }}>
                        <div style={{ position: "fixed", top: 0, left: 0, zIndex: -1 }}>
                            <VantaBackground />
                        </div>
                        <div>
                            <div style={{margin: "2rem"}}>
                                <text style={{fontSize: "24px", color: "white"}}>{error} (try reloading)</text>
                            </div>
                        </div>
                    </div>

                    :

                    <div style={{ position: "relative", height: "100vh", overflow: "hidden", display: "flex", justifyContent: "center", alignItems: "center" }}>
                        <div style={{ position: "fixed", top: 0, left: 0, zIndex: -1 }}>
                            <VantaBackground />
                        </div>
                        <div>
                            <div style={{margin: "2rem"}}>
                                <text style={{fontSize: "24px", color: "white"}}>Loading... (check internet connection)</text>
                            </div>
                            <BeatLoader color="white" size={25}/>
                        </div>
                    </div>

            }
    </>
  )
}

export default App
