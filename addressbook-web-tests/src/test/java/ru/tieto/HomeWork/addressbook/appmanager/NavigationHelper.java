package ru.tieto.HomeWork.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }


  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void groupPage() {
    click(By.linkText("groups"));
  }

  public void goToHome() {
    click(By.linkText("home"));
  }
}
