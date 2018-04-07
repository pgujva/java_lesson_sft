package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class ContactDeleteFromGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      Groups groups = app.db().groups();
      GroupData groupData;

      if (app.db().groups().size() == 0) {
        app.goTo().groupPage();
        groupData = new GroupData().withName("test1");
        app.group().create(groupData);
      } else {
        groupData = groups.iterator().next();
      }
      app.goTo().homePage();
      app.contact().create(new ContactData()
              .withFirstname("TestFirstName").withLastname("TestLastName").withAddress("TestAddress")
              .withEmail("Test@mail.ru").withMobile("89040000011")
              .inGroup(groupData), true);
    }
  }

  @Test
  public void TestContactDeleteFromGroup () {
    Contacts before = app.db().contacts();
    ContactData contactDel = before.iterator().next();
    Groups list = contactDel.getGroups();
    GroupData group = app.db().groups().iterator().next();

    if(contactDel.getGroups().isEmpty()) {
      app.contact().add(contactDel);
      app.goTo().homePage();
      list=list.withAdded(group);
    }
    app.contact().delcontact(contactDel.getId());
    Groups updateGroups = contactDel.getGroups();

    assertThat(updateGroups, equalTo(list.without(group)));
    verifyContactListInUI();
  }
}
