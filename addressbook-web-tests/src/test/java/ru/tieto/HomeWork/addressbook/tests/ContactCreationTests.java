package ru.tieto.HomeWork.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreationTests() {
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData("Ivan", "Ivanov", "Testovich", "Testname", "Test case", "Tieto", "Saint-Petersburg", "+7(999)123-87-09","test@hh.ok" );
    app.getContactHelper().createContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);


    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
