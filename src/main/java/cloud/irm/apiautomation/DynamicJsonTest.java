package cloud.irm.apiautomation;

import cloud.irm.apiautomaion.payloads.addBookPayloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJsonTest {
    @Test(dataProvider = "Books Data")
    public void testAddBook(String isbn, String aisle){

        RestAssured.baseURI = "https://rahulshettyacademy.com";
       String response = given().log().all().header("Content-Type", "application/json").body(addBookPayloads.addbook(isbn, aisle)).when()
                .post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract()
                .response().asString();

        JsonPath js = ReUsableMethods.rawToJson(response);
        String fetchedID = js.get("ID");
        System.out.println(fetchedID);

    }
    @DataProvider(name = "Books Data")
    public Object[][] getData(){
        return new Object[][] {{"Harry Potter and the Philosopher’s Stone", "0747532699"}, {"Baby Elephant Goes for a Swim Lap Book", "9781933624532"}, {"Berries For Baby Elephant Lap Book", "9781933624525"}};
    }
}
