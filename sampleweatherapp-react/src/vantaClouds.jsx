import { useEffect, useState, useRef } from "react";
import VantaClouds from "vanta/src/vanta.clouds.js";
import * as THREE from "three";

const VantaBackground = () => {
    const vantaRef = useRef(null);
    const [vantaEffect, setVantaEffect] = useState(null);

    useEffect(() => {
        if (!vantaEffect && vantaRef.current) {
            setVantaEffect(
                VantaClouds({
                    el: vantaRef.current,
                    THREE,
                    mouseControls: false,
                    touchControls: false,
                    gyroControls: false,
                    minHeight: 200.0,
                    minWidth: 200.0,
                    skyColor: "#68b8d7",
                    cloudColor: "#adc1de",
                    cloudShadowColor: "#cacad2",
                    sunColor: "#ff9919",
                    sunGlareColor: "#ffffff",
                    sunlightColor: "#ff9933",
                    speed: 0.65
                })
            );
        }

        return () => {
            if (vantaEffect) vantaEffect.destroy();
        };
    }, [vantaEffect]);

    return <div ref={vantaRef} style={{ width: "100vw", height: "100vh" }} />;
};

export default VantaBackground;
