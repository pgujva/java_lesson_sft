package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class AddNewCreationTests extends TestBase {


  @Test
  public void TestAddNewCreationTests() {
    app.getNavigationHelper().goToHomepage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initAddNewContactCreation();
    app.getContactHelper().fillAddContactForm(new ContactData("TestFirstName", "TestLastName", "TestAddress", "Test@mail.ru", "89040000011", "test1"),true);
    app.getContactHelper().submitAddContact();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

  }
}
