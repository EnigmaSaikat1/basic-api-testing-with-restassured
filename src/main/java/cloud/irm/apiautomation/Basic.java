package cloud.irm.apiautomation;

import cloud.irm.apiautomaion.payloads.addPlaceApi;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.equalTo;

public class Basic{
	public static void main(String[] args) {
		//given -> all the input details
		//when -> Submit api	, --resource and http methods
		//then -> validate the response

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		//Add Place API
		System.out.println("============================Add Place api started========================");
		String responseAddPlaceApi = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(addPlaceApi.addPlace()).when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(Integer.parseInt("200"))
				.body("scope", containsStringIgnoringCase("app")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		System.out.println(responseAddPlaceApi);
		JsonPath jsonPath = new JsonPath(responseAddPlaceApi);
		String placeId = jsonPath.getString("place_id");
		String Id = jsonPath.getString("id");
		String reference = jsonPath.getString("reference");
		System.out.println("Place Id is: "+placeId);
		System.out.println("Id is: "+Id);
		System.out.println("Reference Id is: "+reference);
		System.out.println("============================Add Place api completed========================");
		System.out.println("============================Update Place api stared========================");
		//Update Place API
//		String responseUpadtePlaceApi =
				given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\n" +
						"\"place_id\":\""+placeId+"\",\n" +
						"\"address\":\"70 winter walk, USA\",\n" +
						"\"key\":\"qaclick123\"\n" +
						"}")
				.when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated")).extract().response().asString();

		//Get Place API

		given().log().all()
				.queryParam("key", "qaclick123")
				.queryParam("place_id", placeId)
				.when().get("/maps/api/place/get/json")
				.then().log().all() // Log response details
				.assertThat().statusCode(200) // Validate status code
				.body("address", equalTo("70 winter walk, USA")) // Validate response body
				.extract().response().asString();

//		given().log().all().queryParam("key", "qaclcik123")
//				.queryParam("place_id", placeId)
//				.when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200).body("address", equalTo("70 winter walk, USA")).extract().response().asString();

//		String getPlaceApi = given().log().all().queryParam("key", "qaclick123")
//				.queryParam("place_id",placeId)
//				.when().get("maps/api/place/get/json")
//				.then().assertThat().log().all().statusCode (200).extract().response().asString();
//		JsonPath jsonPath1 = new JsonPath(getPlaceApi);
		System.out.println("============================Update Place api completed========================");
	}
}