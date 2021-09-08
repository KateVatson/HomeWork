package ru.tieto.HomeWork.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion (){
    if (! app.getContactHelper().isThereAContact()) {
     app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "Testovich", "Testname", "Test case", "Tieto", "Saint-Petersburg", "+7(999)123-87-09","test@hh.ok"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().isAlertAccept();
    app.goTo().GoToHome();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size()-1);

    before.remove(before.size() - 1);
    Assert.assertEquals(before,after);
  }

}
