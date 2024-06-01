package testpkg;


import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pagepkg.Turtlepage;
import utilities.Excelutils;
//password: 1234abcd
public class Turtletest {

	WebDriver driver;
	String baseurl="https://www.turtle.in";
	Turtlepage ob;
	
	@BeforeTest
	public void setup()
	{
		driver=new ChromeDriver();
		driver.get(baseurl);
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod
	public void befmthd()
	{
		ob=new Turtlepage(driver);
	}
	
	@Test(priority = 1)
	public void popuptest() throws Exception 
	{
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ob.popupwindow();
		ob.login();
		
		
	}
	
	@Test(priority = 2)
	public void logintest() throws Exception 
	{
		
		String xl="C:\\Users\\hp admin\\OneDrive\\Book 18.xlsx";
		String sheet="Sheet1";
		int rowcount=Excelutils.getrowcount(xl, sheet);
		for(int i=1;i<=rowcount;i++)
		{
			String email=Excelutils.getcellvalue(xl, sheet, i, 0);
			System.out.println("email="+email);
			String password=Excelutils.getcellvalue(xl, sheet, i, 1);
			System.out.println("password="+password);
			ob.acctlogin(email, password);
			
		}
		Thread.sleep(1000);	
		
		
	}
	
	@Test(priority = 3)
	public void logonmenu()
	{
		ob.logodisplay();	
	}
	
	@Test(priority = 4)
	public void linksandmenus()
	{
		ob.printmenus();
		ob.linkcount();
	}
	
	@Test(priority = 5)
	public void dropnTitle()
	{
		ob.titleverification();
		ob.dropdown();
		ob.addtowishlist();
	}
	
	@Test(priority = 6)
	public void shirts()
	{
		ob.addtocart();	
	}
	
	@Test(priority = 7)
	public void denims()
	{
		ob.denimitems();
	}
	
	@Test(priority = 8)
	public void screenshots() throws Exception
	{
		Thread.sleep(2000);
		ob.screenshot();
	}
	
	
}
