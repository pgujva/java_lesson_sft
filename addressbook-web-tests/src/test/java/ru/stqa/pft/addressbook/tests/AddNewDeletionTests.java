package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class AddNewDeletionTests extends TestBase {
  @Test
  public void testAddNewDeletion() {
    app.getNavigationHelper().goToHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("TestFirstName", "TestLastName", "TestAddress", "Test@mail.ru", "89040000011", "test1"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initAddNewDeletion(before.size() - 1);
    app.getContactHelper().submitAddNewDeletion();
    app.getNavigationHelper().goToHomepage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() -1);

    before.remove(before.size() - 1);
      Assert.assertEquals(before,after);

  }
}
