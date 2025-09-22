import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DownloadFileToCustomFolder4 {

	public static void main(String[] args) throws InterruptedException {

		String downloadedCustomFolder = System.getProperty("user.dir") + File.separator + "CustomFolder";

		File file = new File(downloadedCustomFolder);

		if (!file.exists()) {
			System.out.println("Custom directory is not created!!");
			file.mkdir();
			System.out.println("Custom directory created!! --  Refresh Project");
		} else {
			System.out.println("Custom Folder Already created!!");
		}

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", downloadedCustomFolder);
		prefs.put("download.prompt_for_download", false);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.setExperimentalOption("prefs", prefs);

		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.jenkins.io");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		By downloadLinkLocator = By.xpath("//a[contains(@class,'app-button app') and @href='/download/']");
		WebElement downloadLink = wait.until(ExpectedConditions.visibilityOfElementLocated(downloadLinkLocator));
		downloadLink.click();

		By GenericJavaLinkLocator = By.xpath("//p[contains(text(),'LTS for')]//parent::div//div//div[contains(text(),'Generic')]");
		WebElement GenericJavaLink = wait.until(ExpectedConditions.visibilityOfElementLocated(GenericJavaLinkLocator));
		GenericJavaLink.click();

		// Verify File is download or not

		File downloadFile = new File(downloadedCustomFolder, "jenkins1.war");

		int cutOfTime = 10;
		int depletion = 0;

		while (depletion < cutOfTime && !downloadFile.exists()) {
			Thread.sleep(2000);
			depletion++;
			System.out.println("Waiting for file download");
		}
		if (downloadFile.exists()) {
			System.out.println("File Downloaded successfully!!!");
		} else {
			System.err.println("Not able to download due to some error!!!");
		}
		
		driver.quit();

	}
}
