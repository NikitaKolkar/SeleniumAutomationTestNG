package pageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {
	public CartPage(WebDriver driver) 
	{
		super(driver);
	}

@FindBy(xpath="//p[@class='text-center']//b[text()='Cart is empty!']")
WebElement emptyCartMsg;
	
@FindBy(xpath="//table[@id='cart_info_table']//td[@class='cart_description']//h4")
List<WebElement> productDesc;

@FindBy(xpath="//table[@id='cart_info_table']//td[@class='cart_price']//p")
List<WebElement> price;

@FindBy(xpath="//table[@id='cart_info_table']//td[@class='cart_quantity']//button")
List<WebElement>quantity;

@FindBy(xpath="//table[@id='cart_info_table']//td[@class='cart_total']//p")
List<WebElement> tprice;

@FindBy(xpath="//table[@id='cart_info_table']//td[@class='cart_delete']//i")
List<WebElement> deleteProduct;

@FindBy(xpath="//a[normalize-space()='Proceed To Checkout']")
WebElement checkout;

public List<String> getProductDesc()
{
	List<String> productName = new ArrayList<>();
	for(int i=0;i<productDesc.size();i++)
	{
		productName.add(productDesc.get(i).getText());
	}
	return productName;
}

public void clickDeleteProduct(String s)
{
	for(int i=0;i<productDesc.size();i++)
	{
		if(productDesc.get(i).getText().equals(s))
		{
			deleteProduct.get(i).click();
		}
	}
}

public void clickCheckout()
{
	checkout.click();
}

public boolean isCartEmpty()
{
	if(emptyCartMsg.isDisplayed()==true)
	{
		return true;
	}
	else if(emptyCartMsg.isDisplayed()==false && productDesc.isEmpty()==false)
	{
		return false;
	}
	return false;
}
}
