package com.product;

import java.util.Iterator;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class HttpClientExample {


	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		CloseableHttpClient client = HttpClients.createDefault();
	    HttpPost httpPost = new HttpPost("https://api-qa.jaegar.io/catalog/search/products");
	 
	    String json = "{\"query\":\"Milk\",\"suggest\":\"true\"}";
	    StringEntity entity = new StringEntity(json);
	    httpPost.setEntity(entity);
	    httpPost.setHeader("Accept", "application/json");
	    httpPost.setHeader("Content-type", "application/json");
	    httpPost.setHeader("x-api-key", "uXegSDiQLQ8xwgrv1ehJCavoH4v0b7Ww9eMmQBmW");
	    httpPost.setHeader("X-711-Locale", "country=US;currency=USD");

	    CloseableHttpResponse response = client.execute(httpPost);
	    System.out.println(response.toString());
	    HttpEntity entity1 = response.getEntity();
	    Header encodingHeader = entity.getContentEncoding();
	    
	    ObjectMapper objectMapper = new ObjectMapper();

	    //ProductSearchResponse productSearchResponse = objectMapper.readValue(response.getEntity().getContent(), ProductSearchResponse.class);

	    JsonNode rootNode = objectMapper.readTree(response.getEntity().getContent());
/*	    JsonNode idNode = rootNode.path("Count");
	    System.out.println("id = "+idNode.asInt());
*/	    
	    JsonNode itemsList = rootNode.path("Items");
	    Iterator<JsonNode> elements = itemsList.elements();
	    int count =0; 
	    while(count <3){
	    	JsonNode item = elements.next();
	    	Item newJsonNode = objectMapper.treeToValue(item, Item.class);

	    	System.out.println("Item Names = "+newJsonNode.getName());
	    	count ++;
	    }
	    
	  
 

	}


}
