/**
 *  Weather Station
 *
 *  Author: jnovack@gmail.com
 *  Date: 2013-08-15
 *  Code: https://github.com/smartthings-users/device-type.weatherstation
 *
 * Copyright (C) 2013 Justin J. Novack <jnovack@gmail.com>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this
 * software and associated documentation files (the "Software"), to deal in the Software
 * without restriction, including without limitation the rights to use, copy, modify,
 * merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following
 * conditions: The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

preferences {
    input("zipcode", "text", title: "ZipCode", description: "ZipCode of Forecast (leave blank to autodetect)")
}

metadata {
    definition (name: "Weather Station", author: "jnovack@gmail.com") {
      capability "Polling"
      capability "Relative Humidity Measurement"
      capability "Water Sensor"
      capability "Temperature Measurement"
    }

    simulator {
        // TODO: define status and reply messages here
    }

    tiles {
        // First Row
        valueTile("temperature", "device.temperature", width: 1, height: 1, canChangeIcon: true) {
            state("temperature", label: 'Outside\n${currentValue}°F', unit:"F", backgroundColors: [
                    [value: 31, color: "#153591"],
                    [value: 44, color: "#1e9cbb"],
                    [value: 59, color: "#90d2a7"],
                    [value: 74, color: "#44b621"],
                    [value: 84, color: "#f1d801"],
                    [value: 95, color: "#d04e00"],
                    [value: 96, color: "#bc2323"]
                ]
            )
        }
        valueTile("humidity", "device.humidity", inactiveLabel: false, decoration: "flat") {
            state "default",  label:'${currentValue}%', unit: "Humidity"
        }
        valueTile("feels_like", "device.feels_like", inactiveLabel: false, decoration: "flat") {
            state "feels_like", label: '${currentValue}ºF', unit: "Feels Like"
        }

        // Second Row
        standardTile("forecast", "device.forecast", inactiveLabel: false, decoration: "flat") {
            state "default",        label: 'updating...',        icon: "st.unknown.unknown.unknown"
            state "chanceflurries", label: 'Chance of Flurries', icon: "st.Weather.weather6"
            state "chancerain",     label: 'Chance of Rain',     icon: "st.Weather.weather9"
            state "chancesleet",    label: 'Chance of Sleet',    icon: "st.Weather.weather6"
            state "chancesnow",     label: 'Chance of Snow',     icon: "st.Weather.weather6"
            state "chancetstorms",  label: 'Chance of TStorms',  icon: "st.Weather.weather9"
            state "clear",          label: 'Clear',              icon: "st.Weather.weather14"
            state "cloudy",         label: 'Cloudy',             icon: "st.Weather.weather15"
            state "flurries",       label: 'Flurries',           icon: "st.Weather.weather6"
            state "fog",            label: 'Fog',                icon: "st.Weather.weather13"
            state "hazy",           label: 'Hazy',               icon: "st.Weather.weather13"
            state "mostlycloudy",   label: 'Mostly Cloudy',      icon: "st.Weather.weather15"
            state "mostlysunny",    label: 'Mostly Sunny',       icon: "st.Weather.weather11"
            state "partlycloudy",   label: 'Partly Cloudy',      icon: "st.Weather.weather11"
            state "partlysunny",    label: 'Partly Sunny',       icon: "st.Weather.weather11"
            state "sleet",          label: 'Sleet',              icon: "st.Weather.weather10"
            state "rain",           label: 'Rain',               icon: "st.Weather.weather10"
            state "snow",           label: 'Snow',               icon: "st.Weather.weather7"
            state "sunny",          label: 'Sunny',              icon: "st.Weather.weather14"
            state "tstorms",        label: 'Thunder Storms',     icon: "st.Weather.weather10"
        }
        valueTile("wind_speed", "device.wind_speed", inactiveLabel: false, decoration: "flat") {
            state "wind_speed", label: '${currentValue}', unit: "mph", backgroundColors: [
                // Values and colors based on the Beaufort Scale
                // http://en.wikipedia.org/wiki/Beaufort_scale#Modern_scale
                [value: 0,  color: "#ffffff"],
                [value: 1,  color: "#ccffff"],
                [value: 4,  color: "#99ffcc"],
                [value: 8,  color: "#99ff99"],
                [value: 13, color: "#99ff66"],
                [value: 18, color: "#99ff00"],
                [value: 25, color: "#ccff00"],
                [value: 31, color: "#ffff00"],
                [value: 39, color: "#ffcc00"],
                [value: 47, color: "#ff9900"],
                [value: 55, color: "#ff6600"],
                [value: 64, color: "#ff3300"],
                [value: 74, color: "#ff0000"]
            ]
        }
        valueTile("wind_direction", "device.wind_direction", inactiveLabel: false, decoration: "flat") {
            state "wind_direction", label: '${currentValue}', unit: "Direction"
        }

        // Third Row
        valueTile("uv_index", "device.uv_index", inactiveLabel: false, decoration: "flat") {
            state "uv_index", label: '${currentValue}', unit: "UV Index"
        }
        standardTile("water", "device.water", inactiveLabel: false, decoration: "flat") {
            state "default", label: 'updating...', icon: "st.unknown.unknown.unknown"
            state "true",    label:'Wet',          icon: "st.alarm.water.wet",        backgroundColor:"#ff9999"
            state "false",   label:'Dry',          icon: "st.alarm.water.dry",        backgroundColor:"#99ff99"
        }
        valueTile("location", "device.location", inactiveLabel: false, decoration: "flat") {
            state "location", label: '${currentValue}', unit: "ZipCode"
        }

        // Fourth Row
        standardTile("refresh", "device.refresh", inactiveLabel: false, decoration: "flat") {
            state "default", action:"polling.poll", icon:"st.secondary.refresh"
        }

        // This tile will be the tile that is displayed on the Hub page.
        main "temperature"

        // These tiles will be displayed when clicked on the device, in the order listed here.
        details(["temperature", "humidity", "feels_like", "forecast", "wind_speed", "wind_direction", "uv_index", "water", "location", "refresh"])
    }
}

// As an object that can be polled, the poll() function will be called on each polling cycle.
def poll() {
    def weather

    // If there is a zipcode defined, use it, otherwise SmartThings will determine your current location from the Hub.
    if (settings.zipcode) {
        log.debug "ZipCode: ${settings.zipcode}"
        weather = getWeatherFeature( "conditions", settings.zipcode )
    } else {
        log.debug "ZipCode: (automatic)"
        weather = getWeatherFeature( "conditions" )
    }

    // Check if the variable is populated, otherwise return.
    if (!weather) {
        log.debug( "Something went wrong, no data found." )
        return false
    }

    // UV Index
    log.debug( "UV Index: ${weather.current_observation.UV}" )
    sendEvent( name: 'uv_index', value: weather.current_observation.UV )

    // Forecast
    log.debug( "Forecast: ${weather.current_observation.icon}" )
    sendEvent( name: "forecast", value: weather.current_observation.icon )

    // Wind
    log.debug( "Wind Speed: ${weather.current_observation.wind_mph} mph")
    sendEvent( name: "wind_speed", value: weather.current_observation.wind_mph )

    log.debug( "Wind Direction: ${weather.current_observation.wind_dir}" )
    sendEvent( name: "wind_direction", value: weather.current_observation.wind_dir )

    // Set the tiles
    log.debug( "Current Temperature: ${weather.current_observation.temp_f}ºF" )
    sendEvent( name: 'temperature', value: weather.current_observation.temp_f )

    // Sending a value to a valueTile REQUIRES an Integer (on Android, for now)
    log.debug( "Relative Humidity: ${weather.current_observation.relative_humidity.tokenize('%')[0].toInteger()}" )
    sendEvent( name: 'humidity', value: weather.current_observation.relative_humidity.tokenize('%')[0].toInteger() )

    log.debug( "Feels Like: ${weather.current_observation.feelslike_f}" )
    sendEvent( name: 'feels_like', value: weather.current_observation.feelslike_f )

    // Something that looks like an integer will get converted to an integer
    log.debug( "Location: ${weather.current_observation.display_location.zip.toString()}" )
    sendEvent( name: 'location', value: weather.current_observation.display_location.zip.toString())

    // Since precip_1hr_in is a string, we need to convert it to a decimal in order to compare it as a number.
    if (weather.current_observation.precip_1hr_in.toFloat() > 0) {
        log.debug( "Precipitation: ${weather.current_observation.precip_1hr_in}" )
        sendEvent( name: 'water', value: "true" )
    } else {
        log.debug( "Precipitation: None" )
        sendEvent( name: 'water', value: "false" )
    }
}

def cToF(temp) {
    return temp * 1.8 + 32
}

def fToC(temp) {
    return (temp - 32) / 1.8
}
