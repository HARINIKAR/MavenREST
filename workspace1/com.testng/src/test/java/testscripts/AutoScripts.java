package testscripts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;


import utility.ReUsableMethods;

public class AutoScripts {
	public static WebDriver driver;
	
	@Test(enabled=false)
	public static void TC1_LoginErrorMessage() throws InterruptedException{
		System.setProperty("webdriver.gecko.driver","./src/test/java/utility/geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get("https://login.salesforce.com/");
				
		String expString="Please enter your password.";
		String applicationTitle=driver.getTitle();
		if(applicationTitle.contains("Salesforce"))
			System.out.println("salesforce page is verified");
		else
			System.out.println("salesforce page is not verified");
		//
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("User@gmail.com");
		driver.findElement(By.xpath("//*[@id='password']")).clear();
		driver.findElement(By.xpath(".//*[@id='Login']")).click();
		String actualText=driver.findElement(By.xpath("//*[@id='error']")).getText();
		if(actualText.equalsIgnoreCase(expString))
			System.out.println("error message verified");
		else
			System.out.println("error message is not verified...testcase failed");
		ReUsableMethods.closeBrowser(driver);
		
	}
	@Test(enabled=false)
		public static void TC2_LoginToSFDC() throws InterruptedException{
			System.setProperty("webdriver.gecko.driver","./src/test/java/utility/geckodriver.exe");
			driver=new FirefoxDriver();
			driver.get("https://login.salesforce.com/");
					
			driver.findElement(By.xpath("//*[@id='username']")).sendKeys("harini.karanam@gmail.com");
			driver.findElement(By.xpath("//*[@id='password']")).sendKeys("&Ashrita06");;
			driver.findElement(By.xpath(".//*[@id='Login']")).click();
			Thread.sleep(6000);
			String actualText=driver.getTitle();
			//String expectedText="Home Page";
			if(actualText.contains("Home Page")){
				System.out.println("Title is verified");
			}else{
					System.out.println("Title not verified");
		}
		
		ReUsableMethods.closeBrowser(driver);
		

	}
	@Test(enabled=false)

	public static void CheckRememberMe() throws InterruptedException{
		System.setProperty("webdriver.gecko.driver","./src/test/java/utility/geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("harini.karanam@gmail.com");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("&Ashrita06");
		driver.findElement(By.xpath("//*[@id='rememberUn']")).click();
		driver.findElement(By.xpath(".//*[@id='Login']")).click();
		Thread.sleep(6000);
		String actualText=driver.getTitle();
		//String expectedText="Home Page";
		if(actualText.contains("Home Page")){
			System.out.println("Title is verified");
		}else{
				System.out.println("Title not verified");
	}
		Thread.sleep(6000);
	driver.findElement(By.xpath("//*[@id='userNav-arrow']")).click();
	driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[5]")).click();
	Thread.sleep(6000);
	String id=driver.findElement(By.xpath("//*[@id='idcard-identity']")).getText();
	if(id.equalsIgnoreCase("harini.karanam@gmail.com")){
		System.out.println("Username displayed verified");
	}
	else{
		System.out.println("Username displayed not verified");
	
			}
	ReUsableMethods.closeBrowser(driver);
}
	@Test(enabled=false)
	public static void ForgotPassword() throws InterruptedException{
		
		String expectedHeader="Forgot Your Password";
		System.setProperty("webdriver.gecko.driver","./src/test/java/utility/geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.xpath("//*[@id='forgot_password_link']")).click();
		String headeractual=driver.findElement(By.xpath("//*[@id='un']")).getText();
		if(headeractual.contains(expectedHeader)){
			System.out.println("Forgot Password page is displayed");
		}else
			System.out.println("Forgot Password page not displayed");
		driver.findElement(By.xpath("//*[@id='un']")).sendKeys("harini.karanam@gmail.com");
		driver.findElement(By.xpath("//*[@id='continue']")).click();
		ReUsableMethods.closeBrowser(driver);

		
}
	@Test(enabled=false)
	public static void TC05_UserMenu() throws InterruptedException{
		String[] options=new String[]{"My Profile","My Settings","Developer Console","Logout"};
		List<String> expList=new ArrayList<String>(Arrays.asList(options));
		
		System.setProperty("webdriver.gecko.driver","./src/test/java/utility/geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("harini.karanam@gmail.com");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("&Ashrita06");
		driver.findElement(By.xpath("//*[@id='rememberUn']")).click();
		driver.findElement(By.xpath(".//*[@id='Login']")).click();
		Thread.sleep(6000);
		String actualText=driver.getTitle();
		//String expectedText="Home Page";
		if(actualText.contains("Home Page")){
			System.out.println("Title is verified");
		}else{
				System.out.println("Title not verified");
	}
		Thread.sleep(6000);
		String username=driver.findElement(By.xpath("//*[@id='userNavLabel']")).getText();
		if(username.equals("Harini Karanam")){
			System.out.println("Username verified");
		}else
			System.out.println("Username not verified ");
	driver.findElement(By.xpath("//*[@id='userNav-arrow']")).click();
	List<WebElement> dropdown=driver.findElements(By.xpath("//*[@id='userNav-menuItems']/a"));
	ArrayList<String> values=new ArrayList<String>();
	for(WebElement ele:dropdown){
		values.add(ele.getText().trim());
	}
	System.out.println(values);
	if(values.containsAll(expList))
		System.out.println("usermenu verified ");
	else
		System.out.println("user menu not verified");
	ReUsableMethods.closeBrowser(driver);

	}
	@Test(enabled=true)
	public static void TC06_MyProfile() throws InterruptedException{
		String[] options=new String[]{"About","Contact"};
		List<String> expList=new ArrayList<String>(Arrays.asList(options));
		
		System.setProperty("webdriver.gecko.driver","./src/test/java/utility/geckodriver.exe");
		driver=new FirefoxDriver();
		driver.get("https://login.salesforce.com/");
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys("harini.karanam@gmail.com");
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("&Ashrita06");
		driver.findElement(By.xpath("//*[@id='rememberUn']")).click();
		driver.findElement(By.xpath(".//*[@id='Login']")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//*[@id='userNav-arrow']")).click();
		driver.findElement(By.xpath("//*[@id='userNav-menuItems']/a[1]")).click();
		driver.findElement(By.xpath(".//*[@id='chatterTab']/div[2]/div[2]/div[1]/h3/div/div/a/img")).click();
		driver.switchTo().frame("contactInfoContentId");
		List<WebElement> tabs=driver.findElements(By.tagName("a"));
		ArrayList<String> values=new ArrayList<String>();
        System.out.println(tabs);
		for(WebElement ele:tabs){
			values.add(ele.getText().trim());
		}
		if(values.containsAll(expList))
			System.out.println("Frame verified ");
		else
			System.out.println("Frame not verified");
		Thread.sleep(6000);
		driver.findElement(By.xpath("//*[@id='aboutTab']/a")).click();
		driver.findElement(By.xpath("//*[@id='lastName']")).clear();
		driver.findElement(By.xpath("//*[@id='lastName']")).sendKeys("Karanam");
		driver.findElement(By.xpath("//*[@id='TabPanel']/div/div[2]/form/div/input[1]")).click();
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//*[@id='publisherAttachTextPost']/span[1]")).click();
		Thread.sleep(4000);
		
		WebElement frame=driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));
		WebDriverWait wait = new WebDriverWait(driver,2000);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']")));
		driver.switchTo().frame(frame);
		System.out.println("switched");
		Thread.sleep(10000);
		System.out.println("switched");
		Thread.sleep(10000);
		System.out.println("tag html?  "+driver.findElements(By.xpath("//html/body")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body")));
		WebElement postMessage=driver.findElement(By.xpath("/html/body"));
		postMessage.sendKeys("This is using Automation");
		driver.switchTo().defaultContent();
		WebElement shareButton=driver.findElement(By.xpath(".//*[@id='publishersharebutton']"));
		shareButton.click();
		
		Thread.sleep(2000);
		//For File
		
		WebElement file=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='publisherAttachContentPost']/span[1]")));
		file.click();
		System.out.println("clicked on file");
		
		WebElement upload=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='chatterUploadFileAction']")));
		upload.click();
		System.out.println("clicked on upload");
		
		WebElement browse=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='chatterFile']")));
		browse.sendKeys("C:\\Users\\Harini\\salesforce.txt");
		
		WebElement share=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='publishersharebutton']")));
		share.click();
		Thread.sleep(5000);
		//Add photo
		
		Actions mousehoover=new Actions(driver);
		mousehoover.moveToElement(driver.findElement(By.xpath("//*[text()='Moderator']"))).perform();;
		WebElement addPhoto=driver.findElement(By.xpath(".//*[@id='uploadLink']"));
		addPhoto.click();
		Thread.sleep(5000);
		WebElement photoframe=driver.findElement(By.id("uploadPhotoContentId"));
		driver.switchTo().frame(photoframe);
		WebElement browsePhoto=wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='j_id0:uploadFileForm:uploadInputFile']")));
	
		browsePhoto.sendKeys("C:\\Users\\Harini\\Image1.jpg");
	
		WebElement savePhoto=driver.findElement(By.xpath(".//*[@id='j_id0:uploadFileForm:uploadBtn']"));
		savePhoto.click();
		
		Actions action=new Actions(driver); 
		action.dragAndDropBy(driver.findElement(By.xpath(".//*[@id='j_id0:outer']/div[1]/div/div/div/div[6]")), 100, 20);
		driver.findElement(By.xpath(".//*[@id='j_id0:j_id7:save']")).click(); 
		
		driver.switchTo().defaultContent();
		ReUsableMethods.closeBrowser(driver);
}
}


