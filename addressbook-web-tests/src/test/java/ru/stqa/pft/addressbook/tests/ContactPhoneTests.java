package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size()== 0) {
      app.contact().create(new ContactData()
              .withFirstname("TestFirstName").withLastname("TestLastName").withAddress("TestAddress").withHome("111").withMobile("222").withWork("333")
              .withEmail("Test@mail.ru").withEmail2("Test2@mail.ru").withEmail3("Test3@mail.ru").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactPhones(){
    app.goTo().homePage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().InfoFromEditForm(contact);
  }
}
