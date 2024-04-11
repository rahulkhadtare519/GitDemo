import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUploading {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String downloadPath= System.getProperty("user.dir");

		ChromeOptions options = new ChromeOptions();              //link to chromeoptions 
		Map<String, Object> prefs = new HashMap<String, Object>();   //https://chromedriver.chromium.org/capabilities
		prefs.put("download.default_directory", downloadPath);
		options.setExperimentalOption("prefs", prefs);
		
		WebDriver driver= new ChromeDriver(options);

		driver.get("https://altoconvertpdftojpg.com/");
		driver.manage().window().maximize();
		driver.findElement(By.id("browse")).click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("C:\\Users\\visha\\OneDrive\\Documents\\Testing\\FileUpload.exe");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Convert files')]")));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,300)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[contains(text(),'Convert files')]")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Download")));
		driver.findElement(By.linkText("Download")).click();
		File f= new File(downloadPath+"/Resume_-_Pradeep.zip"); //for concatenating path a forward slash is recommended
		if(f.exists())
		{
			System.out.println("File Found");
			driver.close();
		}


	}

}
