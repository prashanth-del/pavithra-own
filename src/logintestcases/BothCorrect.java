package logintestcases;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
public class BothCorrect {
	
	String[][] data=null;
	
	
	@DataProvider(name="Login")
	public String[][] login() throws BiffException, IOException
	{
		data=getexcel();
		return data;
	}
	
	public String[][] getexcel() throws BiffException, IOException
	{
		FileInputStream excel = new FileInputStream("‪C:\\Users\\Pavithra\\Desktop\\datadriven.xls");
	     
		Workbook workbok1 = Workbook.getWorkbook(excel);
		
		Sheet sheet =workbok1.getSheet(0);
		
		int rowcount= sheet.getRows();
		int colcount= sheet.getColumns();
		
		String testdata[][]= new String[rowcount-1][colcount];
		
		for(int i=1 ; i<rowcount ;i++)

		{
			for(int j=0 ; i<colcount ;j++)
			{
				testdata[i-1][j]=sheet.getCell(j,i).getContents();
			}
		}
	
	return testdata;
	}
	
	
	@Test(dataProvider="Login")
     public void loginwithcorrectusername(String uname,String pwd)
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Pavithra\\Desktop\\automation\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		WebElement username=driver.findElement(By.id("txtUsername"));
		username.sendKeys(uname);
		
		WebElement pswd=driver.findElement(By.id("txtPassword"));
		pswd.sendKeys(pwd);
		
		WebElement login=driver.findElement(By.id("btnLogin"));
		login.click();
		driver.quit();
		
	}


}
