package ru.tieto.HomeWork.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelperBase extends HelperBase {

  public NavigationHelperBase(ChromeDriver wd) {
    super(wd);
  }


  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void goToGroupPage() {
    click(By.linkText("groups"));
  }
}
