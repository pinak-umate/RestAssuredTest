package jsonResponseParsing;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;


public class ParsingJSONResponseData {
	
	@Test(priority=1)
	void testJsonResponse() {
		//Approach 1
		Response res = given()
			.contentType(ContentType.JSON)
		
		.when()
			.get("http://localhost:3000/book");
		
//		.then()
//			.statusCode(200);
		
		// Search for author in JSON
		Boolean status = false;
			
		JSONObject jo = new JSONObject(res.asString()); //converting response to Json object type
		for(int i=0;i<jo.getJSONArray("books").length();i++) {
			String author = jo.getJSONArray("books").getJSONObject(i).get("author").toString();
//			System.out.println(author);
			if(author.equals("Pinak Umate")) {
				status=true;
				break;
			}
		}
		Assert.assertEquals(status,true);
		
		//Validate Total price of books
		
		double totalprice=0;
		for(int i=0;i<jo.getJSONArray("books").length();i++) {
			String price = jo.getJSONArray("books").getJSONObject(i).get("price").toString();
			totalprice = totalprice + Double.parseDouble(price);
		}
		System.out.println("total price of books is:"+ totalprice);
		Assert.assertEquals(totalprice,690);
	}
}

