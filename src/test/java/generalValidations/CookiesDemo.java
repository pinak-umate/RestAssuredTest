package generalValidations;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

public class CookiesDemo {
	
	
//	@Test (priority=1)
//	void testcookies() {
//		
//		given()
//			
//		.when()
//			.get("https://www.google.com")
//		
//		.then()
//			.cookie("AEC","AZ6Zc-V-YNzKcSJxs_cK3NsQP82GOv8UkfPNKKcyMQJN9asT7ZnJZ8yjbgg")
//			.log().all();
//	}
	
	@Test (priority=2)
	void testcookiesInfo() {
		
		Response res = given()
			
		.when()
			.get("https://www.google.com");
			
		//get single cookie info
		//String cookie_value = res.getCookie("AEC");
		//System.out.println("Value of cookie is====>"+cookie_value);
		
		// get all cookies info
		Map<String,String> cookies_values = res.getCookies();
		
		System.out.println(cookies_values.keySet());
		
		for(String k:cookies_values.keySet()) {
				String cookie_value=res.getCookie(k);
				System.out.println(k+"  "+cookie_value);
		}
		
	}
	
}
