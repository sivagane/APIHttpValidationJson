package org.qa.api;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.Test.TestBase;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.qa.data.Users;
import org.restclient.RestClient;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PostAPI extends TestBase {
	RestClient restclient;
	String serviceurl;
	String apiurl;
	String url;
	TestBase testbase;
	CloseableHttpResponse closeablehttpresponse;
	@BeforeMethod
	public void setup() {
		testbase=new TestBase();
		String serviceurl = prop.getProperty("Url");
		String apiurl = prop.getProperty("serviceUrl");
		
		url=serviceurl+apiurl;
	}
@Test
public void postApi() throws ClientProtocolException, IOException, JSONException {
	restclient=new RestClient();
	HashMap<String, String>allheaders=new HashMap<String, String>();
	allheaders.put("User-Agent", "application/xhtml+xml");
	System.out.println("The Headers are "+allheaders);
	
	ObjectMapper mapper=new ObjectMapper();
	Users user=new Users("morpheus","leader");
	
	mapper.writeValue(new File("C:\\Users\\jegan\\eclipse-workspace\\APIHttpValidationJson\\src\\test\\java\\org\\qa\\data\\config.utility"), user);
	
	String userstring = mapper.writeValueAsString(user);
	System.out.println(userstring);
	
	CloseableHttpResponse closeablehttpresponse = restclient.post(url, userstring, allheaders);
	
	int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
	System.out.println("The Status code is "+statusCode);
	Assert.assertEquals(statusCode, testbase.RESPONSE_STATUS_CODE_201);
	
	String responsecode = EntityUtils.toString(closeablehttpresponse.getEntity(),"UTF-8");
	JSONObject responsejson=new JSONObject(responsecode);
	System.out.println("The Json responese code is "+responsejson);
	
	Users userobjectdata = mapper.readValue(responsecode, Users.class);
	System.out.println(userobjectdata);
	//Assert.assertTrue(user.getName().equals(userobjectdata.getName()));
	
	System.out.println(userobjectdata.getName());
	System.out.println(userobjectdata.getJob());
	System.out.println(userobjectdata.getId());
	System.out.println(userobjectdata.getCreatedAt());
	
	
}

}
