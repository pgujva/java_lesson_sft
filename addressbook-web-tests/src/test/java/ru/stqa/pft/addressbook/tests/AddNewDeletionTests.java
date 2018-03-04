package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewDeletionTests extends TestBase {
  @Test
  public void testAddNewDeletion() {
    app.getNavigationHelper().goToHomepage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("TestFirstName", "TestLastName", "TestAddress", "Test@mail.ru", "89040000011", "test1"), true);
    }
    app.getContactHelper().initAddNewDeletion();
    app.getContactHelper().submitAddNewDeletion();
    app.getNavigationHelper().goToHomepage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }
}
