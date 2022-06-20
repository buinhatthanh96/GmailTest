package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class Main {
    public static void main(String[] args) throws InterruptedException{

        System.out.println("Hello Bui Nhat Thanh");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        //Open browser with Gmail page
        String gmailUrl = "https://www.gmail.com/";
        driver.get(gmailUrl);
        String myEmail = "thanhbn9641@gmail.com";

        //a.Login gmail
        driver.findElement(By.name("identifier")).sendKeys(myEmail);
        WebElement next = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Tiếp theo']")));
        next.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.name("password")).click();
        driver.findElement(By.name("password")).sendKeys("Thanh@1234");
        driver.findElement(By.xpath("//span[text()='Tiếp theo']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        //b.Compose email
        //Open new Message
        WebElement compose = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()=\"Compose\" and @role=\"button\"]")));
        compose.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='New Message']")));
        //Input data test
        String destinationEmail = "buinhatthanh96@gmail.com";
        String subjectEmail = "Test Subject Gmail";
        String bodyEmail = "Test message body";
        String result;

        WebElement emailElement = driver.findElement(By.xpath("//textarea[@aria-label=\"To\"]"));
        WebElement subjectElement = driver.findElement(By.xpath("//input[@name=\"subjectbox\"]"));
        WebElement bodyMsgElement = driver.findElement(By.xpath("//div[@aria-label='Message Body']"));

        //Input email
        emailElement.sendKeys(destinationEmail);

        //Input subject
        subjectElement.sendKeys(subjectEmail);
        String actualSubject = driver.findElement(By.xpath("//input[@name=\"subjectbox\"]")).getAttribute("value").toString();
        result = (actualSubject.equals(subjectEmail)) ? "PASS" : "FAILED: Subject not mactch\n\tActual: " + actualSubject + "\n\t" + "Expected: " + subjectEmail;
        System.out.println(result);

        //Input body message
        bodyMsgElement.sendKeys(bodyEmail);
        String actualBody = bodyMsgElement.getText();
        result = (actualBody.equals(bodyEmail)) ? "PASS" : "FAILED \n\tActual: " + actualBody + "\n\t" + "Expected: " + bodyEmail;
        System.out.println(result);

        //c.Send the email
        driver.findElement(By.xpath("//div[@role=\"dialog\"]//..//div[text()='Send']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Message sent\"]")));

        driver.close();
    }

}