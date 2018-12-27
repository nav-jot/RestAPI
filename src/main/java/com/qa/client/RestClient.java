package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	//1. Get Method without headers (username, password, authentication key, etc.)
	
	 public CloseableHttpResponse get(String url) throws IOException  {
		CloseableHttpClient httpClient = HttpClients.createDefault(); 
					// HttpClients class creates a client connection
		 		    // returns a Closable Object
		HttpGet httpget = new HttpGet(url); // create a get connection with url
											// httpget is the request
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);// hit url
		return closeableHttpResponse;
		
	 }
	
	//2. Get Method with headers
	 
	 public CloseableHttpResponse get(String url, HashMap<String,String> headerMap) throws IOException  {
			CloseableHttpClient httpClient = HttpClients.createDefault(); 
						// HttpClients class creates a client connection
			 		    // returns a Closable Object
			HttpGet httpget = new HttpGet(url); // create a get connection with url
												// httpget is the request
			for(Map.Entry<String, String> entry : headerMap.entrySet()){
				httpget.addHeader(entry.getKey(), entry.getValue());
			}
			CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);// hit url
			return closeableHttpResponse;
			
		 }
	// 3. POST Method 
	 
	 public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {
		 CloseableHttpClient httpClient = HttpClients.createDefault(); 
		 HttpPost httppost = new HttpPost(url); // Post request
		 httppost.setEntity(new StringEntity(entityString)); // for payload
		 
		 //for headers:
		 for(Map.Entry<String, String> entry : headerMap.entrySet()){
				httppost.addHeader(entry.getKey(), entry.getValue()); // for headers
			}
		  
		 CloseableHttpResponse closeableHttpResponse = httpClient.execute(httppost);
			 return closeableHttpResponse;
		 
	 }
	
}
