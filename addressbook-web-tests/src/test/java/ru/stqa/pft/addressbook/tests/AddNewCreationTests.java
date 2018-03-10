package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


import java.util.Set;

public class AddNewCreationTests extends TestBase {


  @Test
  public void TestAddNewCreationTests() {
    app.goTo();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("TestFirstName5").withLastname("TestLastName").withAddress("TestAddress").withEmail("Test@mail.ru").withMobile("89040000011").withGroup("test1");
    app.contact().create(contact,true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before,after);
  }
}
