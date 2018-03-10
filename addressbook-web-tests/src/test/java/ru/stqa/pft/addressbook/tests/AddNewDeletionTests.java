package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.Set;

public class AddNewDeletionTests extends TestBase {
  @BeforeMethod
  void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size()== 0) {
      app.contact().create(new ContactData()
              .withFirstname("TestFirstName").withLastname("TestLastName").withAddress("TestAddress").withEmail("Test@mail.ru").withMobile("89040000011").withGroup("test1"), true);
    }
  }

  @Test
  public void testAddNewDeletion() {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }


}
