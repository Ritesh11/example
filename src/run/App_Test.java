package run;
import java.io.IOException;

import org.junit.Test;

import Methods.AppMethods;

public class App_Test extends AppMethods {

	//	AppMethods methods = new AppMethods();

	
	public void run() throws IOException
	{
		try{
			alpha("Android","4.4.4");
			InstallApp("G:\\Android_Apk_Files\\BraceToolApp.apk","BraceToolApp");
			sleep(3000);
			SelectOption_App("News");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			driver.quit();
			downloadReport(driver);

		}
		System.out.println("run ended");
	}

	@Test
	public void LaunchingApp() throws Exception
	{
		try{
		alpha("Android","4.4.4");
		sleep(3000);
		LaunchApp("BraceToolApp");
		SelectOption_App("News");
	}
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	finally
	{
		driver.quit();
		downloadReport(driver);
		openreport();

	}
	System.out.println("run ended");
	}

}
