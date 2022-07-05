package executionEngine;

import Base.Base;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.*;
import java.util.Properties;

public class KeywordEngine {

    public WebDriver driver;
    public Properties prop;
    public static XSSFWorkbook wb;
    public static XSSFSheet sheet;
    public Base objeBase;
    public WebElement we;
    public final String SCENARIO_SHEET_PATH = "/home/sanumenon/IdeaProjects/Automation/SelKey/src/main/resources/ExcelFiles/DemoFile.xlsx";

    public void startExecution(String strSheetname) throws FileNotFoundException {
        FileInputStream file = null;
        String selLocatorName = "";
        String selLocatorValue = "";

        try {
            file = new FileInputStream(new File(SCENARIO_SHEET_PATH));
            wb = new XSSFWorkbook(file);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = wb.getSheet(strSheetname);
        int k = 0;
        try {
            for (int i = 0; i < sheet.getLastRowNum(); i++) {

                String locatorcolvalue = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
                if (!locatorcolvalue.equalsIgnoreCase("NA")) {
                    selLocatorName = locatorcolvalue.split("=",2)[0].trim();
                    selLocatorValue = locatorcolvalue.split("=",2)[1].trim();
                }
                String action = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
                String value = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
                switch (action) {
                    case "openBrowser":
                        objeBase = new Base();
                        prop = objeBase.init_properties();
                        if (value.isEmpty() || value.equals("NA"))
                            driver = objeBase.init_driver(prop.getProperty("browser"));
                        else
                            System.out.println("***  Browser Open" +value );
                            driver = objeBase.init_driver(value);
                        break;
                    case "navigate":
                        if (value.isEmpty() || value.equals("NA"))
                            driver.get(prop.getProperty("url"));
                        else
                            driver.get(value);
                        break;
                    case "closeBrowser":
                        driver.quit();
                        break;
                    default:
                        break;
                }
                System.out.println("selLocatorName " + selLocatorName);
                switch (selLocatorName) {
                    case "id":
                        we = driver.findElement(By.id(selLocatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            we.clear();
                            we.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            we.click();
                        }
                        selLocatorName = "";
                        break;
                    case "xpath":
                        Thread.sleep(15000);
                        we = driver.findElement(By.xpath(selLocatorValue));
                        if (action.equalsIgnoreCase("sendkeys")) {
                            we.click();
                            we.clear();
                            we.sendKeys(value);
                        } else if (action.equalsIgnoreCase("click")) {
                            Thread.sleep(15000);
                            we.click();
                        }
                        selLocatorName = "";
                        break;
                    default:
                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
