package com.example.weatherapp.model.weather

data class Weather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherInfo>,
    val message: Int
)


val dummyWeather = Weather(
    city = City(
        coord = Coord(lat = 37.7749, lon = -122.4194),
        country = "US",
        id = 5391959,
        name = "San Francisco",
        population = 883305,
        sunrise = 1680140400,
        sunset = 1680183600,
        timezone = -25200
    ),
    cnt = 5,
    cod = "200",
    list = listOf(
        WeatherInfo(
            clouds = Clouds(all = 20),
            dt = 1680178800,
            dt_txt = "2024-03-29 18:00:00",
            main = Main(
                feels_like = 15.0,
                grnd_level = 1012,
                humidity = 60,
                pressure = 1016,
                sea_level = 1020,
                temp = 16.5,
                temp_kf = 0.0,
                temp_max = 17.0,
                temp_min = 15.0
            ),
            pop = 10,
            sys = Sys(pod = "d"),
            visibility = 10000,
            weather = listOf(
                WeatherX(
                    description = "clear sky",
                    icon = "01d",
                    id = 800,
                    main = "Clear"
                ),
            ),
            wind = Wind(deg = 270, gust = 3.2, speed = 2.5)
        ),
        WeatherInfo(
            clouds = Clouds(all = 90),
            dt = 1680189600,
            dt_txt = "2024-03-29 21:00:00",
            main = Main(
                feels_like = 14.2,
                grnd_level = 1010,
                humidity = 65,
                pressure = 1014,
                sea_level = 1018,
                temp = 15.0,
                temp_kf = 0.0,
                temp_max = 15.5,
                temp_min = 14.5
            ),
            pop = 30,
            sys = Sys(pod = "n"),
            visibility = 8000,
            weather = listOf(
                WeatherX(
                    description = "overcast clouds",
                    icon = "04n",
                    id = 804,
                    main = "Clouds"
                )
            ),
            wind = Wind(deg = 260, gust = 5.0, speed = 3.0)
        )
    ),
    message = 0
)
