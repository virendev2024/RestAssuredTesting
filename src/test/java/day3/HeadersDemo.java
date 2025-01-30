package day3;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.useRelaxedHTTPSValidation;

public class HeadersDemo
{
    // value of few header elements remains same(eg. content type , content encoding , server) , we need to validate that only
    // 1. verifying header value
//    @Test(priority = 1)
    void headersDemo() {
        useRelaxedHTTPSValidation(); // Disable SSL verification
        given()
                .when()
                .get("https://www.google.com/")
                .then()
//                    .header("Content-Type","text/html; charset=utf-8")
                .header("Server", "ESF")
                .log().all();

    }

    // 2. get header content
//    @Test(priority = 1)
    void getHeader() {
        useRelaxedHTTPSValidation(); // Disable SSL verification
        given()
                .when()
                .get("https://www.google.com/")
                .then()
//                    .header("Content-Type","text/html; charset=utf-8")
                .header("Server", "ESF")
                .log().all();

    }

    @Test(priority = 3)
    void getHeaders()
    {
        useRelaxedHTTPSValidation(); // Disable SSL verification
        Response res = given()
                .when()
                .get("https://www.google.com/");

        //get all headers
        Headers myheaders = res.getHeaders();

        for(Header hdr: myheaders)
        {
            System.out.println(hdr.getName()+" "+ hdr.getValue());
        }

    }

}

