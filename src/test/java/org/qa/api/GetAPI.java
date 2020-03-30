package org.qa.api;

import java.io.IOException;
import java.util.HashMap;

import org.Test.TestBase;
import org.Util.TestUtil;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.restclient.RestClient;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetAPI extends TestBase{
	RestClient restclient;
	String serviceurl;
	String apiurl;
	String url;
	TestBase testbase;
	@BeforeMethod
	public void setup() {
		testbase=new TestBase();
		String serviceurl = prop.getProperty("Url");
		String apiurl = prop.getProperty("serviceUrl");
		
		url=serviceurl+apiurl;
	}

	@Test
	public void getApi() throws ClientProtocolException, IOException, JSONException {
		RestClient restclient = new RestClient();
		CloseableHttpResponse closeablehttpresponse = restclient.get(url);
		
		int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("The Status Code is "+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not true");
		
		String responsecode = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
		JSONObject jsocresponsecode=new JSONObject(responsecode);
		System.out.println("The JSON response code is "+jsocresponsecode);
		
		//To validate a single Object of the JSON Response code
		
		String Pages = TestUtil.getValueByJPath(jsocresponsecode, "/per_page");
		System.out.println("The Number of Pages is "+Integer.parseInt(Pages));
		Assert.assertEquals(Integer.parseInt(Pages), 6);
		
		String Total = TestUtil.getValueByJPath(jsocresponsecode, "/total");
		
		Assert.assertEquals(Integer.parseInt(Total), 12);
		
		String Lastname = TestUtil.getValueByJPath(jsocresponsecode, "data[0]/last_name");
		Assert.assertEquals(Lastname, "Lawson");
		
		String Id = TestUtil.getValueByJPath(jsocresponsecode, "data[0]/id");
		Assert.assertEquals(Integer.parseInt(Id), 7);
		
	}
	
	@Test
	public void getApiwithHeaders() throws ClientProtocolException, IOException, JSONException {
		RestClient restclient = new RestClient();
		HashMap<String, String>allheaders=new HashMap<String, String>();
		allheaders.put("User-Agent", "application/xhtml+xml");
		
		
		CloseableHttpResponse closeablehttpresponse = restclient.get(url);
		
		int statusCode = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("The Status Code is "+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200,"Status code is not true");
		
		String responsecode = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");
		JSONObject jsocresponsecode=new JSONObject(responsecode);
		System.out.println("The JSON response code is "+jsocresponsecode);
		
		//To validate a single Object of the JSON Response code
		
		String Pages = TestUtil.getValueByJPath(jsocresponsecode, "/per_page");
		System.out.println("The Number of Pages is "+Integer.parseInt(Pages));
		Assert.assertEquals(Integer.parseInt(Pages), 6);
		
		String Total = TestUtil.getValueByJPath(jsocresponsecode, "/total");
		System.out.println("The total no pages "+Total);
		Assert.assertEquals(Integer.parseInt(Total), 12);
		
		String Lastname = TestUtil.getValueByJPath(jsocresponsecode, "data[0]/last_name");
		System.out.println("The Last name is "+Lastname);
		Assert.assertEquals(Lastname, "Lawson");
		
		
		String Id = TestUtil.getValueByJPath(jsocresponsecode, "data[0]/id");
		Assert.assertEquals(Integer.parseInt(Id), 7);
		
		System.out.println("The Headers "+allheaders);
		
	}

}
