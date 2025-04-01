package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WeatherAPITest {
    String apiKey = "de7009f1128d38ac0aa507d701d992e3";
    String baseUrl = "https://api.openweathermap.org/data/2.5/weather";
    String lat = "12.9716";
    String lon = "77.5946"; 

    @Test
    public void validateWeatherAPIResponse() {
        Response response = RestAssured.given()
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("appid", apiKey)
                .get(baseUrl);

        response.then().statusCode(200);
        
        Assert.assertNotNull(response.jsonPath().getString("weather[0].description"), "Weather Description is null");
        Assert.assertNotNull(response.jsonPath().getString("main.temp"), "Temperature is null");
        Assert.assertNotNull(response.jsonPath().getString("main.humidity"), "Humidity is null");
        Assert.assertNotNull(response.jsonPath().getString("wind.speed"), "Wind Speed is null");
        Assert.assertNotNull(response.jsonPath().getString("sys.country"), "Country Code is null");
        Assert.assertEquals(response.jsonPath().getString("name"), "Bangalore", "City Name is incorrect");
    }
}

