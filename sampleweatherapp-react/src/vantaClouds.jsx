import { useEffect, useRef } from "react";
import VantaClouds from "vanta/src/vanta.clouds";
import * as THREE from "three";

const VantaBackground = ({ colorScheme }) => {
    const vantaRef = useRef(null);
    const vantaEffectRef = useRef(null);

    useEffect(() => {
        // Destroy previous effect
        if (vantaEffectRef.current) {
            vantaEffectRef.current.destroy();
        }

        // Create new Vanta effect with updated color scheme
        vantaEffectRef.current = VantaClouds({
            el: vantaRef.current,
            THREE,
            mouseControls: false,
            touchControls: false,
            gyroControls: false,
            minHeight: 200.0,
            minWidth: 200.0,
            skyColor: colorScheme === "dark" ? "#363645" : "#68b8d7",
            cloudColor: colorScheme === "dark" ? "#43437a" : "#adc1de",
            cloudShadowColor: colorScheme === "dark" ? "#ff0000" : "#cacad2",
            sunColor: colorScheme === "dark" ? "#d89557" : "#ff9919",
            sunGlareColor: colorScheme === "dark" ? "#d89557" : "#ffffff",
            sunlightColor: colorScheme === "dark" ? "#e8e89d" : "#ff9933",
            speed: 0.65
        });

        return () => {
            if (vantaEffectRef.current) {
                vantaEffectRef.current.destroy();
            }
        };
    }, [colorScheme]); // <-- This is the key

    return <div ref={vantaRef} style={{ width: "100vw", height: "100vh" }} />;
};

export default VantaBackground;
