package pageObject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends BasePage{
	
	public ContactUsPage(WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath="//h2[normalize-space()='Get In Touch']")
WebElement sectionHeading;

@FindBy(xpath="//input[@placeholder='Name']")
WebElement name;

@FindBy(xpath="//input[@placeholder='Email']")
WebElement email;

@FindBy(xpath="//input[@placeholder='Subject']")
WebElement subject;

@FindBy(xpath="//textarea[@id='message']")
WebElement msgBody;

@FindBy(xpath="//input[@name='upload_file']")
WebElement uploadFile;

@FindBy(xpath="//input[@name='submit']")
WebElement btnSubmit;

@FindBy(xpath="//div[@class='status alert alert-success']")
WebElement successMsg;

public String getHeading()
{
	return sectionHeading.getText();
}

public void setName(String fname)
{
	name.sendKeys(fname);
}

public void setEmail(String eid )
{
	email.sendKeys(eid);
}

public void setSubject(String subjectBody)
{
	subject.sendKeys(subjectBody);
}

public void setMessageBody(String body)
{
	msgBody.sendKeys(body);
}

public void setFile(String filepath)
{
	uploadFile.sendKeys(filepath);
}

public String clickSubmit() throws InterruptedException
{
	btnSubmit.click();
	Thread.sleep(2000);
	Alert myAlert = driver.switchTo().alert();
	String msg = myAlert.getText();
	myAlert.accept();//close alert using OK button
	return msg;
}

public String getSuccessMsg()
{
	return successMsg.getText();
}
}
