package com.product;
 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
// import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class MainController {


  @RequestMapping(value= "/searchProductAPI",method=RequestMethod.POST,produces = "application/json")
  public String searchProductAPI(@RequestBody JsonNode appData) {
	  	
	  try {
		String items = HttpClientUtility.getProductResponse(appData.path("queryResult").path("queryText").asText());
	    return "{\"fulfillmentText\":\"" + items+ "\"}";

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    return "{\"fulfillmentText\":\"Error\")}";

	}

  }

}