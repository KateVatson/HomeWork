package ru.tieto.HomeWork.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationHelper {
private ChromeDriver wd;

  public NavigationHelper(ChromeDriver wd) {
    this.wd = wd;
  }


  public void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  public void goToGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }
}
