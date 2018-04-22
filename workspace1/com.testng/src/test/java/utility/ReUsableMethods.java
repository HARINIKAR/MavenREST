package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReUsableMethods {
	public static HSSFWorkbook wb;
	public static WebDriver driver;
	
	
	public static void openBrowser(WebDriver driver) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver","./src/test/java/utility/geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get("https://login.salesforce.com/");
				
		
	}
	public static void enterText(WebElement obj, String textVal, String objName){
		if(obj.isDisplayed()){
			obj.sendKeys(textVal);
			System.out.println("Pass: "+textVal+ " is entered in " + objName +" field.");
		}else{
			System.out.println("Fail: " + objName +" field does not exist please check your application.");
			//logger.log();
		}
	}
	/*Name of the Method: clickObj
	 * Brief Description: Click on Object
	 * Arguments: obj--->Object, objName---> name of object
	 * Created By : Automation team
	 * Creation date : Mar 09 2018
	 * Last Modified Date: Mar 15 2018
	 * Last Modified by: Divyashree
	 */
	public static void clickObj(WebElement obj1,String objName){
		if(obj1.isDisplayed()){
			obj1.click();
			System.out.println("Pass : " + objName +" is clicked" + objName);
		}
		else{
			System.out.println("Fail :" + objName + " is not diplayed, please check your application");
		}
	}
	public static void selectCheckBox(WebElement obj,String objName){
		if(obj.isDisplayed())
		{
			if(obj.isSelected()){
			
			System.out.println("Pass" +objName+ "is already selected");
			}else{
				obj.click();
			}
			System.out.println("Fail" +objName+ "is not displayed,please check your application");
		}		
	}
    	public static void deSelectCheckBox(WebElement obj,String objName){
    		if(obj.isDisplayed())
    		{ 
    			if(obj.isSelected()){
    				obj.click();
    				System.out.println("Pass: "+ objName + " is  de selected.");
    				
    			}else{
    				System.out.println("Pass: "+ objName + " is already de selected.");
    			}

    		}
    		else
    		{
    			System.out.println("Fail :" + objName + " is not diplayed, please check your application");
    		}
    	
    	}
    	public static void validateTextBoxContent(WebElement obj, String expectedText, String objName){
    		if(obj.isDisplayed())
    		{
    			String actualText = obj.getAttribute("value");
    			if(expectedText.equals(actualText)){
    				System.out.println("Pass: " + " Expected text '" + expectedText + "' is matching with actual text.");
    			}else{
    				System.out.println("Fail: "+" Expected text '" + expectedText + "' is not matching with actual text '"+ actualText+ "'.");
    			}
    			
    		}else{
    			System.out.println("Fail :" + objName + " is not diplayed, please check your application");
    		}
    	}
    	
    	
    	
    	
    	public static WebDriver locateFrame(String frameName, WebDriver driver) {
    		WebDriverWait wt = new WebDriverWait(driver, 10);
    		wt.until(ExpectedConditions.visibilityOfElementLocated((By.className(frameName))));
    		driver.switchTo().frame(frameName);
    		return driver;
    	}
    	
    	
    	public static void displayFrames(WebDriver driver) {
    		int i=0;
    		for (WebElement e:driver.findElements(By.tagName("iframe"))) {
    			System.out.println(String.format("Frame %d=%s",i++, e.getTagName()+e.getText()));
    		}
    		
    	}

	public static String[][] readXlSheet(String link,String sheetName) throws IOException{
		FileInputStream fio=new FileInputStream(new File(link));
		wb=new HSSFWorkbook(fio);
		HSSFSheet sheet=wb.getSheet(sheetName);
		int trow=sheet.getLastRowNum()+1;
		int tcol=sheet.getRow(0).getLastCellNum();
		String[][] s = new String[trow][tcol];
		System.out.println("total rows="+trow+" and total column="+tcol);
		for(int i=0;i<trow;i++){
			for(int j=0;j<tcol;j++){
				int cellType=sheet.getRow(i).getCell(j).getCellType();
				if(cellType==HSSFCell.CELL_TYPE_NUMERIC)
					s[i][j]=String.valueOf((int)sheet.getRow(i).getCell(j).getNumericCellValue());
				else
				s[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();	
		
			}
		}
		
		return s;
	}
	public static void closeBrowser(WebDriver driver) {
		// TODO Auto-generated method stub
		driver.close();
		
		
	}

}
