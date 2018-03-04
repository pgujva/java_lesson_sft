package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initAddNewContactCreation() {
    click(By.linkText("add new"));
  }


  public void fillAddContactForm(ContactData contactData, boolean creation ) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("mobile"), contactData.getMobile());


    if(creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitAddContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }


  public void initAddNewModification() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitAddNewModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }


  public void initAddNewDeletion() {
    List<WebElement> contacts = wd.findElements(By.cssSelector("input[name='selected[]']"));
    contacts.get(0).click();
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void submitAddNewDeletion() {
    wd.switchTo().alert().accept();
  }

  public void createContact(ContactData contact, boolean b) {
    initAddNewContactCreation();
    fillAddContactForm(contact,true);
    submitAddContact();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.cssSelector("input[name='selected[]']"));
  }

  public int getContactCount() {
    int n= wd.findElements(By.name("selected[]")).size();
    System.out.println(n);
    return wd.findElements(By.name("selected[]")).size();
  }
}