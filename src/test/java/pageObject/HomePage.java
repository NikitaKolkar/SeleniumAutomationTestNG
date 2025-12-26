package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) 
	{
		super(driver);
	}
 
@FindBy(xpath="//a[normalize-space()='Signup / Login']") 
WebElement lnkSignLogin;

@FindBy(xpath="//a[@href='/products']")
WebElement lnkProducts;

@FindBy(xpath="//a[text()=' Cart']")
WebElement lnkCart;

@FindBy(xpath="//a[normalize-space()='Logout']")
WebElement btnLogout;

@FindBy(xpath="//a[normalize-space()='Delete Account']")
WebElement btnDeleteAccount;

@FindBy(xpath="//b[normalize-space()='Account Deleted!']")
WebElement msgDeleteAccount;

@FindBy(xpath="//ul[@class='nav navbar-nav']//li//a[text()=' Contact us']")
WebElement lnkContactUs;

@FindBy(xpath="//li[10]//a[1]")
WebElement loggedInMsg;

@FindBy(xpath="//p[normalize-space()='Your email or password is incorrect!']")
WebElement loginErrMsg;

@FindBy(xpath="//h2[normalize-space()='Category']")
WebElement lnkCategory;

@FindBy(xpath="//h2[normalize-space()='Subscription']")
WebElement subscriptionHeading;

@FindBy(xpath="//input[@id='susbscribe_email']")
WebElement subEmail;

@FindBy(xpath="//form[@class='searchform']//button//i")
WebElement btnSubscribe;

@FindBy(xpath="//div[@class='alert-success alert']")
WebElement successAlert;

public void clickSignLogin()
{
	lnkSignLogin.click();
}

public void clickProducts()
{
	//lnkProducts.click();
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("arguments[0].scrollIntoView(true);", lnkProducts);
	 lnkProducts.click();
}

public void clickCart()
{
	//JavascriptExecutor js = (JavascriptExecutor) driver;
	//js.executeScript("arguments[0].scrollIntoView(true);", lnkCart);
	//lnkCart.click();
	
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(lnkCart));
    element.click();
}

public void clickLogOut()
{
	btnLogout.click();
}

public boolean isLogoutVisible()
{
	try
	{
		return btnLogout.isDisplayed();
	}
	catch(Exception e)
	{
		return false;
	}
}

public void clickDeleteAccount()
{
	btnDeleteAccount.click();
}

public boolean isDeleteAccountVisible()
{
	try
	{
		return btnDeleteAccount.isDisplayed();
	}
	catch(Exception e)
	{
		return false;
	}
}

public String getDeleteAccMsg()
{
	return msgDeleteAccount.getText();
}
public void clickContactUs()
{
	lnkContactUs.click();
}

public String getLoggedInMsg()
{
	return loggedInMsg.getText();
}

public boolean isErrorVisible()
{
	try
	{
		return loginErrMsg.isDisplayed();
	}
	catch(Exception e)
	{
		return false;
	}
}

public boolean isCategoryVisible()
{
	return lnkCategory.isDisplayed();
}

public String getSubHeading()
{
	JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView(true);", subscriptionHeading);
    return subscriptionHeading.getText();
	
}

public void setSubEmail(String email)
{
	subEmail.sendKeys(email);
}

public String isEmailRequired()
{
	return subEmail.getAttribute("required");
}

public String getEmailErrorMsg()
{
	return subEmail.getAttribute("validationMessage");
}

public void clickBtnSubscribe()
{
	btnSubscribe.click();
}
public boolean isSuccessAlertVisible()
{
	return successAlert.isDisplayed();
}

public String getSuccessAlert()
{
	return successAlert.getText();
}
}
