package ru.tieto.HomeWork.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.ContactData;
import ru.tieto.HomeWork.addressbook.model.Contacts;
import ru.tieto.HomeWork.addressbook.model.GroupData;
import ru.tieto.HomeWork.addressbook.model.Groups;

import java.util.Collection;
import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test").withHeader("Omega").withFooter("Alfa"));
    }

    if (app.db().contacts().size() == 0) {
      Groups dbGroups = app.db().groups();
      GroupData group = dbGroups.iterator().next();
      app.goTo().goToHome();
      app.contact().create(new ContactData().withFirstname("Pika").withLastname("Veronika").withAddress("Moscow").inGroup(group));
    }
  }


  @Test
  public void testAddContactToGroup() throws Exception{
    app.goTo().goToHome();
    ContactData contact = getContactData();
    Groups before = contact.getGroups();
    GroupData groupForAdd = getGroupData(contact);
    app.contact().addContactToGroup(contact, groupForAdd);
    Groups after = app.db().getContactsFromDbById(contact.getId()).getGroups();
    assertThat(after, equalTo(before.withAdded(groupForAdd)));
  }

  public ContactData getContactData() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    for (ContactData contact : contacts) {
      if (contact.getGroups().size() < groups.size()) {
        return contact;
      }
    }
    app.goTo().groupPage();
    app.group().create(new GroupData().withName("Pokemon"));
    app.goTo().goToHome();
    return  contacts.iterator().next();
  }

  public GroupData getGroupData(ContactData contact) {
    Groups groups = app.db().groups();
    Collection<GroupData> allGroups = new HashSet<>(groups);
    allGroups.removeAll(contact.getGroups());
    return allGroups.iterator().next();
  }
}

