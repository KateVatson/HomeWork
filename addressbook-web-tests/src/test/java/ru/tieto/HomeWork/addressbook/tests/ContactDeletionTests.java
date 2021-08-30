package ru.tieto.HomeWork.addressbook.tests;

import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.ContactData;
import ru.tieto.HomeWork.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion (){
    if (! app.getContactHelper().isThereAContact()) {
     app.getContactHelper().createContact(new ContactData("Test", "Testovich2", "Testov", "TestCase", "Test title", "Tieto", "Saint-Petersburg", "+7(999)543-12-09", "test@mail.ru"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().isAlertAccept();
    app.getNavigationHelper().GoToHome();
  }

}
