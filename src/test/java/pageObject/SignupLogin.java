package pageObject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignupLogin extends BasePage {

	public SignupLogin(WebDriver driver)
	{
		super(driver);
		
	}
	
@FindBy(xpath="//input[@placeholder='Name']") 
WebElement name;

@FindBy(xpath="//input[@data-qa='signup-email']")
WebElement signEmail;

@FindBy(xpath="//button[normalize-space()='Signup']")
WebElement btnSignUp;

@FindBy(xpath="//input[@data-qa='login-email']")
WebElement loginEmail;

@FindBy(xpath="//input[@placeholder='Password']")
WebElement password;

@FindBy(xpath="//button[normalize-space()='Login']")
WebElement btnLogin;

@FindBy(xpath="//p[normalize-space()='Email Address already exist!']")
WebElement emailExistMsg;

public void setName(String sname)
{
	name.sendKeys(sname);
}

public void setSignupEmail(String semail)
{
	signEmail.sendKeys(semail);
}

public void clickSignup()
{
	//btnSignUp.click();
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("arguments[0].scrollIntoView(true);", btnSignUp);
	 js.executeScript("arguments[0].click();", btnSignUp);

}

public void setLoginEmail(String lemail)
{
	loginEmail.sendKeys(lemail);
}

public void setPassword(String pass)
{
	password.sendKeys(pass);
}

public void clickLogin()
{
	//btnLogin.click();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);", btnLogin);
	js.executeScript("arguments[0].click();", btnLogin);
}

public String getEmailExistError()
{
	try {
		return (emailExistMsg.getText());
	}
	catch(Exception e)
	{
		return (e.getMessage());
	}
	
}

}
