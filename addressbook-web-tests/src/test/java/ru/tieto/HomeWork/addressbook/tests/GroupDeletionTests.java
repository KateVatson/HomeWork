package ru.tieto.HomeWork.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase{
  @Test
  public void testGroupDeletion() {
    app.getNavigationHelper().goToGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
    app.getGroupHelper().createGroup(new GroupData("Shiny", "Pokemon", "Legendary"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroup();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }
}
