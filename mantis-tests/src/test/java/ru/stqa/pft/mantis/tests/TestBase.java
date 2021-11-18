package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;

public class TestBase {

  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));


  @BeforeMethod
  public void setUp() throws Exception {
     app.init();
     app.ftp().upload(new File("C:\\Users\\xxkartoe\\Documents\\GitHub\\HomeWork\\mantis-tests\\src\\test\\resources\\config_inc.php"),"config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    app.ftp().restore("config_inc.php.bak","config_inc.php" );
    app.stop();
  }
}

