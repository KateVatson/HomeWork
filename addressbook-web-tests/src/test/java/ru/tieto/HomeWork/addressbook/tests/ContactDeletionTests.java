package ru.tieto.HomeWork.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.ContactData;
import ru.tieto.HomeWork.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMiddlename("Testovich").withNickname("Testname").withAddress("Saint-Petersburg").withMobilePhone("+7(999)123-87-09").withEmail("test@hh.ok"));
    }
  }

  @Test
  public void testContactDeletion (){
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().GoToHome();
    Contacts after = app.contact().all();
    Assert.assertEquals(after.size(), before.size()-1);

    assertThat(after, equalTo(before.without(deletedContact)));
  }


}
