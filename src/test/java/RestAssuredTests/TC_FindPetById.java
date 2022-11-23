package RestAssuredTests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class TC_FindPetById {

	@Test
	public void test_getPetById()
	{
		Response response = given()
							.when()
								.get("https://petstore.swagger.io/v2/pet/findByStatus?status=pending")
							.then()
							.log().body().extract().response();
		Assert.assertEquals(200,  response.statusCode());	
		String js = response.asString();
		
		//System.out.println(js.contains("pending"));
		Assert.assertEquals(js.contains("pending"), true);
	}

	}
	

