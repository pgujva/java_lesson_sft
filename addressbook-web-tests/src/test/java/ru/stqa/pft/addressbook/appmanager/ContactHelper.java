package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void initAddNewContactCreation() {
    click(By.linkText("add new"));
  }


  public void fillAddContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("mobile"), contactData.getMobile());
  }


  public void submitAddContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void goToHomePage() {
    click(By.linkText("home page"));
  }

  public void initAddNewModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitAddNewModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }
}
