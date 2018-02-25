package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewModificationTests extends TestBase {

  @Test
  public void testAddNewModification (){
   app.getContactHelper().initAddNewModification();
   app.getContactHelper().fillAddContactForm(new ContactData("TestFirstName1", "TestLastName1", "TestAddress1", "Test111@mail.ru", "89040000011", null),false);
   app.getContactHelper().submitAddNewModification();
   app.getContactHelper().returnToHomePage();
  }
}
