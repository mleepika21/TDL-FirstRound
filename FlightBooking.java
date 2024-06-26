import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlightBooking {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "C:/Users/Bharathwaj Murali/Desktop/LeepikaProject/Leepika/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		//Launch the url
		driver.get("https://www.tatadigital.com/home");
		
		//Click on Flights under Explore Categories
		 driver.findElement(By.xpath("//a[contains(text(), 'Flights')]")).click();
		 		
		//Select From and To
		 driver.findElement(By.id("From")).sendKeys("DEL");
		 driver.findElement(By.id("To")).sendKeys("BLR");
		 
		//Select date Departure T+7 and Return T+10
		    LocalDate currentDate = LocalDate.now();
	        LocalDate departureDate = currentDate.plusDays(7);
	        LocalDate returnDate = currentDate.plusDays(10);

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	        driver.findElement(By.id("Departure")).sendKeys(departureDate.format(formatter));
	        driver.findElement(By.id("Return")).sendKeys(returnDate.format(formatter));
	        
	        //Select 2 adults and 1 infant
	        //Adults
	        WebElement sdd = driver.findElement(By.cssSelector("flt-scene-host:nth-child(4) > flt-scene:nth-child(1) > flt-transform:nth-child(1) > flt-offset:nth-child(1) > flt-clip:nth-child(1) > flt-clip-interior:nth-child(1) > flt-offset:nth-child(1) > flt-offset:nth-child(1) > flt-clip:nth-child(1) > flt-clip-interior:nth-child(1) > flt-offset:nth-child(4) > flt-offset:nth-child(2) > flt-offset:nth-child(1) > flt-offset:nth-child(1) > flt-clip:nth-child(1) > flt-clip-interior:nth-child(1) > flt-picture:nth-child(1) > flt-canvas:nth-child(1) > flt-paragraph:nth-child(2) > flt-span:nth-child(1)"));
	        Select dropdown = new Select (sdd);
	        dropdown.selectByIndex(0);
	        for(int i=1;i<=2;i++)
	        {
	        	driver.findElement(By.xpath("/flt-scene-host/flt-scene/flt-transform/flt-offset/flt-clip/flt-clip-interior/flt-offset/flt-offset/flt-clip/flt-clip-interior/flt-offset[4]/flt-offset/flt-offset/flt-offset/flt-clip/flt-clip-interior/flt-picture[1]/flt-canvas/draw-circle[2]")).click();
	        }
	        //Infant
	        WebElement sdd1 = driver.findElement(By.cssSelector("flt-scene-host:nth-child(4) > flt-scene:nth-child(1) > flt-transform:nth-child(1) > flt-offset:nth-child(1) > flt-clip:nth-child(1) > flt-clip-interior:nth-child(1) > flt-offset:nth-child(1) > flt-offset:nth-child(1) > flt-clip:nth-child(1) > flt-clip-interior:nth-child(1) > flt-offset:nth-child(4) > flt-offset:nth-child(2) > flt-offset:nth-child(1) > flt-offset:nth-child(1) > flt-clip:nth-child(1) > flt-clip-interior:nth-child(1) > flt-picture:nth-child(1) > flt-canvas:nth-child(1) > flt-paragraph:nth-child(18) > flt-span:nth-child(1)"));
	        Select dropdown1 = new Select (sdd1);
	        dropdown1.selectByIndex(0);
	        for(int i=1;i<=1;i++)
	        {
	        	driver.findElement(By.xpath("/flt-scene-host/flt-scene/flt-transform/flt-offset/flt-clip/flt-clip-interior/flt-offset/flt-offset/flt-clip/flt-clip-interior/flt-offset[4]/flt-offset/flt-offset/flt-offset/flt-clip/flt-clip-interior/flt-picture[1]/flt-canvas/draw-circle[6]")).click();
	        }
	        
	        //Click on Search
	        driver.findElement(By.id("SearchBtn")).click();
	        
	        //Filter - Non stop and cheapest
	        WebDriverWait wait =new WebDriverWait (driver, 10);
	        WebElement nonStopFilter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Nonstop']/following-sibling::label")));
	        nonStopFilter.click();

	        driver.findElement(By.xpath("//input[@id='cheapest']/following-sibling::label")).click();
	        
	        //Compare Price
	        List<WebElement> prices = driver.findElements(By.xpath("//div[contains(@class,'flight-card')]//span[@class='flight-price']"));
	        boolean isPriceDescending = true;

	        for (int i = 0; i < prices.size() - 1; i++) {
	            String price1 = prices.get(i).getText().replace("₹", "").replace(",", "").trim();
	            String price2 = prices.get(i + 1).getText().replace("₹", "").replace(",", "").trim();

	            if (Integer.parseInt(price1) < Integer.parseInt(price2)) {
	                isPriceDescending = false;
	                break;
	            }
	        }

	        if (isPriceDescending) {
	            System.out.println("Results: Pass");
	        } else {
	            System.out.println("Results: Fail");
	        
	          //Screenshot
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).renameTo(new File("failure_screenshot.png"));
            }
	        	
	        driver.quit();

	}
}
