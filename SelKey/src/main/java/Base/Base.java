package Base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Base {

    public WebDriver driver;
    public Properties prop;
    public static String strUsrdirectory;
    public WebDriver init_driver(String browser) {
        strUsrdirectory=System.getProperty("user.dir");
        //System.out.println("**** **  " +strUsrdirectory);
        if (browser.equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "/home/sanumenon/IdeaProjects/Automation/SelKey/src/main/resources/ChromeDriver/chromedriver");
            if (prop.getProperty("headless").equals("yes")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
            } else {
                driver = new ChromeDriver();
                driver.manage().window().maximize();
            }
        }
        return driver;
    }

    public Properties init_properties(){
        prop = new Properties();
        try{
            FileInputStream ip = new FileInputStream("/home/sanumenon/IdeaProjects/Automation/SelKey/src/main/java/config/config.properties");
            prop.load(ip);
        }
        catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return(prop);
    }
}
