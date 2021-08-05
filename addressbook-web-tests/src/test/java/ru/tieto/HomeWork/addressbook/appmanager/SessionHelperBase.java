package ru.tieto.HomeWork.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class SessionHelperBase extends HelperBase {

  public SessionHelperBase(ChromeDriver wd) {
    super(wd);
  }
  public void login(String username, String password) {
    type(By.name("user"),username);
    type(By.name("pass"),password);
    click(By.xpath("//input[@value='Login']"));
  }
}
