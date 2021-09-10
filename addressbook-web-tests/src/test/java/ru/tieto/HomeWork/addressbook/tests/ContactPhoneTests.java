package ru.tieto.HomeWork.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactPhoneTests extends  TestBase{
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov")
              .withAddress("Saint-Petersburg").withMobilePhone("+7(999)123-87-09").withEmail("test@hh.ok").withEmail2("test@case.com")
              .withHomePhone("554").withHomePhone("22 22"));
    }
  }


  @Test
  public void testContactPhones() {
    app.goTo().GoToHome();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);


    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoFromEditForm)));

  }

  private String mergeAddress(ContactData address) {
    return Arrays.asList(address.getAddress())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
      }



  private String mergeEmails(ContactData email) {
    return Arrays.asList(email.getEmail(), email.getEmail2(), email.getEmail3())
            .stream().filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergePhones(ContactData phone) {
    return Arrays.asList(phone.getHomePhone(), phone.getMobilePhone(), phone.getWorkPhone())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleaned).
            collect(Collectors.joining("\n"));

  }


  public static String cleaned(String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }

}
