package assigment.assignment2.Test1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class FlightSearch {

	public static Properties setPropertiesFile(String path){
		File file = new File(path);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	   Properties prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static void getScreenshot(WebDriver driver, String name){
		try{
			 Date date = new Date();
		     name = name + new Timestamp(date.getTime());
		     name = name.replaceAll(":", "");
	    	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	           
	    	FileUtils.copyFile(scrFile, new File("D:\\"+name+".png"));
	    }catch(Exception e){
	    	System.out.println(e.getMessage());
	    }
	}
}
