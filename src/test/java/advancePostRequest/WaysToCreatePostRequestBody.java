package advancePostRequest;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import org.json.JSONObject;
import org.json.JSONTokener;

//Different ways to create post request
//1) Post request body using Hashmap
//2) Post request body creation using POJO classes
//3) Post request body creation using Org.json
//4) Post request using external JSON file data

public class WaysToCreatePostRequestBody {
	
	int id;
	
	//Post request body using Hashmap
	
	@Test (priority=4)
	void testPostusingHashMap() {
		HashMap data = new HashMap();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "123456");
		
		String courseArr[] = {"C", "C++"};
		data.put("courses", courseArr);
		
		given()
			.contentType("application/json")
			.body(data)
			
		.when()
			.post("http://localhost:3000/students")
		
		.then()
			.statusCode(201)
			.body("name",equalTo("Scott"))
			.body("location",equalTo("France"))
			.body("phone", equalTo("123456"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.log().all();
	}
	
	//2) Post request body creation using Org.json
	@Test (priority=3)
	void testPostusingJSONLiabrary() {
		
		// Create a JSONObject
        JSONObject data = new JSONObject();
        data.put("name", "Scott");
        data.put("location", "France");
        data.put("phone", "123456");

        // Add courses array
        String[] coursesArr = {"C", "C++"};
        data.put("courses", coursesArr); // Directly add array

        // REST-assured request
        given()
            .contentType("application/json")
            .body(data.toString()) // Serialize JSONObject to JSON string
        .when()
            .post("http://localhost:3000/students")
        .then()
            .statusCode(201)
            .body("name", equalTo("Scott"))
            .body("location", equalTo("France"))
            .body("phone", equalTo("123456"))
            .body("courses[0]", equalTo("C"))
            .body("courses[1]", equalTo("C++"))
            .log().all();
	}
	
	// Test post using Pojo classes
	
	@Test (priority=2)
	void testPostusingPOJO() {
		Pojo_PostRequest data = new Pojo_PostRequest();
		
		data.setName("Pinak");
		data.setLocation("France");
		data.setPhone("123456");
		String coursesArr[] = {"C", "C++"};
		data.setCourses(coursesArr);
		
		// REST-assured request
        given()
            .contentType("application/json")
            .body(data)
        .when()
            .post("http://localhost:3000/students")
        .then()
            .statusCode(201)
            .body("name", equalTo("Pinak"))
            .body("location", equalTo("France"))
            .body("phone", equalTo("123456"))
            .body("courses[0]", equalTo("C"))
            .body("courses[1]", equalTo("C++"))
            .log().all();
	}
	
	
	// Test post using external json file
	@Test (priority=1)
	void testPostusingExternalJsonFile() throws FileNotFoundException {
		
		File f = new File(".\\body.json");
		FileReader fr = new FileReader(f);
		
		JSONTokener jt =  new JSONTokener(fr);
		
		JSONObject data = new JSONObject(jt);
		
		
		// REST-assured request
        given()
            .contentType("application/json")
            .body(data.toString())
        .when()
            .post("http://localhost:3000/students")
        .then()
            .statusCode(201)
            .body("name", equalTo("Sakshi"))
            .body("location", equalTo("Nagpur"))
            .body("phone", equalTo("567567"))
            .body("courses[0]", equalTo("C"))
            .body("courses[1]", equalTo("C++"))
            .header("Content-Type","application/json")
            .log().all();
	}
}
