import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DownloadFileToCustomFolder2 {

	public static void main(String[] args) {
		
		String downloadCustomFolder = "C:\\Users\\hites\\eclipse-workspace\\DownloadFileUsingSelenium\\CustomFolder";
		
		File file = new File(downloadCustomFolder);
		
		if(!file.exists()) {
		System.out.println("Custom Directory is not Present!!");
			if(file.mkdir()) {
				System.out.println("Custom Direcotry is Created!!! --> Refresh Project");
			}
		}
		
		Map<String, Object> prefs =  new HashMap();
		prefs.put("download.default_directory", downloadCustomFolder);
		prefs.put("download.prompt_for_download", false);
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		options.setExperimentalOption("prefs", prefs);
		
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://get.jenkins.io/war-stable/2.516.3/jenkins.war");
		
	}

}
