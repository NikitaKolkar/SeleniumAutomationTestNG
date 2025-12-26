package testCases;

import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.CartPage;
import pageObject.HomePage;
import pageObject.ProductsPage;
import pageObject.SignupLogin;
import testBase.BaseClass;

public class TS05_TC01_AddProductsToCart extends BaseClass {

	@Test(groups={"Master","Regression"})
	public void verify_addtocart()
	{
		logger.info("***** Starting TS05_TC01_AddProductsToCart *****");
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
			  pr.setSearchInput(p.getProperty("searchProductName"));
			  pr.clickSearch();
			  List<String> productNames = pr.hoverOnAllProduct();
			  pr.clickAddToCartAndCNTShopping();
			  
			  hp.clickCart();
			  logger.info("Clicked on Cart Page");
			  
			  CartPage cp = new CartPage(driver);
			  List<String> cproducts = cp.getProductDesc();
			  Collections.sort(productNames);
			  Collections.sort(cproducts);
			  
			  logger.info("Verify expected products in the Cart");
			  if(productNames.size()==cproducts.size())
			  {
				  boolean isEqual = productNames.equals(cproducts);
				  softAssert.assertEquals(isEqual, true);
			  }
			  else
			  {
				  softAssert.assertTrue(false);
			  } 
			  
			  hp.clickLogOut();
			  
			  softAssert.assertAll();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			Assert.fail();
		}
		logger.info("***** Finished TS05_TC01_AddProductsToCart *****");
	}
	

}
