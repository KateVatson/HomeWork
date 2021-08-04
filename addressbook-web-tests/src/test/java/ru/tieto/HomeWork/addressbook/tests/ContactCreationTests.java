package ru.tieto.HomeWork.addressbook.tests;

import org.testng.annotations.Test;
import ru.tieto.HomeWork.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreationTests() {
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("Test", "Testovich", "Testov", "TestCase", "Test title", "Tieto", "Saint-Petersburg", "+7(999)543-12-09", "test@mail.ru"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().returnToHomePage();
  }

}
