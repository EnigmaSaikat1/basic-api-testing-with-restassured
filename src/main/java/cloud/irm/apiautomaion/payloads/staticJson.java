package cloud.irm.apiautomaion.payloads;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class staticJson {
    @Test
    public void testStaticJsonfile() throws IOException {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json").
                body(new String(Files.readAllBytes(Paths.get("./src/main/java/cloud/irm/apiautomaion/payloads/addPlace.json")))).when()
                .post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract()
                .response().asString();
    }
}
