package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.HomePage;
import pageObject.SignupLogin;
import testBase.BaseClass;

public class TS08_TC01_SubscriptionHomePage extends BaseClass {
	
	@Test(groups= "Master")
	public void verify_subscription()
	{
		logger.info("***** Starting TS08_TC01_SubscriptionHomePage *****");
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
			  
			  softAssert.assertEquals(hp.getSubHeading().toLowerCase(), "subscription");
			  
			  hp.setSubEmail("");
			  hp.clickBtnSubscribe();
			  softAssert.assertEquals(hp.isEmailRequired().toLowerCase(), "true");
			  softAssert.assertEquals(hp.getEmailErrorMsg().toLowerCase(), "please fill out this field.");
			  
			  hp.setSubEmail(RandomString(3)+"@gmail.com");
			  hp.clickBtnSubscribe();
			  softAssert.assertEquals(hp.isSuccessAlertVisible(), true);
			  softAssert.assertEquals(hp.getSuccessAlert().toLowerCase(), "you have been successfully subscribed!" );
			  softAssert.assertAll();
			  
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** TS08_TC01_SubscriptionHomePage *****");
	}
	

}
