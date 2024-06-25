import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Product {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "C:/Users/Bharathwaj Murali/Desktop/LeepikaProject/Leepika/geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		
		//Launch the url
		driver.get("https://www.tatadigital.com/home");
		
		//Click on Mobiles under Explore Categories
		driver.findElement(By.xpath("//a[contains(text(), 'Mobiles')]")).click();
		
		//Go to New section and click on first product
		driver.findElement(By.xpath("/flt-scene-host/flt-scene/flt-transform/flt-offset/flt-clip/flt-clip-interior/flt-offset/flt-offset/flt-clip/flt-clip-interior/flt-offset[2]/flt-offset/flt-offset/flt-offset/flt-offset/flt-clip/flt-clip-interior/flt-offset[2]/flt-offset/flt-offset[1]/flt-picture/draw-rect")).click();
		
		//Check in new tab
		for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            }
		driver.get("https://www.tatadigital.com/native-electronics/slp/mobiles");
		driver.getWindowHandle();

		//Click on Buy Now
		driver.findElement(By.xpath("//button[@class='btn btn-default buyNowBtn]'"));
		
		//Comparison
		String cartProductInfo = driver.findElement(By.cssSelector(".product-name a")).getText();
        String cartPrice = driver.findElement(By.cssSelector(".item-price .price")).getText();
        String productInfo = null;
        String price = null;
        
        // Step 10: Compare and display result
        if (productInfo.equals(cartProductInfo) && price.equals(cartPrice)) {
            System.out.println("Test Result: Pass");
        } else {
            System.out.println("Test Result: Fail");
            // Screenshot
            ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).renameTo(new File("failure_screenshot.png"));
        }
		driver.quit();
		
	}
}