package goRestApi;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;
import CreateUserWithPOJOAndLombok.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetDeleteUserTestFlow {

    public String getRandomEmailId() {
        return "testuser" + System.currentTimeMillis() + "@example.com";
    }

    @Test
    public void updateUserTest() {
        //POST- 1. Set Base URI
        RestAssured.baseURI = "https://gorest.co.in";

        // 2. Create user object with dynamic email
        User user = new User("Test User", getRandomEmailId(), "male", "active");

        // 3. Create user with POST and validate all fields using POJO getters
        Response response = given().log().all()
                .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
                .contentType(ContentType.JSON)
                .body(user)
            .when()
                .post("/public/v2/users")
            .then().log().all()
                .statusCode(201)
                .body("name", equalTo(user.getName()))
                .body("email", equalTo(user.getEmail()))
                .body("gender", equalTo(user.getGender()))
                .body("status", equalTo(user.getStatus()))
                .extract().response();

        Integer userId = response.path("id");
        System.out.println("User created successfully. ID: " + userId);

        System.out.println("-----------------");

        // 4. DELETE the user
        given().log().all()
            .header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
        .when()
            .delete("/public/v2/users/" + userId)
        .then().log().all()
            .assertThat()
            .statusCode(204);

        System.out.println("-----------------");
    }
}

