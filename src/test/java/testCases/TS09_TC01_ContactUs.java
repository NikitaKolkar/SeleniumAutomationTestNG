package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObject.ContactUsPage;
import pageObject.HomePage;
import pageObject.SignupLogin;
import testBase.BaseClass;

public class TS09_TC01_ContactUs extends BaseClass{
	
	@Test(groups= {"Master","Regression"})
	public void verify_contactus()
	{
		logger.info("***** Starting TS09_TC01_ContactUs *****");
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
			  
			  hp.clickContactUs();
			  logger.info("Clicked on Contact Us link");
			  
			  ContactUsPage cu = new ContactUsPage(driver);
			  softAssert.assertEquals(cu.getHeading().toLowerCase(), "get in touch");
			  
			  logger.info("Providing user details along with message");
			  cu.setName(p.getProperty("name"));
			  cu.setEmail(p.getProperty("email"));
			  cu.setSubject(p.getProperty("subject"));
			  cu.setMessageBody(p.getProperty("subjectbody"));
			  cu.setFile(p.getProperty("filepath"));
			  softAssert.assertEquals(cu.clickSubmit().toLowerCase(), "press ok to proceed!");
			  softAssert.assertEquals(cu.getSuccessMsg().toLowerCase(), "success! your details have been submitted successfully.");
			  
			  hp.clickLogOut();
			 
			  softAssert.assertAll();
			  
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finished TS09_TC01_ContactUs *****");
	}

}
