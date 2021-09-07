package ru.tieto.HomeWork.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification () {
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Ivan", "Ivanov", "Testovich", "Testname", "Test case", "Tieto", "Saint-Petersburg", "+7(999)123-87-09","test@hh.ok" ));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().editContact(0);
    ContactData contact = new ContactData(before.get(0).getId(), "Ivan1", "Ivanovich", "Ivanov4", "TestCase", "Test title", "Tieto", "Saint-Petersburg", "+7(900)543-12-09", "drugoytest@mail.ru");
    app.getContactHelper().fillContactForm(contact);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(0);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1,c2) -> Integer.compare(c1.getId(),c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}

