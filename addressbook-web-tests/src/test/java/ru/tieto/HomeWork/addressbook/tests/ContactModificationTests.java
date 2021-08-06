package ru.tieto.HomeWork.addressbook.tests;


import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification () {
    app.getContactHelper().editContact();
    app.getContactHelper().fillContactForm(new ContactData("Ivan", "Ivanovich", "Ivanov", "TestCase", "Test title", "Tieto", "Saint-Petersburg", "+7(900)543-12-09", "drugoytest@mail.ru"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
  }



}

