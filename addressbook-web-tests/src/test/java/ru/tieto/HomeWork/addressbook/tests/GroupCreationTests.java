package ru.tieto.HomeWork.addressbook.tests;

import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreationTests() {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", null, "shadow"));
  }
}