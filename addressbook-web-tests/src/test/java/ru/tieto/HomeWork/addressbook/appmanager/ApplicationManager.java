package ru.tieto.HomeWork.addressbook.appmanager;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  ChromeDriver wd;
  private SessionHelperBase sessionHelper;
  private ContactHelperBase contactHelper;
  private GroupHelperBase groupHelper;
  private NavigationHelperBase navigationHelper;

  public void init() {
    ChromeOptions handlingSSL = new ChromeOptions();
    handlingSSL.setAcceptInsecureCerts(true);
    wd = new ChromeDriver(handlingSSL);
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    navigationHelper = new NavigationHelperBase(wd);
    groupHelper = new GroupHelperBase(wd);
    contactHelper = new ContactHelperBase(wd);
    sessionHelper = new SessionHelperBase(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public NavigationHelperBase getNavigationHelper() {
    return navigationHelper;
  }

  public GroupHelperBase getGroupHelper() {
    return groupHelper;
  }

  public ContactHelperBase getContactHelper() {
    return contactHelper;
  }
}
