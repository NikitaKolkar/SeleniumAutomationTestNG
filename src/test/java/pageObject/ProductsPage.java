package pageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends BasePage {
	
	public ProductsPage(WebDriver driver)
	{
		super(driver);
	}
	
JavascriptExecutor js;

@FindBy(xpath="//input[@id='search_product']")
WebElement inputSearch;

@FindBy(xpath="//i[@class='fa fa-search']")
WebElement btnSearch;

@FindBy(xpath="//a[normalize-space()='Women']")
WebElement categoryWomen;

@FindBy(xpath="//a[normalize-space()='Tops']")
WebElement subCatWomenTop;

@FindBy(xpath="//h2[normalize-space()='Searched Products']")
WebElement sectionHeadingSearch;

@FindBy(xpath="//div[@class='col-sm-4']//div[@class='product-image-wrapper']")
List<WebElement> lstProductCount;

@FindBy(xpath="//div[@class='col-sm-4']//div[@class='product-image-wrapper']//div[@class='product-overlay']//p")
List<WebElement> lstProductName;

@FindBy(xpath="//div[@class='col-sm-4']//div[@class='product-image-wrapper']//div[@class='product-overlay']//a[text()='Add to cart']")
List<WebElement> lnkAddToCart;

@FindBy(xpath="//div[@class='product-image-wrapper']//a[text()='View Product']")
List<WebElement> lstBtnViewProduct;

@FindBy(xpath="//div[@class='product-information']//h2")
WebElement productName;

@FindBy(xpath="//a[normalize-space()='Write Your Review']")
WebElement reviewHeading;

@FindBy(xpath="//input[@id='name']")
WebElement reviewerName;

@FindBy(xpath="//input[@id='email']")
WebElement reviewerId;

@FindBy(xpath="//textarea[@id='review']")
WebElement reviewBody;

@FindBy(xpath="//button[@id='button-review']")
WebElement btnSubmitReview;

@FindBy(xpath="//div[@class='col-md-12 form-group']//div[@class='alert-success alert']")
WebElement reviewSuccessMsg;

@FindBy(xpath="//button[normalize-space()='Continue Shopping']")
WebElement btnContinueShopping;

@FindBy(xpath="//u[normalize-space()='View Cart']")
WebElement lnkViewCart;


public void setSearchInput(String search) 
{
	//inputSearch.sendKeys(search);
	 this.js = (JavascriptExecutor) driver;
	 js.executeScript("arguments[0].scrollIntoView(true);", inputSearch);
	 inputSearch.sendKeys(search);
}

public void clickSearch()
{
	//btnSearch.click();
	 this.js = (JavascriptExecutor) driver;
	 js.executeScript("arguments[0].scrollIntoView(true);", btnSearch);
	 js.executeScript("arguments[0].click();", btnSearch);
}

public boolean isHeadingDisplayed()
{
	return sectionHeadingSearch.isDisplayed();
}

public String getHeadingText()
{
	return sectionHeadingSearch.getText();
}

public int getProductsCount()
{
	return lstProductCount.size();
}

public List<String> hoverOnAllProduct()
{
	Actions action = new Actions(driver);
	List<String> pname = new ArrayList<>();
	for(int i=0;i<lstProductCount.size();i++)
	{
		action.moveToElement(lstProductCount.get(i)).click().perform();
		pname.add(lstProductName.get(i).getText());
	}
	return pname;
}

public List<String> clickViewProductAll(List<String> list)
{
	this.js = (JavascriptExecutor) driver;
	List<String> pNotMatch = new ArrayList<>();
	boolean isProductMatch;
	for(int i=0;i<lstBtnViewProduct.size();i++)
	{
		js.executeScript("arguments[0].scrollIntoView(true);", lstBtnViewProduct.get(i));
		lstBtnViewProduct.get(i).click();
		isProductMatch = productName.getText().equals(list.get(i));
		if(isProductMatch!=true)
		{
		   pNotMatch.add(list.get(i));
		}
		driver.navigate().back();
	}
	return pNotMatch;
}

public void clickViewProductSelected(String pname) throws InterruptedException
{
	Actions action = new Actions(driver);
	this.js = (JavascriptExecutor) driver;
	for(int i=0;i<lstProductCount.size();i++)
	{
		action.moveToElement(lstProductCount.get(i)).click().perform();
		if(lstProductName.get(i).getText().toLowerCase().equals(pname))
		{
			js.executeScript("arguments[0].scrollIntoView(true);", lstBtnViewProduct.get(i));
			js.executeScript("arguments[0].click();", lstBtnViewProduct.get(i));
		}
	}	
}

public String getReviewHeading()

{
	this.js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);", reviewHeading);
	return reviewHeading.getText();
}

public void setReviewerName(String name)
{
	reviewerName.sendKeys(name);
}

public void setReviewerID(String id)
{
	reviewerId.sendKeys(id);
}


public void setReviewBody(String bd)
{
	reviewBody.sendKeys(bd);
}


public void clickSubmitReview()
{
	//btnSubmitReview.click();
	js.executeScript("arguments[0].scrollIntoView(true);",btnSubmitReview);
	js.executeScript("arguments[0].click();", btnSubmitReview);
}

public String getReviewSuccessMsg()
{
	return reviewSuccessMsg.getText();
}

public void clickAddToCartAndCNTShopping()
{
	this.js = (JavascriptExecutor) driver;
	for(int i=0;i<lstProductCount.size();i++)
	{
		js.executeScript("arguments[0].scrollIntoView(true);",lstProductCount.get(i));
		js.executeScript("arguments[0].click();", lstProductCount.get(i));
		js.executeScript("arguments[0].scrollIntoView(true);", lnkAddToCart.get(i));
		js.executeScript("arguments[0].click();", lnkAddToCart.get(i));
		clickContinueShopping();
	}
	
	/*
	  Actions action = new Actions(driver);
	  for(int i=0;i<lstProductCount.size();i++)
	  {
		action.moveToElement(lstProductCount.get(i)).click().perform();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    WebElement addToCart = wait.until(ExpectedConditions.visibilityOf(lnkAddToCart.get(i)));
	    addToCart.click();
	    WebElement ctnShopping = wait.until(ExpectedConditions.elementToBeClickable(btnContinueShopping));
	    ctnShopping.click();
	   }
	 */
	
}

public void clickAddToCartS(String s)
{
	this.js = (JavascriptExecutor) driver;
	for(int i=0;i<lstProductCount.size();i++)
	{
		js.executeScript("arguments[0].scrollIntoView(true);",lstProductCount.get(i));
		js.executeScript("arguments[0].click();", lstProductCount.get(i));
		if(lstProductName.get(i).getText().toLowerCase().equals(s))
		{
			js.executeScript("arguments[0].scrollIntoView(true);", lnkAddToCart.get(i));
			js.executeScript("arguments[0].click();", lnkAddToCart.get(i));
			clickViewCartLink();
		}
		
	}
}


public void clickContinueShopping()
{
	//btnContinueShopping.click();
	this.js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);",btnContinueShopping);
	js.executeScript("arguments[0].click();", btnContinueShopping);
}

public void clickViewCartLink()
{
	lnkViewCart.click();
}

public void clickWomenCategory()
{
	//categoryWomen.click();
	this.js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);",categoryWomen);
	js.executeScript("arguments[0].click();", categoryWomen);
}

public void clickTopSub()
{
	//subCatWomenTop.click();
	this.js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);",subCatWomenTop);
	js.executeScript("arguments[0].click();", subCatWomenTop);
}
}
