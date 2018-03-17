package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line !=null) {
      String[] split=line.split(";");
      list.add(new Object[] {new ContactData().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])});
      line = reader.readLine();
    }
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
