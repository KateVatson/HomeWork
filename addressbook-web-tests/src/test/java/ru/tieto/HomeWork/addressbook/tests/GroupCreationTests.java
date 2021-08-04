package ru.tieto.HomeWork.addressbook.tests;

import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreationTests() {
    app.goToGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("Shiny", "Pokemon", "Legendary"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }
}