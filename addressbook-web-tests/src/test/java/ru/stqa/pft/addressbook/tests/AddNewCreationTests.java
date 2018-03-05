package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class AddNewCreationTests extends TestBase {


  @Test
  public void TestAddNewCreationTests() {
    app.getNavigationHelper().goToHomepage();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("TestFirstName5", "TestLastName", "TestAddress", "Test@mail.ru", "89040000011", "test1");
    app.getContactHelper().initAddNewContactCreation();
    app.getContactHelper().fillAddContactForm(contact,true);
    app.getContactHelper().submitAddContact();
    app.getContactHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
