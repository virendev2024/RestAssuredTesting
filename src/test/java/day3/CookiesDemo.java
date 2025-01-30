package day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;



public class CookiesDemo {

//    @Test(priority = 1)
//    void testCookies() {
//        useRelaxedHTTPSValidation(); // Disable SSL verification
//        given()
//                .when()
//                .get("https://www.google.com/")
//                .then()
//                .cookie("AEC", "AZ6Zc-XbHh3xM8SJVWQOHbo3PrhkSoSUGG1lvYrjMkqWCPp6YZRScvIuCpg")
//                .log().all();
//    }


    // value of cookies keep changing
//    @Test(priority = 2)
    void getCookiesInfo()
    {
        useRelaxedHTTPSValidation();
        Response res= given()
                .when()
                    .get("https://www.google.com/");
        //Get single cookie info
        String cookie_val =res.getCookie("AEC");  // gives values of a single cookie
        System.out.println("value of the cookie:" + cookie_val);

        //Get all cookie info
        // Map stores data in key:value pair
        Map<String, String> get_all_cookies = res.getCookies();

        get_all_cookies.keySet(); // will give all the keys

        for(String k: get_all_cookies.keySet())
        {
            String cookie_values = res.getCookie(k);
            System.out.println(k +":" + cookie_values);

        }

    }

}