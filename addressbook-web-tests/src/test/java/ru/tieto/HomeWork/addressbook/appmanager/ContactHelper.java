package ru.tieto.HomeWork.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.tieto.HomeWork.addressbook.model.ContactData;
import ru.tieto.HomeWork.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
  }

  public void initCreation() {
    click(By.linkText("add new"));
  }


  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }


  public void deleteSelected() {
    click(By.xpath("//input[@value='Delete']"));
  }


  private void editContactById(int id) {
    wd.findElement(By.xpath("//td[@class='center']//a[@href='edit.php?id=" + id + "']")).click();
  }


  public void submitModification() {
    click(By.name("update"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }


  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//tr[@name ='entry']//input[1]"));
  }

  public void create(ContactData contact) {
    initCreation();
    fillContactForm(contact);
    submitContactCreation();
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelected();
    isAlertAccept();
  }

  public void modify(ContactData contact) {
    editContactById(contact.getId());
    fillContactForm(contact);
    submitModification();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).
              withLastname(lastname).withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return contacts;
  }

  private void initContactModificationById(int id) {
    wd.findElement (By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getText();


    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
  }
}

