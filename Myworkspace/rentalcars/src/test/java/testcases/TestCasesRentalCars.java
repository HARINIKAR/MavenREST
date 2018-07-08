//Assuming the response is a proper json
package testcases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.authentication.AuthenticationScheme;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.mapper.ObjectMapper;
import com.jayway.restassured.mapper.ObjectMapperDeserializationContext;
import com.jayway.restassured.mapper.ObjectMapperSerializationContext;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class TestCasesRentalCars {
			
	
	//Adding authentication step
	
		String consumerKey = "xxx";
		String consumerSecret = "xxxxxxxx";
		String accessToken = "xxxx";
		String accessSecret = "xxxx";
		
		RequestSpecBuilder requestBuilder;
		static RequestSpecification requestSpec;

		//Validating authentication for every call
		@BeforeClass
		public void setup() {
			AuthenticationScheme authScheme = 
					RestAssured.oauth(consumerKey, consumerSecret, accessToken, accessSecret);
			requestBuilder = new RequestSpecBuilder();
			requestBuilder.setBaseUri("/Cars");
			requestBuilder.setBasePath("");
			requestBuilder.addQueryParam("user_id", "xxx");
			requestBuilder.setAuth(authScheme);
			requestSpec = requestBuilder.build();
		}
	/*Extract response as String */	
		public String ResponseString(){
			String responseString=RestAssured.given().spec(requestSpec)
					.when()
					.get("/RentalCars")
					.then()
					.extract().response().asString();
					return responseString;
		}
		/*Extract Response as JSON Response*/
		public Response returnJSONResponse(){
			Response response=RestAssured.given().spec(requestSpec)
					.when()
					.get("/RentalCars");
					return response;
		}
		
/*Print all the blue Teslas received in the web service response.*/

@Test
public void TC01(){
	/*Calling the function ResponseString for the response as String*/
	String responseString=ResponseString();
	
/*convert response string to json*/
	    JsonPath jsonResponse= new JsonPath(responseString);
 //get list of cars from response
	    int listofCars=	jsonResponse.get("Car.size()");

//loop through the list to get the desired one(blue tesla)

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
/*Return all cars which have the lowest per day rental cost for both cases:
	a. Price only
	b. Price after discounts*/

//a.Price only
@Test
public void CarWithLowestRentalPrice(){
	Response response=returnJSONResponse();
	/*Response response=RestAssured.given().spec(requestSpec)
	.when()
	.get("/RentalCars");
	*/
	
    JsonPath jsonPathEvaluator= response.jsonPath();
    //Parse the response and get the minimum price
   int MinPrice= jsonPathEvaluator.get("Car.perdayrent.Price.min()");
   
   ArrayList l = jsonPathEvaluator.get("Car.findAll{it->it.perdayrent.Price == "+MinPrice+"}");
   Map carData = (HashMap)l.get(0);
  //print car with min price
System.out.println("Car with lowest price "+ carData.get("make"));


}



/*b.Car with lowest Price with discount*/

@Test
public void PriceAfterdiscount(){
		Response response=returnJSONResponse();
		JsonPath jsonPathEvaluator= response.jsonPath();
		    
    /*get list of price*/
    List<Float> listOfPrice=jsonPathEvaluator.getList("Car.perdayrent.Price");
    
    /*get list of discount*/
    List<Float> listOfDiscount=jsonPathEvaluator.getList("Car.perdayrent.Discount");
    
    
    /*price with discount..assuming discount is in same denomination as price and not in %*/
    List<Float> listofPricewithDiscount=new ArrayList<Float>();
    
    for(int i=0;i<listOfPrice.size();i++){
    	listofPricewithDiscount.add(listOfPrice.get(i)-listOfDiscount.get(i));
    	
    }
    int MinPriceAfterDiscountIndex= listofPricewithDiscount.indexOf(Collections.min(listofPricewithDiscount));
	System.out.println(jsonPathEvaluator.get("Car["+MinPriceAfterDiscountIndex+"]"));

}


// Question 3: Find the highest revenue generating car. year over year maintenance cost + depreciation is the total expense per car for the full year for the rental car company.
//The objective is to find those cars that produced the highest profit in the last year


@Test

public void highestRevenueCar(){
	
	Response response=returnJSONResponse();
    JsonPath jsonPathEvaluator= response.jsonPath();

    /*get list of price*/
    List<Float> listOfPrice=jsonPathEvaluator.getList("Car.perdayrent.Price");
    
  /*  get list of discount */
    List<Float> listOfDiscount=jsonPathEvaluator.getList("Car.perdayrent.Discount");
    
    
 /*   price with discount..assuming discount is in same denomination as price and not in %*/
    List<Float> listofPricewithDiscount=new ArrayList<Float>();
    
    /* adding discounted price to the list*/
    for(int i=0;i<listOfPrice.size();i++){
    	listofPricewithDiscount.add(listOfPrice.get(i)-listOfDiscount.get(i));
    	
    }
    
    /* finding the minimum discounted price  */
    int MinPriceAfterDiscountIndex= listofPricewithDiscount.indexOf(Collections.min(listofPricewithDiscount));
 	//	jsonPathEvaluator.get("Car.perdayrent.Price.min()");
	System.out.println(jsonPathEvaluator.get("Car["+MinPriceAfterDiscountIndex+"]"));

/*add values to listOfRevenue after applying discountedprice *rentalcount*/

List<Float> listOfRentalCount= jsonPathEvaluator.getList("Car.metrics.rentalcount.yeartodate");
List<Float> listOfRevenue=new ArrayList<Float>();

/*  Getting the total revenue in the list  */
for(int i=0; i<listOfPrice.size();i++){
	listOfRevenue.add(listofPricewithDiscount.get(i)* listOfRentalCount.get(i));
}
/*find highest revenue car index*/
int indexofHighestRevenueCar = listOfRevenue.indexOf(Collections.max(listOfRevenue));

/*print highest car revenue*/
System.out.println(jsonPathEvaluator.get("Car[indexofHighestRevenueCar]"));



}

//highest profit generated car
@Test
public void highestProfitCar(){
	Response response=returnJSONResponse();
	JsonPath jsonPathEvaluator= response.jsonPath();
		    
		    /*get list of price*/
		    List<Float> listOfPrice=jsonPathEvaluator.getList("Car.perdayrent.Price");
		    
		    /*get list of discount*/
		    List<Float> listOfDiscount=jsonPathEvaluator.getList("Car.perdayrent.Discount");
		    
		    
		    /*price with discount..assuming discount is in same denomiation as price and not in %*/
		    List<Float> listofPricewithDiscount=new ArrayList<Float>();
		    
		    for(int i=0;i<listOfPrice.size();i++){
		    	listofPricewithDiscount.add(listOfPrice.get(i)-listOfDiscount.get(i));
		    	
		    }
		    List<Float> listOfMaintenanceCost= jsonPathEvaluator.getList("Car.metrics.yoymaintenancecost");
		    List<Float> listOfDepreciationCost= jsonPathEvaluator.getList("Car.perdayrent.metrics.depreciation");

		    List<Float> listOfRentalCount= jsonPathEvaluator.getList("Car.metrics.rentalcount.yeartodate");
		    List<Float> listOfProfit=new ArrayList();
		    //getting total price--(Price-discount)+"yoymaintenancecost" 
		    
			for(int i=0; i<listOfPrice.size();i++){
				listOfProfit.add((listofPricewithDiscount.get(i)* listOfRentalCount.get(i))-(
						listOfMaintenanceCost.get(i)+ listOfDepreciationCost.get(i)));
		}
			/*find highest revenue car index*/
			int highestRevenueCarindex = listOfProfit.indexOf(Collections.max(listOfProfit));
			/*print highest car revenue*/
			System.out.println(jsonPathEvaluator.get("Car["+highestRevenueCarindex+"]"));


		  
}

}