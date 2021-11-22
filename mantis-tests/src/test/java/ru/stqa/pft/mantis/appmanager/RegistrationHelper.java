package ru.stqa.pft.mantis.appmanager;


import org.openqa.selenium.By;

import static org.openqa.selenium.By.cssSelector;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), username);
    type(By.name("email"), email);
    click(cssSelector("input[type='submit']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(cssSelector("button[type='submit']"));
  }

  public void login(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    click(By.cssSelector("input[type='submit']"));
    type(By.name("password"), password);
    click(By.cssSelector("input[type='submit']"));
  }

  public void resetUserPassword(int id) {
    click(By.cssSelector("button#menu-toggler"));
    click(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i"));
    click(By.cssSelector("ul.nav-tabs li:nth-child(2)"));
    click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']", id)));
    click(By.xpath("//*[@id='manage-user-reset-form']/fieldset/span/input"));
  }

  public void finishResetUserPassword(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.cssSelector("#password"), password);
    type(By.cssSelector("#password-confirm"), password);
    click(By.cssSelector("button[type='submit']"));
  }
}
