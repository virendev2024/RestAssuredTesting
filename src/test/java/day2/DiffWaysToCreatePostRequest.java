package day2;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class DiffWaysToCreatePostRequest
{
    public DiffWaysToCreatePostRequest(){
    }

    //    @Test(priority = 1)
    void testPostUsingHashmap()
    {
        HashMap data = new HashMap<>();
        data.put("name","virender");
        data.put("location","France");
        data.put("phone","2746732");

        String coursesArr [] = {"C","C++"};   //data in array format
        data.put("courses",coursesArr);

        given()
                    .contentType("application/json")
                    .body(data)

                .when()
                    .post("http//localhost:3000/students")

                .then()
                    .statusCode(201)
                    .body("name",equalTo("virender"))
                    .body("location",equalTo("France"))
                    .body("phone", equalTo("2746732"))
                    .body("courses[0]",equalTo("C"))
                    .body("courses[1]",equalTo("C++"))
                    .header("content-Type" ,"application/json; charset=utf-8") //validating header
                    .log().all();

    }
    // deletion request

//    @Test(priority = 2)
    void testDelete()
    {
        given()
                .when()
                .delete("http//localhost:3000/students/4")   //deleting by "id =4"
                .then()
                .statusCode(200)
                .log().all();
    }

    //2. post request body using org.json library
//    @Test(priority = 1)
    void testPostJsonLibrary()
    {
        JSONObject obj = new JSONObject();
        obj.put("name","virender");
        obj.put("location","France");
        obj.put("phone","2746732");

        String[] coursesArr = {"C","C++"};
        obj.put("courses",coursesArr);

        given()
                .contentType("application/json")
                .body(obj.toString())         // changed the data type to string so that it can be passed to json
                                                // this (toString) is applied for json only

                .when()
                    .post("http//localhost:3000/students")
                .then()
                    .statusCode(201)
                    .body("name",equalTo("virender"))
                    .body("location",equalTo("France"))
                    .body("phone", equalTo("2746732"))
                    .body("courses[0]",equalTo("C"))
                    .body("courses[1]",equalTo("C++"))
                    .header("content-Type" ,"application/json; charset=utf-8") //validating header
                    .log().all();

    }

//    @Test(priority = 2)
    void testDeleteJson()
    {
        given()
                .when()
                .delete("http//localhost:3000/students/4")
                .then()
                .statusCode(200)
                .log().all();
    }

    //post request body using POJO classes
//    @Test(priority = 1)
    void testPostPOJO()
    {
        // make object of POJO class and set the values using the object
        POJOPostRequest data = new POJOPostRequest();
        data.setName("virender");
        data.setLocation("France");
        data.setPhone("2746732");

        String[] coursesArr = {"C","C++"};
        data.setCourses(coursesArr);

        given()
                    .contentType("application/json")
                .body(data)

                .when()
                    .post("http//localhost:3000/students")
                .then()
                    .statusCode(201)
                    .body("name",equalTo("virender"))
                    .body("location",equalTo("France"))
                    .body("phone", equalTo("2746732"))
                    .body("courses[0]",equalTo("C"))
                    .body("courses[1]",equalTo("C++"))
                    .header("content-Type" ,"application/json; charset=utf-8") //validating header
                    .log().all();

    }

//    @Test(priority = 2)
    void testDeletePOJO()
    {
        given()
                .when()
                .delete("http//localhost:3000/students/4")
                .then()
                .statusCode(200)
                .log().all();
    }


    //4. Post request body using external json file
    @Test(priority = 1)
    void testPostUsingExternalJsonFile() throws FileNotFoundException
    {

        File f = new File(".\\body.json");  // path of the file
        FileReader fr = new FileReader(f);          // to open the file

        // splitting the file into different tokens to get the json form of data
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt); // finally we get the data into json object

        given()
                .contentType("application/json")
                .body(data.toString())         // changed the data type to string so that it can be passed to json
                // this (toString) is applied for json only

                .when()
                .post("http//localhost:3000/students")
                .then()
                .statusCode(201)
                .body("name",equalTo("virender"))
                .body("location",equalTo("France"))
                .body("phone", equalTo("2746732"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .header("content-Type" ,"application/json; charset=utf-8") //validating header
                .log().all();

    }

}


