package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class AddNewDeletionTests extends TestBase {
  @BeforeMethod
  void ensurePreconditions() {
    app.goTo().goToHomepage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("TestFirstName", "TestLastName", "TestAddress", "Test@mail.ru", "89040000011", "test1"), true);
    }
  }

  @Test
  public void testAddNewDeletion() {
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initAddNewDeletion(before.size() - 1);
    app.getContactHelper().submitAddNewDeletion();
    app.goTo().goToHomepage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
