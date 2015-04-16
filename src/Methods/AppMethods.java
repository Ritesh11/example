package Methods;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.perfectomobile.httpclient.MediaType;
import com.perfectomobile.httpclient.utils.FileUtils;
import com.perfectomobile.selenium.MobileDriver;
import com.perfectomobile.selenium.api.IMobileDevice;
import com.perfectomobile.selenium.api.IMobileDriver;
import com.perfectomobile.selenium.api.IMobileWebDriver;
import com.perfectomobile.selenium.options.MobileDeviceFindOptions;
import com.perfectomobile.selenium.options.MobileDeviceOS;

public class AppMethods {

	public MobileDriver driver = new MobileDriver();
	public IMobileWebDriver nativeDriver = null;
	public MobileDeviceFindOptions options = new MobileDeviceFindOptions();
	public IMobileDevice device=null;


	public  void alpha(String OperatingSystem , String OSVersion) throws IOException {

		System.out.println("Run started");

		try {

			//PART 1: Select a device

			if(OperatingSystem.equalsIgnoreCase("Android"))
			{
				options.setOS(MobileDeviceOS.ANDROID);
				options.setOSVersion(OSVersion);
			}
			else if(OperatingSystem.equalsIgnoreCase("IOS"))
			{
				options.setOS(MobileDeviceOS.IOS);
				options.setOSVersion(OSVersion);
			}
			System.out.println("YE chalta hai kya");
			options.setOSVersion(OSVersion);
			System.out.println("or YE wala chalta hai kya");
			device = driver.findDevice(options);
			//Open the selected device
			device.open();

			//Navigate device to home screen
			device.home();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Selection of device ended");
		}

	}


	//===============================================INSTALL APP=========================================================

	public void InstallApp(String appRepositoryPath , String appIdentifier) throws IOException{



		//		String appid=appIdentifier;
		try{
			System.out.println("Installing App from appRepositoryPath");

			device.installApplication(appRepositoryPath);
			System.out.println("Successfully Installed");
			//Define the nativeDriver
			nativeDriver = device.getNativeDriver(appIdentifier);
			nativeDriver.open();

		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

	//================================================================================================================
	//===============================================Methods==========================================================
	public void LaunchApp(String appIdentifier)
	{
		try{
		nativeDriver = device.getNativeDriver(appIdentifier);
		nativeDriver.open();
		}
		 catch (Exception e) {
				e.printStackTrace();
			} 
	}
	//===============================================SelectOption_App=================================================
	public void SelectOption_App(String Choice)
	{
		try{
			System.out.println("Selection of options are in progress");
			if(Choice.equalsIgnoreCase("Tech info"))
			{
				WebElement Tech_info = nativeDriver.findElement(By.linkText("Tech info"));
				Tech_info.click();
			}
			else if(Choice.equalsIgnoreCase("Products"))
			{
				WebElement Products = nativeDriver.findElement(By.linkText("Products"));
				Products.click();
			}
			else if(Choice.equalsIgnoreCase("News"))
			{
				WebElement News = nativeDriver.findElement(By.linkText("News"));
				News.click();
			}
			else if(Choice.equalsIgnoreCase("Location"))
			{
				WebElement Location = nativeDriver.findElement(By.linkText("Location"));
				Location.click();
			}
			else if(Choice.equalsIgnoreCase("Settings"))
			{
				WebElement Settings = nativeDriver.findElement(By.xpath("//device/view/group[1]/view[1]/group[1]/view[1]/group[1]/group[1]/button[1]"));
				Settings.click();
			}
			else
			{
				WebElement Customer_Support = nativeDriver.findElement(By.xpath("//device/view/group[1]/view[1]/group[1]/view[1]/group[1]/group[1]/button[2]"));
				Customer_Support.click();
			}

			System.out.println("Option Selected");
		}
		catch (Exception e) {
			e.printStackTrace();
		} 

	}
	//====================================================Download Reports===============================================
	public static void downloadReport(IMobileDriver driver) 
	{
		InputStream reportStream = driver.downloadReport(MediaType.PDF);
		if (reportStream != null) 
		{
			String path = "E:\\Reports\\report.pdf";
			File reportFile = new File(path);
			try 
			{
				FileUtils.write(reportStream, reportFile);
			} catch (IOException e) 
			{
				System.out.println("Failed to write report to path: " + path + " - " + e.getMessage());
			}
		}
	}
 
	//==================================================Open Reports======================================================
	public void openreport() throws Exception
	{
	Desktop dk = null;
	dk = Desktop.getDesktop();
	dk.open(new File("E:\\report22.pdf"));

	}


	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}


}
