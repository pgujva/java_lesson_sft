package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {


  @Test
  public void TestContactCreationTests() {
    app.goTo();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/39005.jpg");
    ContactData contact = new ContactData()
            .withFirstname("TestFirstName5").withLastname("TestLastName").withAddress("TestAddress").withEmail("Test@mail.ru")
            .withMobile("89040000011").withPhoto(photo).withGroup("test1");
    app.contact().create(contact,true);
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test
  public void testCurrentDir (){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsoluteFile());
    File photo = new File("src/test/resources/39005.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
