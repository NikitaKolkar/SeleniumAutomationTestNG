package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.HomePage;
import pageObject.SignupLogin;
import testBase.BaseClass;

public class TS03_TC01_Logout extends BaseClass{
	
	@Test(groups= {"Sanity","Master"})
	public void verify_logout()
	{
		logger.info("***** Starting TS03_TC01_Logout *****");
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
			  
			  logger.info("Validate if user can see LogOut button ");
			  softAssert.assertEquals(hp.isLogoutVisible(), true);
			  softAssert.assertEquals(hp.getLoggedInMsg(), "Logged in as "+p.getProperty("name"));
			  
			  hp.clickLogOut();
			  
			  softAssert.assertEquals(hp.isLogoutVisible(), false);
			  
			  softAssert.assertAll();  
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finished TS03_TC01_Logout *****");
	}

}
