package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase {


  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"),email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("input[value='Update User']"));
  }
  public void adminlogin(String username, String password) {
    wd.get(app.getProperty("web.baseUrl"));
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }
  public void userpasschange() {
    click(By.linkText("Manage Users"));
    click(By.linkText("user1"));
    click(By.xpath("//div[4]/form[1]/input[3]"));
  }

  public void finishchangepassword(String confirmationLink, String userpassword) {
    wd.get(confirmationLink);
    type(By.name("password"),userpassword);
    type(By.name("password_confirm"),userpassword);
    click(By.cssSelector("input.button"));
  }
}
