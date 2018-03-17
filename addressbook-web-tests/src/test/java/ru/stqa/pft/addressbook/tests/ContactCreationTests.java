package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/39005.jpg");
    list.add(new Object[]{new ContactData().withFirstname("TestFirstName1").withLastname("TestLastName1").withAddress("TestAddress1")
    .withEmail("1Test@mail.ru").withMobile("89040000011").withPhoto(photo).withGroup("test1")});
    list.add(new Object[]{new ContactData().withFirstname("TestFirstName2").withLastname("TestLastName2").withAddress("TestAddress2")
            .withEmail("2Test@mail.ru").withMobile("89040000011").withPhoto(photo).withGroup("test2")});
    list.add(new Object[]{new ContactData().withFirstname("6TestFirstName3").withLastname("TestLastName3").withAddress("TestAddress3")
            .withEmail("3Test@mail.ru").withMobile("89040000011").withPhoto(photo).withGroup("test3")});
    return list.iterator();
  }


  @Test(dataProvider = "validContacts")
  public void TestContactCreationTests(ContactData contact) {
    app.goTo();
    Contacts before = app.contact().all();
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
