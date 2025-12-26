package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.CartPage;
import pageObject.Checkout;
import pageObject.HomePage;
import pageObject.PaymentPage;
import pageObject.ProductsPage;
import pageObject.SignupLogin;

import testBase.BaseClass;

public class TS07_TC01_ViewCartAndCheckout extends BaseClass{
	
	@Test(groups= {"Sanity","Master","Regression"})
	public void verify_viewcartandcheckout()
	{
		logger.info("***** Starting TS07_TC01_ViewCartAndCheckout *****");
		SoftAssert softAssert = new SoftAssert();
		try
		{
			  HomePage hp = new HomePage(driver);
			  hp.clickSignLogin();
			  logger.info("Clicked on Signup / Login button");
			  
			  SignupLogin sl = new SignupLogin(driver);
			  logger.info("Providing Email and Password");
			  sl.setLoginEmail(p.getProperty("email"));
			  sl.setPassword(p.getProperty("password"));
			  sl.clickLogin();
			  
			  CartPage cp = new CartPage(driver);
			  hp.clickCart();
			  softAssert.assertEquals(cp.isCartEmpty(), true,"Verify if Cart is empty");
			  
			  hp.clickProducts();
	          
			  ProductsPage pr = new ProductsPage(driver);
			  pr.setSearchInput(p.getProperty("searchProductName"));
			  pr.clickSearch();
			  pr.clickAddToCartS(p.getProperty("checkoutProduct"));
		
			  List<String> cproducts = cp.getProductDesc();
			  if(cproducts.size()==1 && cproducts.getFirst().toLowerCase().equals(p.getProperty("checkoutProduct")))	  
			  {
				  softAssert.assertTrue(true);
				  
			  }
			  else
			  {
				  softAssert.assertTrue(false);
			  }
			  
			  cp.clickCheckout();
			  
			  Checkout ch = new Checkout(driver);
			  softAssert.assertEquals(ch.getAddressHeading().toLowerCase(), "address details");
			  softAssert.assertEquals(ch.getDAddHeading().toLowerCase(),"your delivery address");
			  softAssert.assertEquals(ch.getBAddHeading().toLowerCase(),"your billing address");
			  
			  softAssert.assertEquals(ch.getDFirstName().toLowerCase(),p.getProperty("firstname"));
			  softAssert.assertEquals(ch.getDCityPCode().toLowerCase(),p.getProperty("citypcode"));
			  softAssert.assertEquals(ch.getDCountry().toLowerCase(),p.getProperty("country"));
			  softAssert.assertEquals(ch.getDPhone().toLowerCase(),p.getProperty("phone"));
				
			  softAssert.assertEquals(ch.getBFirstName().toLowerCase(),p.getProperty("firstname"));
			  softAssert.assertEquals(ch.getBCityPCode().toLowerCase(),p.getProperty("citypcode"));
			  softAssert.assertEquals(ch.getBCountry().toLowerCase(),p.getProperty("country"));
			  softAssert.assertEquals(ch.getBPhone().toLowerCase(),p.getProperty("phone"));
			  
			  softAssert.assertEquals(ch.getReviewHeading().toLowerCase(),"review your order");
			
			  List<String> rproduct = ch.getProductName();
			  softAssert.assertEquals(rproduct.getFirst().toLowerCase(),p.getProperty("checkoutProduct"));
			  
			  List<String> rprice = ch.getProductPrice();
			  softAssert.assertEquals(rprice.getFirst().toLowerCase(),p.getProperty("checkoutPrice"));
			  
			  softAssert.assertEquals(ch.getTPriceHeading().toLowerCase(), "total amount");
			  softAssert.assertEquals(ch.getTotalPrice().toLowerCase() , p.getProperty("checkoutPrice"));
			  
			  ch.clickPlaceOrder();
			  
			  PaymentPage pg = new PaymentPage(driver);
			  pg.setName(p.getProperty("cardname"));
			  pg.setCardNumber(p.getProperty("cardnumber"));
			  pg.setCVC(p.getProperty("cvc"));
			  pg.setMon(p.getProperty("month"));
			  pg.setYear(p.getProperty("year"));
			  pg.clickPayOrder();
			
			  softAssert.assertEquals(pg.getOPHeading().toLowerCase(), "order placed!");
			  softAssert.assertEquals(pg.getOPMsg().toLowerCase(), "congratulations! your order has been confirmed!");
			  pg.clickDownloadInvoice();
			  pg.clickContinue();
			  
			  softAssert.assertEquals(hp.isCategoryVisible(),true,"Verify if User is redirected to Home Page");  
			  
			  hp.clickLogOut();
			  
			  softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			Assert.fail();
		}
		logger.info("***** Finished TS07_TC01_ViewCartAndCheckout *****");
	}

}
