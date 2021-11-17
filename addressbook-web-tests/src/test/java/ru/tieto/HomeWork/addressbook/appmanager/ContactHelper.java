package ru.tieto.HomeWork.addressbook.appmanager;

import org.hibernate.sql.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import ru.tieto.HomeWork.addressbook.model.ContactData;
import ru.tieto.HomeWork.addressbook.model.Contacts;
import ru.tieto.HomeWork.addressbook.model.GroupData;

import java.util.List;


public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        wd.findElement(By.name("new_group"))
                .findElement(By.xpath("//option[@value=" + contactData.getGroups().iterator().next().getId() + "]")).click();
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public int count() {
    return wd.findElements(By.name("selected[]")).size();
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
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelected();
    isAlertAccept();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    editContactById(contact.getId());
    fillContactForm(contact, false);
    submitModification();
    contactCache = null;
  }
  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name='entry']"));
    for (WebElement element : elements) {
      List<WebElement> cells = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).
              withLastname(lastname).withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return new Contacts(contactCache);
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

  public void addContactToGroup(ContactData contact, GroupData group) {
    selectContactById(contact.getId());
    selectGroupByIdForAdd(group);
    addToGroup();
    goToGroup();

  }

  private void selectGroupByIdForAdd(GroupData group) {
    wd.findElement(By.xpath("//select[@name='to_group']//option[@value=" + group.getId() + "]")).click();
  }

  private void goToGroup() {
    click(By.partialLinkText("group page"));
  }

  private void addToGroup() {
    click(By.name("add"));
  }

  public void selectGroupForRemove(GroupData group) {
    wd.findElement(By.name("group")).findElement(By.xpath("//option[@value=" + group.getId() + "]")).click();
  }

  public void removeContactFromGroup(ContactData contact) {
    selectContactById(contact.getId());
    removeContact();
    goToGroup();
    contactCache = null;
  }

  private void removeContact() {
    click(By.name("remove"));
  }
}





