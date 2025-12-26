package testCases;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.CartPage;
import pageObject.HomePage;
import pageObject.ProductsPage;
import pageObject.SignupLogin;
import testBase.BaseClass;

public class TS06_TC01_RemoveProductFromCart extends BaseClass {
	
	@Test(groups= {"Master","Regression"})
	public void verify_removeproductfromcart()
	{
		logger.info("***** TS06_TC01_RemoveProductFromCart *****");
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
			 
			 hp.clickProducts();
			 logger.info("Clicked on Products page");
			 
			 ProductsPage pr = new ProductsPage(driver);
			 logger.info("Selecting Products Category");
			 pr.clickWomenCategory();
			 Thread.sleep(2000);
			 pr.clickTopSub();
			 
			 pr.clickAddToCartAndCNTShopping();
			 
			 hp.clickCart();
			 logger.info("Clicked on Cart page");
			 
			 CartPage cp = new CartPage(driver);
			 String pname = p.getProperty("removeFromCart");
			 cp.clickDeleteProduct(pname);
			 List<String> prdInCart = cp.getProductDesc();
			 if(prdInCart.contains(pname))
			 {
				 softAssert.assertTrue(false);
			 }
			 if(!prdInCart.contains(pname))
			 {
				 softAssert.assertTrue(true);
			 }
			 
			 hp.clickLogOut();
			 
			 softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			Assert.fail();
		}
		logger.info("***** TS06_TC01_RemoveProductFromCart *****");
	}

}
