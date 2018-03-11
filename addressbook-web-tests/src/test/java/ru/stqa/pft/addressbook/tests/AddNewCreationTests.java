package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;




import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class AddNewCreationTests extends TestBase {


  @Test
  public void TestAddNewCreationTests() {
    app.goTo();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("TestFirstName5").withLastname("TestLastName").withAddress("TestAddress").withEmail("Test@mail.ru").withMobile("89040000011").withGroup("test1");
    app.contact().create(contact,true);
    assertThat(app.contact().getContactCount(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
