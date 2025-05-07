package goRestApi;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

public class GetOnlyActiveUsers {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://gorest.co.in";

        // Get only users with status = active
        Response res = given()
                                .basePath("/public/v2/users")
                                .queryParam("status", "active")
                                .contentType(ContentType.JSON)
                            .when()
                                .get()
                            .then()
                                .statusCode(200)
                                .body("size()", greaterThan(0))
                                .extract().response();
        // Print full response
        System.out.println("Response Body:");
        res.prettyPrint();

        // Extract all statuses and confirm all are "active"
        List<String> checkstatus = res.jsonPath().getList("status");

        boolean allActive = checkstatus.stream().allMatch(status -> status.equalsIgnoreCase("active"));

        if (allActive) {
            System.out.println("I find All returned users have status: active");
        } else {
            System.out.println("this is Some users are not active");
        }

        System.out.println("Number of users returned: " + checkstatus.size());
    }
}


