function interpolateColor(color1, color2, factor) {
    const result = color1.slice();
    for (let i = 0; i < 3; i++) {
        // interpolate each channel: r, g, b
        result[i] = Math.round(result[i] + factor * (color2[i] - color1[i]));
    }
    return result;
}

function rgbToHex(rgb) {
    return (
        "#" +
        rgb
            .map(x => {
                const hex = x.toString(16);
                return hex.length === 1 ? "0" + hex : hex;
            })
            .join("")
    );
}

export default function temperatureToColor(temp) {
    // clamp temperature between 0 and 90
    temp = Math.min(Math.max(temp, 0), 90);

    // Define color stops in RGB
    const purple = [75, 0, 130]; // #4B0082
    const green = [0, 255, 0];   // #00FF00
    const red = [255, 0, 0];     // #FF0000

    if (temp <= 50) {
        // interpolate purple -> green
        const factor = temp / 50;
        const color = interpolateColor(purple, green, factor);
        return rgbToHex(color);
    } else {
        // interpolate green -> red
        const factor = (temp - 50) / 40; // 50 to 90 range
        const color = interpolateColor(green, red, factor);
        return rgbToHex(color);
    }
}