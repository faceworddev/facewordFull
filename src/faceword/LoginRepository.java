package faceword;

import faceword.Credential;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginRepository 
{
    final static String facebookLoginUrl = "https://www.facebook.com/login.php";
    final static String instagramLoginUrl = "https://www.instagram.com/accounts/login";
    final static String twitterLoginUrl = "https://twitter.com/login";
    final static String pinterestLoginUrl = "https://www.pinterest.com/login";
    
    public static void loginFacebook(Credential credential, ChromeDriver driver)
    {
        driver.get(facebookLoginUrl);

        if(driver.findElement(By.id("email")) != null)
        {
            WebElement email = driver.findElement(By.id("email"));

            email.sendKeys(credential.getUsername());

            WebElement password = driver.findElement(By.id("pass"));

            password.sendKeys(credential.getPassword());
            driver.findElement(By.id("loginbutton")).click();
        }
    }
	
    public static void loginInstagram(Credential credential, ChromeDriver driver)
    {
        driver.get(instagramLoginUrl);

        WebElement email = driver.findElement(By.xpath("/html/body/span/section/main/div/article/div/div[1]/div/form/div[1]/input"));

        email.sendKeys(credential.getUsername());

        WebElement password = driver.findElement(By.xpath("/html/body/span/section/main/div/article/div/div[1]/div/form/div[2]/input"));

        password.sendKeys(credential.getPassword());
        driver.findElement(By.xpath("/html/body/span/section/main/div/article/div/div[1]/div/form/span/button")).click();
    }
	
    public static void loginTwitter(Credential credential, ChromeDriver driver)
    {
        driver.get(twitterLoginUrl);

        if(driver.findElement(By.id("page-container")) != null)
        {

            WebElement email = driver.findElement(By.xpath("//*[@id='page-container']/div/div[1]/form/fieldset/div[1]/input"));

            email.sendKeys(credential.getUsername());

            WebElement password = driver.findElement(By.xpath("//*[@id='page-container']/div/div[1]/form/fieldset/div[2]/input"));

            password.sendKeys(credential.getPassword());

            driver.findElement(By.xpath("//*[@id='page-container']/div/div[1]/form/div[2]/button")).click();
        }
    }

    public static void loginPinterest(Credential credential, ChromeDriver driver)
    {

        driver.get(pinterestLoginUrl);

        if (driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div/div/div[3]/form/fieldset[1]/input")) != null)
        {
            WebElement email = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div/div/div[3]/form/fieldset[1]/input"));

            email.sendKeys(credential.getUsername());

            WebElement password = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div/div/div[3]/form/fieldset[2]/input"));

            password.sendKeys(credential.getPassword());
            driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div/div/div[3]/form/button")).click();
        }
    }
}
