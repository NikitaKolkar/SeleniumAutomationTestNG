package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SignUp extends BasePage{

	public SignUp(WebDriver driver) 
	{
		super(driver);
	}
	
@FindBy(xpath="//input[@id='id_gender1']")
WebElement gender1;

@FindBy(xpath="//input[@id='id_gender2']")
WebElement gender2;

@FindBy(xpath="//input[@id='name']")
WebElement name;

@FindBy(xpath="//input[@id='email']")
WebElement email;

@FindBy(xpath="//input[@id='password']")
WebElement password;

@FindBy(xpath="//select[@id='days']")
WebElement day;

@FindBy(xpath="//select[@id='months']")
WebElement month;

@FindBy(xpath="//select[@id='years']")
WebElement year ;

@FindBy(xpath="//input[@id='newsletter']")
WebElement chkNewsletter;


@FindBy(xpath="//input[@id='optin']")
WebElement chkOffer;

@FindBy(xpath="//input[@id='first_name']")
WebElement fname;

@FindBy(xpath="//input[@id='last_name']")
WebElement lname;

@FindBy(xpath="//input[@id='company']")
WebElement company;

@FindBy(xpath="//input[@id='address1']")
WebElement address1;

@FindBy(xpath="//input[@id='address2']")
WebElement address2;


@FindBy(xpath="//select[@id='country']")
WebElement country;

@FindBy(xpath="//input[@id='state']")
WebElement state;

@FindBy(xpath="//input[@id='city']")
WebElement city;

@FindBy(xpath="//input[@id='zipcode']")
WebElement zipcode;

@FindBy(xpath="//input[@id='mobile_number']")
WebElement mobile;

@FindBy(xpath="//button[normalize-space()='Create Account']")
WebElement btnCreateAccount;

@FindBy(xpath="//b[normalize-space()='Account Created!']")
WebElement successMessage;

public void setTitle(String gender)
{
	if(gender.equals("Mr."))
	{
		gender1.click();
	}
	else 
	{
		gender2.click();
	}
}

public void setName(String sname)
{
	
}

public String getEmail()
{
	return email.getAttribute("value").toString();
}

public void setPassword(String pass)
{
	password.sendKeys(pass);
}

public void setDay(String dday)
{
	Select drpDay = new Select(day);
	drpDay.selectByValue(dday);
}

public void setMonth(String dmonth)
{
	Select drpMonth = new Select(month);
	drpMonth.selectByVisibleText(dmonth);
}

public void setYear(String dyear)
{
	Select drpYear = new Select(year);
	drpYear.selectByValue(dyear);
}

public void setChkNews(Boolean b)
{
	if(b)
	{
		chkNewsletter.click();
	}
}

public void setChkOffer(Boolean b)
{
	if(b)
	{
		//chkOffer.click();
		
	    // Using JavaScript
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", chkOffer);
	    chkOffer.click();

	    // Using Actions class
	    // Actions actions = new Actions(driver);
	    // actions.moveToElement(chkOffer).click().perform();
	}
}

public void setFirstName(String firstname)
{
	fname.sendKeys(firstname);
}

public void setLastName(String lastname)
{
	lname.sendKeys(lastname);
}

public void setCompany(String cname)
{
	company.sendKeys(cname);
}

public void setAddress1(String add1)
{
	address1.sendKeys(add1);
}

public void setAddress2(String add2)
{
	address2.sendKeys(add2);
}

public void setCountry(String countryName)
{

	Select drpCountry = new Select(country);
	drpCountry.selectByValue(countryName);
}

public void setState(String stateName)
{
	state.sendKeys(stateName);
}

public void setCity(String cityName)
{
	city.sendKeys(cityName);
}


public void setZipCode(String zcode)
{
	zipcode.sendKeys(zcode);
}


public void setMobile(String mnumber)
{
	mobile.sendKeys(mnumber);
}

public void clickCreateAccount()
{
	//btnCreateAccount.click();
	JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(true);", btnCreateAccount);
    btnCreateAccount.click();
}

public String getSuccessMessage()
{
	return successMessage.getText();
}
}
