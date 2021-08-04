package ru.tieto.HomeWork.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreationTests() {
    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("Shiny", "Pokemon", "Legendary"));
    submitGroupCreation();
    returnToGroupPage();
  }
}