//class that tests the functionality of TestCasesRentalcars.java
//hardcoded the response in a text file and compared the output 

package testcases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;


public class RunTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String responseString = getJsonResponse();
		JsonPath jsonResponse= new JsonPath(responseString);
		
	    //get list of cars from response
       int listofCars=	jsonResponse.get("Car.size()");
		System.out.println(jsonResponse.get("Car.make"));
		for(int i=0;i<listofCars;i++){
			if((jsonResponse.get("Car["+i+"].make").equals("Tesla")) 
				&& (jsonResponse.get("Car["+i+"].metadata.Color").equals("Blue")))
			{
			System.out.println("Tesla" +jsonResponse.get("Car.make"));
			System.out.println("Blue" +jsonResponse.get("Car.metadata.Color"));
			System.out.println("Notes" +jsonResponse.get("Car.metadata.Notes"));
			
		}
		

			}
		}
		
	private static String getJsonResponse() {
		// TODO Auto-generated method stub
		String result = "";
	    try {
	        BufferedReader br = new BufferedReader(new FileReader("C:/Harini/Cars.txt"));
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();
	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	        }
	        result = sb.toString();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return result;
	} 
	
	

}
