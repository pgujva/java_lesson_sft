package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class AddNewDeletionTests extends TestBase {
  @BeforeMethod
  void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size()== 0) {
      app.contact().create(new ContactData("TestFirstName", "TestLastName", "TestAddress", "Test@mail.ru", "89040000011", "test1"), true);
    }
  }

  @Test
  public void testAddNewDeletion() {
    List<ContactData> before = app.contact().list();
    int index= before.size() - 1;
    app.contact().delete(index);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }


}
