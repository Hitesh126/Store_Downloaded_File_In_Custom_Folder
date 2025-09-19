import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DownloadFileToCustomFolder {

	public static void main(String[] args) {

		String downloadFolderPath = "C:\\Users\\hites\\eclipse-workspace\\DownloadFileUsingSelenium\\CustomFolder";

		File file = new File(downloadFolderPath);
		//System.out.println(file.exists());

		if (!file.exists()) {
			System.out.println("Custom folder is not present!!!");
			if (file.mkdir()) {
				System.out.println("Custom folder is created!!!-- Refresh the Project");
			}
		}
		
		Map<String, Object> prefs = new HashMap();
		prefs.put("download.default_directory", downloadFolderPath);
		prefs.put("download.prompt_for_download", false);
		
		ChromeOptions option = new ChromeOptions();
		//option.addArguments("--start-maximized");
		option.setExperimentalOption("prefs", prefs);

		 WebDriver driver = new ChromeDriver(option);
		 driver.get("https://get.jenkins.io/war-stable/2.516.3/jenkins.war");
	}

}
