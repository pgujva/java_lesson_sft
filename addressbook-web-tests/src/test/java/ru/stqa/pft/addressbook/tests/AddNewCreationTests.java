package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddNewCreationTests extends TestBase {


  @Test
  public void TestAddNewCreationTests() {
    app.getNavigationHelper().goToHomepage();
    app.getContactHelper().initAddNewContactCreation();
    app.getContactHelper().fillAddContactForm(new ContactData("TestFirstName", "TestLastName", "TestAddress", "Test@mail.ru", "89040000011", "test1"),true);
    app.getContactHelper().submitAddContact();
    app.getContactHelper().returnToHomePage();
  }


}
