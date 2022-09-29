package com.qa.mypom.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	public WebDriver driver;
    public Properties prop;
    public FileInputStream ip;
    public OptionsManager optionsManager;
    
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("browser name : "+ browserName);
		
		optionsManager = new OptionsManager(prop);
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());	
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

		}
		else if(browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());

		}
		else if(browserName.equalsIgnoreCase("safari")) {
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());

		}
		else {
			System.out.println("please pass right browser : " + browserName);
		}
	  
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	public Properties initProp() {
	  prop = new Properties();
	  FileInputStream ip = null;
	  
	  String envName = System.getProperty("env");
	  System.out.println("Running test case on env : " + envName);
	  
	  if(envName == null) {
		  System.out.println("No env is given hence running it on PROD env by default");
			 try {
				ip = new FileInputStream("./src/test/resources/cofig/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	  }
	  else {
		  try {
		  switch (envName.toLowerCase()) {
		case "qa":
			ip = new FileInputStream("./src/test/resources/cofig/qa.config.properties");
			break;
		case "stage":
			ip = new FileInputStream("./src/test/resources/cofig/stage.config.properties");
			break;
		case "prod":
			ip = new FileInputStream("./src/test/resources/cofig/config.properties");
			break;
		case "dev":
			ip = new FileInputStream("./src/test/resources/cofig/dev.config.properties");
			break;

		default:
			System.out.println("Please pass the right env...: " + envName);
			break;
		}
		  } catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	  }
	  try {
		prop.load(ip);
	} catch (IOException e) {
		e.printStackTrace();
	}
		return prop;
	}
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshot/"+System.currentTimeMillis()+"png";
		
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}

}
