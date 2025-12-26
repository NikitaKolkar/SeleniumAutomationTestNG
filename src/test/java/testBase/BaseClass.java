package testBase;

import static org.apache.commons.text.CharacterPredicates.DIGITS;
import static org.apache.commons.text.CharacterPredicates.LETTERS;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.commons.text.RandomStringGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	
public static WebDriver driver;
public Logger logger;
public Properties p;
Random random = new Random();

	@BeforeClass(groups= {"Sanity","Regression","Master", "DataDriven"})
	@Parameters({"browser","os"})
	public void setup(String br, String os) throws IOException, URISyntaxException
	{
		//Loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		logger = LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap = new DesiredCapabilities();
			
			//os selection
			if(os.equalsIgnoreCase("windows"))
			{
				cap.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux"))
			{
				cap.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				cap.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("No matching OS");
				return;
			}	
			
			//browser selection
			switch(br.toLowerCase())
			{
			case "chrome": cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : cap.setBrowserName("firefox"); break;
			default : System.out.println("Invalid Browser Name!"); return;
			}
			cap.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			//URI uri = new URI("http://192.168.0.102:4444/wd/hub");
			URI uri = new URI("http://localhost:4444/wd/hub");
			driver = new RemoteWebDriver(uri.toURL(),cap);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			
			switch(br.toLowerCase())
			{
			case "chrome": 
				ChromeOptions coptions = new ChromeOptions();
				coptions.addArguments("--incognito");
				coptions.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
				driver=new ChromeDriver(coptions); break;
			
			case "edge" : 
				EdgeOptions eoptions = new EdgeOptions();
				eoptions.addArguments("-inprivate");
				eoptions.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
				driver=new EdgeDriver(eoptions); break;
				
			case "firefox" : 
				FirefoxOptions foptions = new FirefoxOptions();
				foptions.addArguments("-private");
				driver=new FirefoxDriver(foptions); break;
				
			default : System.out.println("Invalid Browser Name!"); return;
			}
		}
	    
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get(p.getProperty("appURL")); //reading URL from config.properties file
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master", "DataDriven"})
	public void tearDown() 
	{
		driver.quit();
	}
	
	public String RandomTitle()
	{
		String[] title = {
	            "Mr.", "Mrs." }; 
		int titleIndex = random.nextInt(title.length);
		String t = title[titleIndex];
		return t;
	}
	
	public String RandomString(int n)
	{
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z') // Define the character range
                .filteredBy(LETTERS, DIGITS) // Filter to include only letters and digits
                .get();
        String randomString = generator.generate(n);
		return randomString;
	}
	
	public String RandomNumber(int n)
	{
		RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0','9') // Define the character range
                .filteredBy(DIGITS) // Filter to include only digits
                .get();
        String randomString = generator.generate(n);
		return randomString;
	}
	
	public int RandomDay()
	{
		int[] days = IntStream.range(1,31).toArray(); 
		int daysIndex = random.nextInt(days.length);
		int d = days[daysIndex];
		return d;
	}
	
	public String RandomMonth()
	{
		String[] months = {
	            "January", "February", "March", "April", "May", "June",
	            "July", "August", "September", "October", "November", "December"
	        }; 
		int monthIndex = random.nextInt(months.length);
		String m = months[monthIndex];
		return m;
	}
	
	public int RandomYear()
	{
		int[] years = IntStream.range(1900,2021).toArray(); 
		int yearIndex = random.nextInt(years.length);
		int y = years[yearIndex];
		return y;
	}
	
	public String RandomCountry()
	{
		String[] country = {
	            "India", "United States", "Canada", "Australia", "Israel", "New Zealand",
	            "Singapore" }; 
		int countryIndex = random.nextInt(country.length);
		String con = country[countryIndex];
		return con;
	}
	
	public String captureScreen(String tname) throws IOException 
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}

}
