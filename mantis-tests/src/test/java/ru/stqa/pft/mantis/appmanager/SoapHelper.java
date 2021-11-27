package ru.stqa.pft.mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
  private final String login;
  private final String password;
  private  ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    login = app.getProperty("web.adminLogin");
    password = app.getProperty("web.adminPassword");
    this.app = app;
  }

  public Set<Project> getProjects() throws ServiceException, RemoteException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible(login, password);
    return Arrays.asList(projects).stream()
            .map((p)-> new Project().withId(p.getId().intValue()).withName(p.getName()))
            .collect(Collectors.toSet());
  }

  public MantisConnectPortType getMantisConnect() throws ServiceException {
    try {
      return new MantisConnectLocator().getMantisConnectPort(new URL("http://localhost/mantisbt-2.25.2/api/soap/mantisconnect.php"));
    }catch (MalformedURLException e) {
      e.printStackTrace();
    }
    return null;
  }

  public Issue addIssue(Issue issue) throws RemoteException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    String [] categories = mc.mc_project_get_categories(login, password, BigInteger.valueOf(issue.getProject().getId()));
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary());
    issueData.setDescription(issue.getDescription());
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    issueData.setCategory(categories[0]);
    BigInteger issueId = mc.mc_issue_add(login, password, issueData);
    IssueData createdIssueData = mc.mc_issue_get(login, password, issueId);
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getProject().getId().intValue())
                    .withName(createdIssueData.getProject().getName()));
  }
}
