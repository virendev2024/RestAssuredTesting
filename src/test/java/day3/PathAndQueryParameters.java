package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PathAndQueryParameters
{
    //https://reqres.in/api/users?page=2&id=5
    // we are including "api" in "domain" only
    // domain ="https://reqres.in/api"
    // path = users  , query = page=2&id=5
    @Test
    void testPathAndQueryParameters()
    {
        given()
                .pathParam("mypath","users")    // path parameters
                .queryParam("page",2)      // query parameter
                .queryParam("id",5)         // query parameter

                .when()
                    // path parameter is just like variable we refer it with curly braces{}
                    //there is no need of defining query parameters it will go along with the request automatically
                    .get("https://reqres.in/api/{mypath}")
                .then()
                    .statusCode(200)
                    .log().all();
    }
}
