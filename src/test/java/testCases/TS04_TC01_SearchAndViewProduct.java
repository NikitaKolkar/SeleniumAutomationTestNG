package testCases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.HomePage;
import pageObject.ProductsPage;
import pageObject.SignupLogin;
import testBase.BaseClass;

public class TS04_TC01_SearchAndViewProduct extends BaseClass{
	
	@Test(groups= {"Master","Regression"})
	public void verify_searchandviewproduct()
	{
		logger.info("***** Starting TS04_TC01_SearchAndViewProduct *****");
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
	          
			  ProductsPage pr = new ProductsPage(driver);
			  pr.setSearchInput(p.getProperty("searchProductName"));
			  pr.clickSearch();
			  
			  logger.info("Verify Search result heading");
			  softAssert.assertEquals(pr.isHeadingDisplayed(), true);
			  softAssert.assertEquals(pr.getHeadingText().toUpperCase(), "SEARCHED PRODUCTS");
			  
			  String expProducts = p.getProperty("jeansProducts");
			  //String expProducts = p.getProperty("sareeProducts");
			  List<String> expPrdList = Arrays.asList(expProducts.split(","));
			  
              //Verify searched product count
			  logger.info("Verify Expected number of products displayed");
			  int count = pr.getProductsCount();
			  softAssert.assertEquals(count, expPrdList.size(),"No. of products are as expected");
			  
			  //Verify Product Names 
			  logger.info("Verify Product Names");
			  List<String> productName = pr.hoverOnAllProduct();
			  List<String> copyExpPrdList = new ArrayList<>(expPrdList);
			  Collections.sort(productName);
			  Collections.sort(copyExpPrdList);
			
			  if(productName.size()==copyExpPrdList.size())
			  {
				  boolean expResult = productName.equals(copyExpPrdList);
				  softAssert.assertEquals(expResult, true);
			  }
			  else
			  {
				  softAssert.assertTrue(false);
			  }
			  
			  //click on View Product of all products and verify details
			  logger.info("Verify View Product");
			  List<String> prdNotMatch =pr.clickViewProductAll(expPrdList);
			  if(prdNotMatch.isEmpty())
			  {
				  softAssert.assertTrue(true);
			  }
			  else
			  {
				  for(String list:prdNotMatch)
				  {
					  System.out.println("Product Names which are not matching with the expected Products "+list);
				  }
				  softAssert.assertTrue(false);
			  }
			  
			  softAssert.assertAll();
			  
		}
		catch(Exception e)
		{
			System.out.println(e);
			Assert.fail();
		}
		logger.info("***** Finished TS04_TC01_SearchAndViewProduct *****");
	}

}
