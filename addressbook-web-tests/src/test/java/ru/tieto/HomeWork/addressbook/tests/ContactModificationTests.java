package ru.tieto.HomeWork.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.ContactData;
import ru.tieto.HomeWork.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMiddlename("Testovich")
              .withNickname("Testname").withAddress("Saint-Petersburg").withMobilePhone("+7(999)123-87-09").withEmail("test@hh.ok").withEmail2("meow@cat.nya"));
    }
  }

  @Test
  public void testContactModification () {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
            withId(modifiedContact.getId()).withFirstname("Ivan").withLastname("Ivanov").
            withNickname("Testname").withAddress("Saint-Petersburg").withMobilePhone("+7(999)123-87-09").withEmail("test@hh.ok").withEmail2("meow@cat.naa");
    app.contact().modify(contact);
    app.goTo().returnToHomePage();
    assertThat(app.contact().count(), equalTo(before.size()));

    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}

