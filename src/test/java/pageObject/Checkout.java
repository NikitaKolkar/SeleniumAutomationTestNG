package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Checkout extends BasePage{
	
	public Checkout(WebDriver driver) 
	{
		super(driver);
	}

@FindBy(xpath="//h2[normalize-space()='Address Details']")
WebElement addressHeading;

@FindBy(xpath="//h3[normalize-space()='Your delivery address']")
WebElement deliveryAddHeading;

@FindBy(xpath="//h3[normalize-space()='Your billing address']")
WebElement billingAddHeading;

@FindBy(xpath="//ul[@id='address_delivery']//li[@class='address_firstname address_lastname']")
WebElement deliveryFirstName;

@FindBy(xpath="//ul[@id='address_delivery']//li[@class='address_city address_state_name address_postcode']")
WebElement deliveryCityPcode;

@FindBy(xpath="//ul[@id='address_delivery']//li[@class='address_country_name']")
WebElement deliveryCountry;

@FindBy(xpath="//ul[@id='address_delivery']//li[@class='address_phone']")
WebElement deliveryPhone;

@FindBy(xpath="//ul[@id='address_invoice']//li[@class='address_firstname address_lastname']")
WebElement billingFirstName;

@FindBy(xpath="//ul[@id='address_invoice']//li[@class='address_city address_state_name address_postcode']")
WebElement billingCityPcode;

@FindBy(xpath="//ul[@id='address_invoice']//li[@class='address_country_name']")
WebElement billingCountry;

@FindBy(xpath="//ul[@id='address_invoice']//li[@class='address_phone']")
WebElement billingPhone;

@FindBy(xpath="//h2[normalize-space()='Review Your Order']")
WebElement reviewHeading;

@FindBy(xpath="//tbody//td[@class='cart_description']/h4")
List<WebElement> productDesc;

@FindBy(xpath="//tbody//td[@class='cart_price']//p")
List<WebElement> productPrice;

@FindBy(xpath="//tbody//td[@class='cart_quantity']//button")
List<WebElement> productQnt;

@FindBy(xpath="//tbody//td[@class='cart_total']//p")
List<WebElement> productTPrice;

@FindBy(xpath="//b[normalize-space()='Total Amount']")
WebElement totalPriceHeading;

@FindBy(xpath="//tbody/tr[last()]//p")
WebElement totalPrice;

@FindBy(xpath="//a[normalize-space()='Place Order']")
WebElement btnPlaceOrder;

public String getAddressHeading()
{
	return addressHeading.getText();
}

public String getDAddHeading()
{
	return deliveryAddHeading.getText();
}

public String getBAddHeading()
{
	return billingAddHeading.getText();
}

public String getDFirstName()
{
	return deliveryFirstName.getText();
}
	
public String getDCityPCode()
{
	return deliveryCityPcode.getText();
}

public String getDCountry()
{
	return deliveryCountry.getText();
}

public String getDPhone()
{
	return deliveryPhone.getText();
}

public String getBFirstName()
{
	return billingFirstName.getText();
}
	
public String getBCityPCode()
{
	return billingCityPcode.getText();
}

public String getBCountry()
{
	return billingCountry.getText();
}

public String getBPhone()
{
	return billingPhone.getText();
}

public String getReviewHeading()
{
	return reviewHeading.getText();
}

public List<String> getProductName()
{
	List<String> pname = new ArrayList<>();
	for(int i=0;i<productDesc.size();i++)
	{
		pname.add(productDesc.get(i).getText());
	}
	return pname;
}

public List<String> getProductPrice()
{
	List<String> price = new ArrayList<>();
	for(int i=0;i<productDesc.size();i++)
	{
		price.add(productPrice.get(i).getText());
	}
	return price;
}

public List<String> getProductQuantity()
{
	List<String> qnt = new ArrayList<>();
	for(int i=0;i<productDesc.size();i++)
	{
		qnt.add(productQnt.get(i).getDomAttribute("value"));
	}
	return qnt;
}

public List<String> getProductTPrice()
{
	List<String> tprice = new ArrayList<>();
	for(int i=0;i<productDesc.size();i++)
	{
		tprice.add(productTPrice.get(i).getText());
	}
	return tprice;
}

public String getTPriceHeading()
{
	return totalPriceHeading.getText();
}

public String getTotalPrice() 
{
	return totalPrice.getText();
}

public void clickPlaceOrder()
{
	//btnPlaceOrder.click();
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);", btnPlaceOrder);
	js.executeScript("arguments[0].click();", btnPlaceOrder);
}
}
