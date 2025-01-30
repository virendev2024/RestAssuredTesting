package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class LoggingDemo
{
    @Test()
    void getHeader() {
        useRelaxedHTTPSValidation(); // Disable SSL verification
        given()
                .when()
                    .get("https://www.google.com/")
                .then()
//                .log().all();
//                .log().headers();
//                .log().cookies();
                .log().body();

    }
}
