package api;

import java.io.IOException;
import java.util.Map;


import org.apache.http.client.ClientProtocolException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;


/**
 * Hello world!
 *
 */
public class Main
{
	


    public static void main( String[] args ) throws ClientProtocolException, IOException, JSONException
    {
    	//Creo los parametros de crear usuario
    	JSONObject jsonParams = new JSONObject();
    	jsonParams.put("id", 0);
    	jsonParams.put("username", "klajdi");
    	jsonParams.put("firstName", "hoxha");
    	jsonParams.put("lastName", "sina");
    	jsonParams.put("email", "klajdi.hoxha@gmail.com");
    	jsonParams.put("password", "12345");
    	jsonParams.put("phone", "123456789");
    	jsonParams.put("userStatus", 0);
    	
    	// Llamada para crear el usuario, muestro por consola el respose
    	PetStoreAPI.callToCreateUser(jsonParams);

    	
    	// Llamo para obtener el usuario por nombre y muestro por consola el response (a veces da error)
    	PetStoreAPI.callToGetUserByUserName("klajdi");

    	//Llamo a FindByStats by status y devuelve un JSONArray
    	JSONArray jsonArray = PetStoreAPI.callToFindPetsByStatus(PetStoreAPI.AVAILABLE);
    	//JSONArray jsonArray = PetStore.callToFindPetsByStatus(PetStore.SOLD);
        
        //Obtengo la lista de {id,Name} y lo muestro por consola
        JSONObject  jsonIdName = PetStoreAPI.listTuplesIdNameReturn(jsonArray);
        
        //Busco los nombres repetidos y el numero de veces repetidos y lo muestro por consola
        //{"doggie": 72, "Buddy": 12}
        PetStoreAPI petStoreApi = new PetStoreAPI(jsonIdName);
        petStoreApi.getPetsNameRepetedAndNumOfRepetitions();
        
        
	     
             
    }    
    


}
