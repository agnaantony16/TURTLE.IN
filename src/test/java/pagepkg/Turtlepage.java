package pagepkg;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Turtlepage {

	WebDriver driver;
	
	@FindBy(xpath="//*[@id=\"NewsletterPopup-newsletter-popup\"]/div/div/button")
	WebElement popup;
	
	@FindBy(xpath="//*[@id=\"SiteHeader\"]/div[1]/div/div[3]/div/div/a[1]")
	WebElement alogin;
	
	@FindBy(name="customer[email]")
	WebElement emailid;
	
	@FindBy(id="CustomerPassword")
	WebElement pass;
	
	@FindBy(xpath="//*[@id=\"customer_login\"]/p[1]/button")
	WebElement loginbutton;
	
	@FindBy(xpath="//*[@id=\"SiteHeader\"]/div[1]/div/div[1]/div/a[1]/img[1]")
	WebElement logoimg;
	
	@FindBy(xpath="//*[@id=\"SiteHeader\"]/div[1]/div/div[2]/ul")
	WebElement menu;
	
	@FindBy(xpath="//*[@id=\"SiteHeader\"]/div[1]/div/div[2]/ul/li[3]/a")
	WebElement newdrop;
	
	@FindBy(xpath="//*[@id=\"SiteHeader\"]/div[1]/div/div[2]/ul/li[3]/ul/li[2]/a")
	WebElement topwear;
	
	@FindBy(xpath="//*[@id=\"SiteHeader\"]/div[1]/div/div[2]/ul/li[3]/ul/li[2]/ul/li[2]/a")
	WebElement shirt;
	
	@FindBy(xpath="//*[@id=\"CollectionAjaxContent\"]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div/a/div[1]/div[1]/div/button")
	WebElement wishlist;

	@FindBy(xpath="//*[@id=\"CollectionAjaxContent\"]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div/a/div[1]/div[3]")
	WebElement casualshirt;
	
	@FindBy(name="Size")
	WebElement shirtsize;
	
	@FindBy(name="add")
	WebElement shirttocart;
	
	@FindBy(xpath="//*[@id=\"CartDrawerForm\"]/div[1]/div/div[2]/button")
	WebElement back;
	
	@FindBy(xpath="//*[@id=\"shopify-section-template--16826905002240__main\"]/div/header/div/p/a")
	WebElement contishop;
	
	@FindBy(xpath="//*[@id=\"SiteHeader\"]/div[1]/div/div[2]/ul/li[8]/a")
	WebElement denim;
	
	@FindBy(xpath="//*[@id=\"CollectionAjaxContent\"]/div/div/div[2]/div[2]/div/div[1]/div[2]/div[1]/div/a")
	WebElement denimproduct;
	
	@FindBy(name="SortBy")
	WebElement denimdropdown;
	
	@FindBy(name="add")
	WebElement denimcart;
	
	
	public Turtlepage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void popupwindow() throws Exception
	{
		Thread.sleep(2000);
		popup.click();
	}
	
	public void login() 
	{
		alogin.click();
	}

	public void acctlogin(String emails, String password)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//emailid.clear();
		emailid.sendKeys(emails);
		//pass.clear();
		pass.sendKeys(password);
		loginbutton.click();
		System.out.println(" ");
	}
	
	public void logodisplay()
	{
		boolean logo=logoimg.isDisplayed();
		if(logo)
		{
			System.out.println("Logo is present");
		}
		else
		{
			System.out.println("Logo is not present");
		}
		System.out.println(" ");
	}
	
	public void linkcount()
	{
		List<WebElement> li=driver.findElements(By.tagName("a"));
		System.out.println("Total no of links: "+li.size());
		
	}
	
	public void printmenus()
	{
		WebElement list=menu;
		List<WebElement> li=list.findElements(By.tagName("li"));
		for(WebElement el:li)
		{
			String s=el.getText();
			if(!s.isEmpty())
			{
				System.out.println(s.trim());
			}
			
		}
		System.out.println(" ");
	}
	
	public void dropdown()
	{
		Actions act=new Actions(driver);
		act.moveToElement(newdrop).moveToElement(topwear).moveToElement(shirt).click().perform();
	}
	
	public void titleverification()
	{
		String actualtitle=driver.getTitle();
		String exptitle="Account";
		Assert.assertEquals(actualtitle, exptitle);
	}
	
	public void addtowishlist()
	{
		wishlist.click();
		
	}
	
	public void addtocart()
	{
		casualshirt.click();
		shirtsize.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		shirttocart.click();
		back.click();
		
	}
	
	public void denimitems()
	{
		denim.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		Select s=new Select(denimdropdown);
		s.selectByVisibleText("Best selling");
		denimproduct.click();
		denimcart.click();
		back.click();
		
		
	}
	
	public void screenshot() throws Exception
	{
		
		/*JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-350)", "");
		Thread.sleep(3000);
		turtlelogo.click();*/
		
		File logoimage=logoimg.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(logoimage, new File("./Screenshot/LogoTurtle.png"));
		
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File("./Screenshot/Denimpage.png"));
	}
}
