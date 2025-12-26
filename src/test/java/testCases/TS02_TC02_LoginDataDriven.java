package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.SignupLogin;
import testBase.BaseClass;
import utilities.DataProviders;

public class TS02_TC02_LoginDataDriven extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups= {"DataDriven","Master"})
	public void verify_LoginDataDriven(String email, String pwd, String res)
	{
		logger.info("***** Starting TS02_TC02_LoginDataDriven *****");
		try
		{
			HomePage hp = new HomePage(driver);
			  hp.clickSignLogin();
			  logger.info("Clicked on Signup / Login button");
			  
			  SignupLogin sl = new SignupLogin(driver);
			  logger.info("Providing Email and Password");
			  sl.setLoginEmail(email);
			  sl.setPassword(pwd);
			  sl.clickLogin();
			  
			  logger.info("Verify login functionality for available credentials");
			  if(res.equalsIgnoreCase("Valid"))
			  {
				  if(hp.isLogoutVisible()==true)
				  {
					  Assert.assertTrue(true);
					  hp.clickLogOut();
			      }
				  else
				  {
					  Assert.assertTrue(false);
				  }
			  }
			  if(res.equalsIgnoreCase("Invalid"))
			  {
				  if(hp.isLogoutVisible()==true)
				  {
					  hp.clickLogOut();
					  Assert.assertTrue(false);
			      }
				  else
				  {
					  if(hp.isErrorVisible()==true)
					  {
						  Assert.assertTrue(true);
					  }
				  }
			  }
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finished TS02_TC02_LoginDataDriven *****");
	}

}
