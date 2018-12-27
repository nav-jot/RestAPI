package com.qa.test;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetAPITest extends TestBase {
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
	 
	@Test(priority =2)
	public void getTest() throws Exception {
		restClient = new RestClient();
		closeableHttpResponse = restClient.get(url);
		
		//a. Status Code
		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(statuscode);
		
		Assert.assertEquals(statuscode,RESPONSE_STATUS_CODE200,"Status Code is not 200");
		 
		// b. JSON String
		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");  
		JSONObject responseJson = new JSONObject(responseString); 
		System.out.println("Response JSON from API--->"+responseJson);
		
		//To fetch single Value Assertions:
		//per page
		String perPageValue = TestUtil.getValueByPath(responseJson, "/per_page");
		System.out.println("Value of per Page is:"+perPageValue);
		Assert.assertEquals(perPageValue, "3");
		
		//total
		String totalValue = TestUtil.getValueByPath(responseJson, "total");
		System.out.println("Value of per Page is:"+totalValue);
		Assert.assertEquals(totalValue, "12");
		 
		//To fetch value from JSON arrays
		String lastName = TestUtil.getValueByPath(responseJson, "/data[0]/last_name");
		String firstName = TestUtil.getValueByPath(responseJson, "/data[0]/first_name");
		String id = TestUtil.getValueByPath(responseJson, "/data[0]/id");
		String avatar= TestUtil.getValueByPath(responseJson, "/data[0]/avatar");
		System.out.println(lastName);
		System.out.println(firstName);
		System.out.println(id);
		System.out.println(avatar);
		
		Assert.assertEquals(lastName, "Bluth");
		
		//c. All Headers
		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for(Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers Array-->"+allHeaders);
		
	} 
		@Test(priority=1)
		
		public void getTestWithHeaders() throws Exception {
			restClient = new RestClient();
			HashMap<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("Content-Type", "application/json");
			headerMap.put("username", "test@amazon.com");
			headerMap.put("password", "test213");
			headerMap.put("Auth Token", "12345");
			
			closeableHttpResponse = restClient.get(url, headerMap);
			
			//a. Status Code
			int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();
			System.out.println(statuscode);
			
			Assert.assertEquals(statuscode,RESPONSE_STATUS_CODE200,"Status Code is not 200");
			 
			// b. JSON String
			String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");  
			JSONObject responseJson = new JSONObject(responseString); 
			System.out.println("Response JSON from API--->"+responseJson);
			
			//To fetch single Value Assertions:
			//per page
			String perPageValue = TestUtil.getValueByPath(responseJson, "/per_page");
			System.out.println("Value of per Page is:"+perPageValue);
			Assert.assertEquals(perPageValue, "3");
			
			//total
			String totalValue = TestUtil.getValueByPath(responseJson, "total");
			System.out.println("Value of per Page is:"+totalValue);
			Assert.assertEquals(totalValue, "12");
			 
			//To fetch value from JSON arrays
			String lastName = TestUtil.getValueByPath(responseJson, "/data[0]/last_name");
			String firstName = TestUtil.getValueByPath(responseJson, "/data[0]/first_name");
			String id = TestUtil.getValueByPath(responseJson, "/data[0]/id");
			String avatar= TestUtil.getValueByPath(responseJson, "/data[0]/avatar");
			System.out.println(lastName);
			System.out.println(firstName);
			System.out.println(id);
			System.out.println(avatar);
			
			Assert.assertEquals(lastName, "Bluth");
			
			//c. All Headers
			Header[] headersArray = closeableHttpResponse.getAllHeaders();
			HashMap<String, String> allHeaders = new HashMap<String, String>();
			for(Header header : headersArray) {
				allHeaders.put(header.getName(), header.getValue());
			}
			System.out.println("Headers Array-->"+allHeaders);
			
		
		
		
		
	} 
	
}
