package pageObject;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage extends BasePage {
	
	public PaymentPage(WebDriver driver)
	{
		super(driver);
	}
	
@FindBy(xpath="//input[@name='name_on_card']")
WebElement name;

@FindBy(xpath="//input[@name='card_number']")
WebElement cardNumber;

@FindBy(xpath="//input[@name='cvc']")
WebElement cvc;

@FindBy(xpath="//input[@placeholder='MM']")
WebElement expiryMon;

@FindBy(xpath="//input[@placeholder='YYYY']")
WebElement expiryYear;

@FindBy(xpath="//button[@id='submit']")
WebElement btnPay;

@FindBy(xpath="//div[@id='success_message']//div")
WebElement successMsg;

@FindBy(xpath="//h2[@class='title text-center']//b")
WebElement orderPlacedHeading;

@FindBy(xpath="//div[@class='col-sm-9 col-sm-offset-1']/p")
WebElement orderPlacedSuccessMsg;

@FindBy(xpath="//a[normalize-space()='Download Invoice']")
WebElement btnDownloadInvoice;

@FindBy(xpath="//a[normalize-space()='Continue']")
WebElement btnContinue;

public void setName(String cname)
{
	name.sendKeys(cname);
}

public void setCardNumber(String cnumber)
{
	cardNumber.sendKeys(cnumber);
}

public void setCVC(String cvcn)
{
	//cvc.sendKeys(cvcn);
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("arguments[0].scrollIntoView(true);", cvc);
	 js.executeScript("arguments[0].value = arguments[1];", cvc, cvcn);
}

public void setMon(String month)
{
	//expiryMon.sendKeys(month);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);", expiryMon);
	js.executeScript("arguments[0].value = arguments[1];", expiryMon, month);
}

public void setYear(String year)
{
	//expiryYear.sendKeys(year);
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);", expiryYear);
	js.executeScript("arguments[0].value = arguments[1];", expiryYear, year);
}

public void clickPayOrder()
{

	//btnPay.click();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);", btnPay);
	js.executeScript("arguments[0].click();", btnPay);
}

public String getSuccessMsg()
{
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    WebElement msg = wait.until(ExpectedConditions.visibilityOf(successMsg));
	return msg.getText();
}

public String getOPHeading()
{
	return orderPlacedHeading.getText();
}

public String getOPMsg()
{
	return orderPlacedSuccessMsg.getText();
}

public void clickDownloadInvoice()
{
	btnDownloadInvoice.click();
}

public void clickContinue()
{
	btnContinue.click();
}

}
