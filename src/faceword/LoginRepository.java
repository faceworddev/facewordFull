package faceword;

import faceword.Credential;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.chrome.ChromeDriver;



public class LoginRepository 
{
    final static String facebookLoginUrl = "https://www.facebook.com/login.php";
    final static String instagramLoginUrl = "https://www.instagram.com/accounts/login";
    final static String twitterLoginUrl = "https://twitter.com/login";
    final static String pinterestLoginUrl = "https://www.pinterest.com/login";
    final static String gmailLoginUrl = "gmail.com";
        
    public static void loginFacebook(Credential credential)
    {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\tcordonnier2\\Documents\\NetBeansProjects\\FaceWord\\ExternalLibraries\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(facebookLoginUrl);

        if(driver.findElement(By.id("email")) != null)
        {
            try 
            {
                Thread.sleep(5000);
            } 
            catch (InterruptedException ex) 
            {
                Logger.getLogger(LoginRepository.class.getName()).log(Level.SEVERE, null, ex);
            }
            WebElement email = driver.findElement(By.id("email"));

            email.sendKeys(credential.getUsername());

            WebElement password = driver.findElement(By.id("pass"));

            password.sendKeys(credential.getPassword());
            driver.findElement(By.id("loginbutton")).click();
        }
			
        try 
        {
            Thread.sleep(1500);
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(LoginRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
			
        driver.close();		
    }
	
    public static void loginInstagram(Credential credential)
    {

        System.setProperty("webdriver.chrome.driver","C:\\Users\\tcordonnier2\\Documents\\NetBeansProjects\\FaceWord\\ExternalLibraries\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get(instagramLoginUrl);

        try 
        {
            Thread.sleep(5000);
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(LoginRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        WebElement email = driver.findElement(By.xpath("/html/body/span/section/main/div/article/div/div[1]/div/form/div[1]/input"));

        email.sendKeys(credential.getUsername());

        WebElement password = driver.findElement(By.xpath("/html/body/span/section/main/div/article/div/div[1]/div/form/div[2]/input"));

        password.sendKeys(credential.getPassword());
        driver.findElement(By.xpath("/html/body/span/section/main/div/article/div/div[1]/div/form/span/button")).click();

        try 
        {
            Thread.sleep(1500);
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(LoginRepository.class.getName()).log(Level.SEVERE, null, ex);
        }

        driver.close();
    }
	
    public static void loginTwitter(Credential credential)
    {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\tcordonnier2\\Documents\\NetBeansProjects\\FaceWord\\ExternalLibraries\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get(twitterLoginUrl);

        if(driver.findElement(By.id("page-container")) != null)
        {
            try 
            {
                Thread.sleep(5000);
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(LoginRepository.class.getName()).log(Level.SEVERE, null, ex);
            }

            WebElement email = driver.findElement(By.xpath("//*[@id='page-container']/div/div[1]/form/fieldset/div[1]/input"));

            email.sendKeys(credential.getUsername());

            WebElement password = driver.findElement(By.xpath("//*[@id='page-container']/div/div[1]/form/fieldset/div[2]/input"));

            password.sendKeys(credential.getPassword());

            driver.findElement(By.xpath("//*[@id='page-container']/div/div[1]/form/div[2]/button")).click();
        }

        try 
        {
            Thread.sleep(1500);
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(LoginRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void loginPinterest(Credential credential)
    {
        try 
        {
            System.setProperty("webdriver.chrome.driver","C:\\Users\\tcordonnier2\\Documents\\NetBeansProjects\\FaceWord\\ExternalLibraries\\chromedriver.exe");
            ChromeDriver driver = new ChromeDriver();
            
            driver.get(pinterestLoginUrl);
            
            if (driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div/div/div[3]/form/fieldset[1]/input")) != null)
            {
                Thread.sleep(5000);
                WebElement email = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div/div/div[3]/form/fieldset[1]/input"));
                
                email.sendKeys(credential.getUsername());
                
                WebElement password = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div/div/div[3]/form/fieldset[2]/input"));
                
                password.sendKeys(credential.getPassword());
                driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div/div/div[3]/form/button")).click();
            }
            
            Thread.sleep(1500);
        } 
        catch (InterruptedException ex) 
        {
            Logger.getLogger(LoginRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

public static void loginGmail(Credential credential)
{
    try 
    {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\tcordonnier2\\Documents\\NetBeansProjects\\FaceWord\\ExternalLibraries\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        driver.get(gmailLoginUrl);

        if (driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/form/div[1]/div/div[1]/div/div/input[1]")) != null) 
        {
            Thread.sleep(5000);
            WebElement email = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/form/div[1]/div/div[1]/div/div/input[1]"));

            email.sendKeys(credential.getUsername());

            driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/form/div[1]/div/input")).click();

            Thread.sleep(5000);

            driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/form/div[2]/div/label/input")).click();

            Thread.sleep(1000);

            WebElement password = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/form/div[2]/div/div[2]/div/div/input[2]"));

            password.sendKeys(credential.getPassword());
            driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[1]/form/div[2]/div/input[1]")).click();
        }

        Thread.sleep(1500);

        } 
        catch (InterruptedException ex) {
            Logger.getLogger(LoginRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
