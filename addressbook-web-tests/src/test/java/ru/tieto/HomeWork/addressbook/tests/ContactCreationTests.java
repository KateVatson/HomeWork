package ru.tieto.HomeWork.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.ContactData;
import ru.tieto.HomeWork.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreationTests() {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMiddlename("Testovich").withNickname("Testname").withAddress("Saint-Petersburg").withMobile("+7(999)123-87-09").withEmail("test@hh.ok");
    app.contact().create(contact);
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);


    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

}
