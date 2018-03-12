package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

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
    assertThat(contact.getAddress(), equalTo(cleaned1(contactInfoFromEditForm.getAddress())));
    assertThat(contact.getHome(), equalTo(cleaned(contactInfoFromEditForm.getHome())));
    assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEditForm.getWork())));
    assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditForm.getMobile())));
    assertThat(contact.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));
    assertThat(contact.getEmail2(), equalTo(contactInfoFromEditForm.getEmail2()));
    assertThat(contact.getEmail3(), equalTo(contactInfoFromEditForm.getEmail3()));
  }

  public String cleaned(String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
  public String cleaned1 (String address) {
    return  address.replaceAll(" +", " ").replaceAll(" *\n *", "\n").trim();
  }

}
