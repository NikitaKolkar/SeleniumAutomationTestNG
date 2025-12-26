package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.HomePage;
import pageObject.ProductsPage;
import pageObject.SignupLogin;
import testBase.BaseClass;

public class TS10_TC01_AddReviewOnProduct extends BaseClass {
	
	@Test(groups= {"Master","Regression"})
	public void verify_addreviewonproduct()
	{
		logger.info("***** Starting TS10_TC01_AddReviewOnProduct *****");
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
			  logger.info("Providing the product name in search box");
			  pr.setSearchInput(p.getProperty("searchProductName"));
			  pr.clickSearch();
			  
			  pr.clickViewProductSelected(p.getProperty("reviewproduct"));
			  logger.info("Clicking on View Product button for the specified product");
			  
			  softAssert.assertEquals(pr.getReviewHeading().toLowerCase(), "write your review");
			  pr.setReviewerName(p.getProperty("name"));
			  pr.setReviewerID(p.getProperty("email"));
			  pr.setReviewBody(p.getProperty("reviewbody"));
			  pr.clickSubmitReview();
			  softAssert.assertEquals(pr.getReviewSuccessMsg().toLowerCase(), "thank you for your review.");
			  
			  softAssert.assertAll();
			  
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			Assert.fail();
		}
		logger.info("***** Finished TS10_TC01_AddReviewOnProduct *****");
	}

}
