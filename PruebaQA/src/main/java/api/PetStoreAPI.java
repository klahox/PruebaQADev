package api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PetStoreAPI {
	
	public static String SOLD = "sold";
	public static String AVAILABLE = "available";	
	public static String PENDING = "pending";		
	
	//Json Object
	private JSONObject jsonIDName; 
	
	public PetStoreAPI(JSONObject jsonIDName) {
		this.jsonIDName= jsonIDName;

	}	
	

	public static String callToCreateUser(JSONObject json) throws JSONException, IOException{
		
    	System.out.println("createUser Request: " + json.toString());
    	HttpPost httpPost = new HttpPost("https://petstore.swagger.io/v2/user");
    	String responseString;

    	
    	// Create a HttpClient object
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            // Add headers if needed
        	httpPost.setHeader("Content-Type", "application/json");
            httpPost.setEntity(new StringEntity(json.toString()));

            // Send the request and get the response
            HttpResponse response = httpClient.execute(httpPost);

            // Print the response
            responseString = EntityUtils.toString(response.getEntity());
            System.out.println("createUser Response: " + responseString);
            
        }
        return responseString;
		
	}
	

	public static String callToGetUserByUserName(String name) throws ClientProtocolException, IOException{
		
    	HttpGet httpGet = new HttpGet("https://petstore.swagger.io/v2/user/"+name);
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse getResponse = httpClient.execute(httpGet);
        
        String jsonString = EntityUtils.toString(getResponse.getEntity());
        System.out.println("getUserByUserName Response"+jsonString);
        
        return jsonString;
		
	}
	

	public static JSONArray callToFindPetsByStatus(String status) throws ClientProtocolException, IOException, JSONException {
		
        HttpGet httpGet = new HttpGet("http://petstore.swagger.io/v2/pet/findByStatus?status="+status);		
        HttpClient httpClient = HttpClients.createDefault();

        HttpResponse getResponse = httpClient.execute(httpGet);
        String jsonString = EntityUtils.toString(getResponse.getEntity());
        JSONArray jsonArray = new JSONArray(jsonString);
        
        return jsonArray;
        
		
	}
	
	public static JSONObject listTuplesIdNameReturn(JSONArray jsonArray) throws JSONException{
		
		
		JSONObject jsonIdName = new JSONObject();
		
		 System.out.println("======================================");
		 System.out.println("Listamos la lista de tuplas {id,name}:");
		 System.out.println("======================================");
		 
	     //Iteramos sobre el jsonArray de entrada y solo copio el id y name en un JSONObject => formato {"id","name"}
	     for(int i = 0; i< jsonArray.length(); i++){
	    	 
	    	 try {
		    	 int id = jsonArray.getJSONObject(i).getInt("id");
		    	 
		    	 String nameOfPet =jsonArray.getJSONObject(i).getString("name");
		    	 jsonIdName.put(Integer.toString(id), (String)nameOfPet);
		    	 
		    	 System.out.println("{"+id+","+nameOfPet+"}");

	    	 }catch(Exception e) {
	    		 //no hacemos nada si algun dato esta corrupto
	    	 }
	     }
	     
	     return jsonIdName;
	}	
	
	public JSONObject getPetsNameRepetedAndNumOfRepetitions() throws JSONException{
		
		 //jSON =>  {"name",contador}
	    JSONObject jsonNameCounter  = new JSONObject();
	     
	    //Itero sobre json {Id,Name} y creo un nuevo json {"name",contador}
	    Iterator iter = this.jsonIDName.keys();
	    while (iter.hasNext()) {
	 
		         Object key = iter.next();
		         String nameOfPet = this.jsonIDName.get(key.toString()).toString();
		         
		         jsonNameCounter.increment(nameOfPet);
	     }    
	     
	     //Itero sobre json {name,contador} y elimino los nombres que no estan repetidos (es decir contador =1)
	     Iterator iter2 = jsonNameCounter.keys();
	     while (iter2.hasNext()) {
	 
		         Object key = iter2.next();
		         int value = jsonNameCounter.getInt((String)key);
		         
		         if(value < 2){
		        	 // Si el valor es 1,el nombre no esta repetidos, asi que lo eliminamos de la lista
		        	 iter2.remove();
		        	
		         }
	     }    
	     
		 System.out.println("==================================");
		 System.out.println("Mascotas que se llaman Iguales ");
		 System.out.println("==================================");
		 System.out.println(jsonNameCounter.toString());
	     return jsonNameCounter;
	}		
	

}
