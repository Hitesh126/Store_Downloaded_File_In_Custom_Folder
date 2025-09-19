import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DownloadFileToCustomFolder3 {

	public static void main(String[] args) throws InterruptedException {

//		String downloadCustomFolder = "C:\\Users\\hites\\eclipse-workspace\\DownloadFileUsingSelenium\\CustomFolder";
		String downloadCustomFolder = System.getProperty("user.dir") + File.separator + "CustomFolder";

		File file = new File(downloadCustomFolder);

		if (!file.exists()) {
			System.out.println("Custom Directory is not Present!!");
			if (file.mkdir()) {
				System.out.println("Custom Direcotry is Created!!! --> Refresh Project");
			}
		}

		Map<String, Object> prefs = new HashMap();
		prefs.put("download.default_directory", downloadCustomFolder);
		prefs.put("download.prompt_for_download", false);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.setExperimentalOption("prefs", prefs);

		WebDriver driver = new ChromeDriver(options);
		driver.get("https://get.jenkins.io/war-stable/2.516.3/jenkins.war");

		// Check File is download or not

		File checkFile = new File(downloadCustomFolder, "jenkins.war");

		int timeOutSecond = 10;
		int elapsedTime = 0;

		while (elapsedTime < timeOutSecond && !checkFile.exists()) {
			Thread.sleep(1000);
			elapsedTime++;
			System.out.println("Waiting for file to download");
		}

		if (checkFile.exists()) {
			System.out.println("File Download Successfully!!");
		}

		else {
			System.err.println("File could not be downloaded. Sys Error!!");
		}

	}

}
