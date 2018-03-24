package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void initAddNewContactCreation() {
    click(By.linkText("add new"));
  }

  public void modify(ContactData contact) {
    initAddNewModificationById(contact.getId());
    fillAddContactForm(contact, false);
    submitAddNewModification();
    contactCache = null;
    returnToHomePage();
  }

  public void fillAddContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("home"), contactData.getHome());
    type(By.name("work"), contactData.getWork());
   // attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
   //   new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void submitAddContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }


  public void initAddNewModification(int index) {
    wd.findElements(By.cssSelector("[title='Edit']")).get(index).click();
  }

  public void initAddNewModificationById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
  }

  public void submitAddNewModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }


  public void initAddNewDeletion(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void initAddNewDeletionById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void submitAddNewDeletion() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact, boolean b) {
    initAddNewContactCreation();
    fillAddContactForm(contact, true);
    submitAddContact();
    contactCache = null;
    returnToHomePage();
  }


  public void delete(ContactData contact) {
    initAddNewDeletionById(contact.getId());
    submitAddNewDeletion();
    contactCache = null;
    returnToHomePage();
  }


  public boolean isThereAContact() {
    return isElementPresent(By.cssSelector("input[name='selected[]']"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String lastname = element.findElements(By.tagName("td")).get(1).getText();
      String firstname = element.findElements(By.tagName("td")).get(2).getText();
      String address = element.findElements(By.tagName("td")).get(3).getText();
      String allEmails = element.findElements(By.tagName("td")).get(4).getText();
      String allPhones = element.findElements(By.tagName("td")).get(5).getText();
      // String[] phones = allphones.split("\n");
      //String[] emails = allemail.split("\n");
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address).withAllPhones(allPhones).withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

  public ContactData InfoFromEditForm(ContactData contact) {
    initAddNewModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withAddress(address)
            .withHome(home).withMobile(mobile).withWork(work).withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}