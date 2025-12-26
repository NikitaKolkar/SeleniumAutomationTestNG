package testCases;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObject.HomePage;
import pageObject.SignUp;
import pageObject.SignupLogin;
import testBase.BaseClass;

public class TS01_TC01_SignUpAndDeleteAccount extends BaseClass {

	
	@Test(groups={"Sanity","Master"})
	public void verify_signUp()
	{
		SoftAssert softAssert = new SoftAssert();
		logger.info("***** Starting TS01_TC01_SignUpAndDeleteAccount *****");
		try 
		{
	 	  HomePage hp = new HomePage(driver);
		  hp.clickSignLogin();
		  logger.info("Clicked on Signup / Login button");
		
	  	  SignupLogin sl = new SignupLogin(driver);
		  String sname = RandomString(4);
		  logger.info("Providing Name and Email details");
		  sl.setName(sname);
		  sl.setSignupEmail(sname+"@gmail.com");
		  sl.clickSignup();

		  SignUp sp = new SignUp(driver);
		  sp.setTitle(RandomTitle());
		  logger.info("Providing details in 'Enter Account Information' section");
		  sp.setName(sname);
		  String email = sp.getEmail();
		  softAssert.assertEquals(email, sname+"@gmail.com");
		  sp.setPassword(RandomString(5));
		  sp.setDay(Integer.toString(RandomDay()));
		  sp.setMonth(RandomMonth());
		  sp.setYear(Integer.toString(RandomYear()));
		  sp.setChkNews(true);
		  sp.setChkOffer(true);
		
		  logger.info("Providing details in 'Address Information' section");
		  sp.setFirstName(RandomString(4));
		  sp.setLastName(RandomString(5));
		  sp.setCompany(RandomString(5));
		  sp.setAddress1(RandomString(5));
		  sp.setAddress2(RandomString(5));
		  sp.setCountry(RandomCountry());
		  sp.setState(RandomString(6));
		  sp.setCity(RandomString(5));
		  sp.setZipCode(RandomNumber(5));
		  sp.setMobile(RandomNumber(10));
		  sp.clickCreateAccount();
		  logger.info("Clicked on Create Account button");
		  String accCreatedMsg = sp.getSuccessMessage();
		  
		  logger.info("Validating expected message..");
		  if(accCreatedMsg.equals("ACCOUNT CREATED!"))
		  {
			  softAssert.assertTrue(true);
		  }
		  else
		  {
			  softAssert.assertTrue(false);
		  }
		  
		  hp.clickSignLogin();
		  
		  logger.info("Clicking on Delete Account button");
		  
		  hp.clickDeleteAccount();
		  
		  softAssert.assertEquals(hp.getDeleteAccMsg().toLowerCase(),"account deleted!");
		  softAssert.assertEquals(hp.isDeleteAccountVisible(), false);
	
		  softAssert.assertAll(); 
		}
		catch(Exception e)
		{
			logger.error("Test Failed");
			System.out.println(e.getMessage());
			Assert.fail();
		}
		logger.info("***** Finished SignUpAndDeleteAccount *****");
	}
	
}


