package ru.tieto.HomeWork.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.ContactData;
import ru.tieto.HomeWork.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMiddlename("Testovich").withNickname("Testname").withAddress("Saint-Petersburg").withMobile("+7(999)123-87-09").withEmail("test@hh.ok"));
    }
  }

  @Test
  public void testContactModification () {
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).withFirstname("Ivan").withLastname("Ivanov").withMiddlename("Testovich").withNickname("Testname").withAddress("Saint-Petersburg").withMobile("+7(999)123-87-09").withEmail("test@hh.ok");
    app.contact().modify(contact);
    app.goTo().returnToHomePage();
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(),before.size());

    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }


}

