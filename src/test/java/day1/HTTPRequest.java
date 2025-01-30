package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

/*
given()
    All the prerequisites are written here
    content type, set cookies, add auth, add params, set headers info etc
when()
    All requests are written inside the when
    get, put,post, delete
then()
    All the validations are written here
    validate status code , extract response , extract headers cookies and response body etc
 */
public class HTTPRequest
{
    int id;
    @Test(priority = 1)
    void getUsers()
    {
        given()

                .when()
                    .get("https://reqres.in/api/users?page=2")
                //validates only
                .then()
                    .statusCode(200)
                    .body("page",equalTo(2))
                    .log().all();
    }

    @Test(priority = 2)
    void createUser()
    {
        // we are hard coding data here which is not recommended
        // Hashmap is suitable for only small set of data
        HashMap data = new HashMap<>();
        data.put("name","virender");
        data.put("job","IT Employee");

        id=given()
                .contentType("application/json")
                .body(data)

                .when()
                    .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

                //validates only
//                .then()
//                    .statusCode(201)
//                    .log().all();
    //this method will create "id" in response and using that id we can update or delete the data

    }

    @Test(priority = 3, dependsOnMethods = {"createUser"})
    void updateUser()
    {
        HashMap data = new HashMap();
        data.put("name"," bebidoll");
        data.put("job","yuvika ki seva");

        given()
                    .contentType("application/json")
                    .body(data)
                .when()
                    .put("https://reqres.in/api/users/+id")

                .then()
                    .statusCode(200)
                .log().all();

    }

    @Test(priority = 4)
    void deleteUser()
    {
        given()
                .when()
                    .delete("https://reqres.in/api/users/+id")
                .then()
                    .statusCode(204)
                    .log().all();

    }

}
