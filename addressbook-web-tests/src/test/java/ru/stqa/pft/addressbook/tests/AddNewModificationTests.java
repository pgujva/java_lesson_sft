package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class AddNewModificationTests extends TestBase {

  @BeforeMethod void ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().list().size()== 0) {
      app.contact().create(new ContactData("TestFirstName", "TestLastName", "TestAddress", "Test@mail.ru", "89040000011", "test1"), true);
    }
  }

  @Test
  public void testAddNewModification() {
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(),"TestFirstName1", "TestLastName1", "TestAddress1", "Test111@mail.ru", "89040000011", null);
    app.contact().modify(index, contact);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());
    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before,after);
  }


  }

