package com.OptimisticChemicalMakers.MapFood;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapFoodApplicationTests {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void nearestStoresTeste() throws Exception {
		
		mvc.perform(get("/v1/stores/nearest?latitude=-30.03742831&longitude=-51.228496&radius=5")
	               .accept(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$", notNullValue()));
	}
	
	@Test
	public void productByStoreTeste() throws Exception {
		
		mvc.perform(get("/v1/products/d5bb47709b5ea9d797a937b0a82728a3490c7845551200434bde3f10c0abccff")
	               .accept(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$", notNullValue()));
	}
	
	@Test
	public void createDeliveryOrder() throws Exception {
		
		mvc.perform(post("/v1/order/create")
	               .contentType(MediaType.APPLICATION_JSON)
	               .content("{ endingLongitude\" : \"-51.18663487\", " + 
		               		"\"endingLatitude\" : \"-30.06447218\"," + 
		               		"\"deliveryItems\":[  " + 
		               		"  {  \"quantity\":1," + 
		               		"     \"productId\":838" + 
		               		"  }" + 
		               		"]," + 
		               		"\"storeHash\":\"fac09173cbb9fc57e9c4b59859f18813433bf0e06d95279daf7d18750e034d51\"," + 
		               		"\"customerId\":5" + 
		               		"}")
	               )
	               .andExpect(status().isOk());
	}
	
	@Test
	public void routesWithYourOrders() throws Exception {
		
		mvc.perform(get("/v1/routes/fac09173cbb9fc57e9c4b59859f18813433bf0e06d95279daf7d18750e034d51")
	               .accept(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$", notNullValue()));
	}
	
	@Test
	public void orderReady() throws Exception {
		
		mvc.perform(post("/v1/order/ready")
	               .contentType(MediaType.APPLICATION_JSON)
	               .content("{ \"id\":52,"+ // isso muda, como faz ?????
	            		   	" \"endingLongitude\" : \"-51.18663487\", " + 
		               		"\"endingLatitude\" : \"-30.06447218\"," + 
		               		"\"deliveryItems\":[  " + 
		               		"  {  "+
		               		"     \"quantity\":1," + 
		               		"     \"productId\":838" + 
		               		"  }" + 
		               		"]," + 
		               		"\"storeHash\":\"fac09173cbb9fc57e9c4b59859f18813433bf0e06d95279daf7d18750e034d51\"," + 
		               		"\"customerId\":5" + 
		               		"}")
	               )
	               .andExpect(status().isOk());
	}
	
	@Test
	public void reportTeste() throws Exception {
		
		mvc.perform(get("/v1/order/filter?store=fac09173cbb9fc57e9c4b59859f18813433bf0e06d95279daf7d18750e034d51&start=2019-02-11&end=2019-02-12")
	               .accept(MediaType.APPLICATION_JSON))
	               .andExpect(status().isOk())
	               .andExpect(jsonPath("$", notNullValue()));
	}
	
	
	

}

