package RestAssuredTests;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


import java.util.HashMap;



public class TC_TestCases {

	
	static ExtentTest logger;
	static ExtentReports report;
	static ExtentTest test;
	
	@BeforeSuite
	public static void startReports() {
		
		 ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		report = new ExtentReports();
		report.attachReporter(htmlReporter);
		
		
	}
	
	@AfterSuite
	public static void closeReports() {

		report.flush();
	}
	
	
	@Test(priority=2)
	public void test_getPetById()
	{
		test = report.createTest("test_getPetById");
		
		test.log(Status.PASS, "Test Case Execution Started");
		
		
		Response response = given()
				.when()
				.get("https://petstore.swagger.io/v2/pet/findByStatus?status=pending")
				.then()
				.log().body().extract().response();
		Assert.assertEquals(200,  response.statusCode());	
		String js = response.asString();

		//System.out.println(js.contains("pending"));
		Assert.assertEquals(js.contains("pending"), true);
		
		test.log(Status.PASS, "Test Case Execution Completed");
		
		
	}

	@Test(priority=3)
	public void test_addPet()
	{
		test = report.createTest("test_addPet");
		test.log(Status.INFO, "Test Case2 Execution Started");
		HashMap data = new HashMap();
		data.put("id", "10");
		data.put("name", RestUtils.getPetName());
		data.put("status", "available");

		Response res =
				given()
				.contentType("application/json")
				.body(data)
				.when()
				.post("https://petstore.swagger.io/v2/pet")
				.then()
				.statusCode(200)
				.log().body()
				//.log().all()
				.extract().response();
		String jsonString  = res.asString();
		Assert.assertEquals(jsonString.contains("available"), true);
		test.log(Status.PASS, "Test Case2 Execution Completed");
	}


}





