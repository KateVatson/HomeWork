package ru.tieto.HomeWork.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreationTests() {
    initContactCreation();
    fillContactForm(new ContactData("Test", "Testovich", "Testov", "TestCase", "Test title", "Tieto", "Saint-Petersburg", "+7(999)543-12-09", "test@mail.ru"));
    submitContactCreation();
    returnToHomePage();
  }

}
