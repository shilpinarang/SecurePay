package com.securepay.test.SpayProject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.IOException;
import java.util.regex.Pattern;
public class App {
    public static void main(String[] args) throws InterruptedException {
    	//Get the OS Type (MAC or Windows)
        	String operatingSystem = System.getProperty("os.name").toUpperCase();
        	Pattern pattern = Pattern.compile("^MAC");
        	Boolean matcher = pattern.matcher(operatingSystem).lookingAt();
        	//Select the correct chromedriver file based on Operating System type.
        	if(matcher == true) {
        		System.setProperty("webdriver.chrome.driver", "./resources/chromedriver");
        	}
        	else {
        		System.setProperty("webdriver.chrome.driver", "./resources/chromedriver.exe");
        	}
        	    	
			WebDriver driver = new ChromeDriver();
			//CSV File contains the fields to fill in the contact us form
			String csvFile = "contacts.csv";
	        String line = "";
	        String cvsSplitBy = ",";
			driver.get("https://www.google.com.au/");
			//Maximize the chrome window size
			driver.manage().window().maximize();
			driver.findElement(By.className("gLFyf")).sendKeys("securepay");
			driver.findElement(By.className("gLFyf")).sendKeys(Keys.ENTER);
			driver.findElement(By.partialLinkText("securepay.com.au")).click();
			Actions actions = new Actions(driver);
			//Hover over the Support link
			actions.moveToElement(driver.findElement(By.id("menu-item-3367"))).perform();
			//Click on the sub menu Contact US under Support Menu
			driver.findElement(By.id("menu-item-179")).click();
			//Wait until the contact us page is loaded
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("reason-for-enquiry")));
	        //Create the object of readCSV class which is used to read the CSV file. 
	        readCSV rcs = new readCSV();
		       try {
	            	//Get the header fields (First Line) from the CSV file
	            	line = rcs.readheader(csvFile, 1);
	            	String[] header = line.split(cvsSplitBy);
	            	//Get the contacts fields (Second Line) from the CSV file
	            	line = rcs.readheader(csvFile, 2);
	            	String[] contact = line.split(cvsSplitBy);
	            	//Loop into all the fields on Contact us form based on element information from header fields
	            	// and fill the required fields with contacts information read from csv file (2nd Line)
	            	for(int i=0;i<=6;i++) {
	            		driver.findElement(By.name(header[i])).sendKeys(contact[i]);
	            		//If condition to validate reason-for-enquiry field as it is a drop down rather than text box
	            		if (i==5) {
	            			driver.findElement(By.name("reason-for-enquiry")).click();
	            			driver.findElement(By.xpath("//*[text()='Accounts']")).click();		
	            		}
	            	}
	            } 
	            catch (IOException e) {
					e.printStackTrace();
				}
	            finally {
	            	driver.quit();
	            }
			}
}