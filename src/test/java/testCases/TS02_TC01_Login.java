package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.HomePage;
import pageObject.SignupLogin;
import testBase.BaseClass;

public class TS02_TC01_Login extends BaseClass {
	
	@Test(groups={"Sanity","Master","Regression"})
	public void verify_login()
	{
		logger.info("***** Starting TS02_TC01_Login *****");
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
			  
			  softAssert.assertEquals(hp.isLogoutVisible(), true);
			  softAssert.assertEquals(hp.getLoggedInMsg(), "Logged in as "+p.getProperty("name"));
			  softAssert.assertAll();
			  
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finished TS02_TC01_Login *****");
	}

}
