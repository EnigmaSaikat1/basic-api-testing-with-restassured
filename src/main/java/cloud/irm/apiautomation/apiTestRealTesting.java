package cloud.irm.apiautomation;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
public class apiTestRealTesting {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://172.16.10.36:8079/authenticate/api/v1/";

        given().log().all().queryParam("x-source-token","0769c278f069eb63654f69911193b38b6629f93757784f880205d802b8f5fb9fa31b0dbba54cc8a3ffc824893d56a973-53118396ba8b376a16d7a4dd5ef4d0f5")
                .queryParam("x-api-key","0769c278f069eb63654f69911193b38b6629f93757784f880205d802b8f5fb9fa31b0dbba54cc8a3ffc824893d56a973-53118396ba8b376a16d7a4dd5ef4d0f5").header("Content-Type", "application/json").body("{\n" +
                        "    \"username\":\"irm.admin\",\n" +
                        "    \"password\":\"welcome\"\n" +
                        "}").when().post("/user/check").then().log().all().assertThat().statusCode(403)
                .extract().response().asString();
    }

}
