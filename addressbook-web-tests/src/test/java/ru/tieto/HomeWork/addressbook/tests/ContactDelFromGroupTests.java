package ru.tieto.HomeWork.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.ContactData;
import ru.tieto.HomeWork.addressbook.model.Contacts;
import ru.tieto.HomeWork.addressbook.model.GroupData;
import ru.tieto.HomeWork.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDelFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Bulbasaur").withHeader("Charmander").withFooter("Shiny"));
    }

    if (app.db().contacts().size() == 0) {
      GroupData group = app.db().groups().iterator().next();
      app.goTo().goToHome();
      app.contact().create(new ContactData().withFirstname("One").withLastname("Piece").withAddress("Saint-Petersburg").inGroup(group));
    }
  }

  @Test
  public void testContactDeletionFromGroup() {
    app.goTo().goToHome();
    ContactData contact = getContact();
    GroupData groupForRemove = getGroup(contact);
    Groups before = contact.getGroups();

    app.contact().selectGroupForRemove(groupForRemove);
    app.contact().removeContactFromGroup(contact);

    ContactData contactsAfter = selectContactById(contact);
    Groups after = contactsAfter.getGroups();
    assertThat(after, equalTo(before.without(groupForRemove)));

  }

  private ContactData selectContactById(ContactData contact) {
    Contacts contactsById = app.db().contacts();
    return contactsById.iterator().next().withId(contact.getId());
  }

  private GroupData getGroup(ContactData removeContact) {
    ContactData contact = selectContactById(removeContact);
    Groups removedContact = contact.getGroups();
    return removedContact.iterator().next();
  }

  private ContactData getContact() {
    Contacts contacts = app.db().contacts();
    for (ContactData contact: contacts) {
      if (contact.getGroups().size() > 0) {
        return contact;
      }
    }
    ContactData addContact = app.db().contacts().iterator().next();
    app.contact().addContactToGroup(addContact, app.db().groups().iterator().next());
    return addContact;
  }


}

