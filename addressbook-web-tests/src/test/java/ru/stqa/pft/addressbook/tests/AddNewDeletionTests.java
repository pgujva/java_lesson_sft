package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class AddNewDeletionTests extends TestBase{
  @Test
  public void testAddNewDeletion(){
    app.getContactHelper().initAddNewDeletion();
  }
}
