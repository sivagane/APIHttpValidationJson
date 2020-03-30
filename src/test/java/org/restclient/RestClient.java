package org.restclient;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {
public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
	CloseableHttpClient httpClient = HttpClients.createDefault();
	HttpGet httpGet = new HttpGet(url);
	CloseableHttpResponse closeablehttpresponse = httpClient.execute(httpGet);
	return closeablehttpresponse;
}

public CloseableHttpResponse get(String url,HashMap<String , String> allheaders) throws ClientProtocolException, IOException {
	CloseableHttpClient httpClient = HttpClients.createDefault();
	HttpGet httpGet = new HttpGet(url);
	for(Map.Entry<String, String>entry:allheaders.entrySet()) {
		httpGet.addHeader(entry.getKey(), entry.getValue());
	}
	
	CloseableHttpResponse closeablehttpresponse = httpClient.execute(httpGet);
	return closeablehttpresponse;
}

public CloseableHttpResponse post(String url, String entitystring, HashMap<String, String> allheaders) throws ClientProtocolException, IOException {
	CloseableHttpClient httpClient = HttpClients.createDefault();
	HttpPost httpPost=new HttpPost(url);//to get post request
	httpPost.setEntity(new StringEntity(entitystring));//payload
	for(Map.Entry<String, String> entry: allheaders.entrySet()) {
		httpPost.addHeader(entry.getKey(), entry.getValue());
	}
	CloseableHttpResponse closeablehttpresponse = httpClient.execute(httpPost);
	return closeablehttpresponse;
}
}
