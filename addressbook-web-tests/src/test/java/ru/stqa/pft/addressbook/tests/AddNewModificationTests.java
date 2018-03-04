package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class AddNewModificationTests extends TestBase {

  @Test
  public void testAddNewModification() {
    app.getNavigationHelper().goToHomepage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("TestFirstName", "TestLastName", "TestAddress", "Test@mail.ru", "89040000011", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initAddNewModification(before.size() - 1);
    app.getContactHelper().fillAddContactForm(new ContactData("TestFirstName1", "TestLastName1", "TestAddress1", "Test111@mail.ru", "89040000011", null), false);
    app.getContactHelper().submitAddNewModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());
  }
}
