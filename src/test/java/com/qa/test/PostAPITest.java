package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends TestBase {

	TestBase testBase;
	String serviceUrl;
	String apiUrl,url;
	RestClient restClient;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	
	public void setUp() throws IOException {
		testBase = new TestBase();
		serviceUrl = prop.getProperty("URL");
		apiUrl = prop.getProperty("serviceURL");
		url = serviceUrl+apiUrl;
		
	}
	
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException {
		
		restClient = new RestClient();
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		
		//Jackson API required for Marshalling and un mArshalling
		// Add Jackson dependency in POM.xml
		// This is for conversion of class object to JSoN object 
		
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader");//Expected users object
		
		//Object to JSON File conversion:
		mapper.writeValue(new File("/Users/gurvarindersingh/Desktop/SeleniumTesting/RestAPITest/src/main/java/com/qa/data/users.json"), users);
		
		//Object to json in String---->Marshalling
		
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		closeableHttpResponse = restClient.post(url, usersJsonString, headerMap);
		
		
		//1. Status Code
		int status = closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(status, testBase.RESPONSE_STATUS_CODE201);
		
		//2. JsonString---->Unmarshalling
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		
		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is-->"+responseJson);
		
		Users usersResObj = mapper.readValue(responseString, Users.class); // Actual users object
		System.out.println(usersResObj);
		Assert.assertTrue(users.getName().equals(usersResObj.getName()));
		Assert.assertTrue(users.getJob().equals(usersResObj.getJob()));
		
		System.out.println(usersResObj.getCreatedAt());
		System.out.println(usersResObj.getId());
	}
}
