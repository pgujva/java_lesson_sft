package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initAddNewContactCreation() {
    click(By.linkText("add new"));
  }


  public void fillAddContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("mobile"), contactData.getMobile());


    if (creation) {
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


  public void initAddNewModification(int index) {
    wd.findElements(By.cssSelector("[title='Edit']")).get(index).click();
  }

  public void submitAddNewModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }


  public void initAddNewDeletion(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void submitAddNewDeletion() {
    wd.switchTo().alert().accept();
  }

  public void createContact(ContactData contact, boolean b) {
    initAddNewContactCreation();
    fillAddContactForm(contact, true);
    submitAddContact();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.cssSelector("input[name='selected[]']"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }


  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String lastname = element.findElements(By.tagName("td")).get(1).getText();
      String firstname = element.findElements(By.tagName("td")).get(2).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      ContactData contact = new ContactData(id, firstname, lastname, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}