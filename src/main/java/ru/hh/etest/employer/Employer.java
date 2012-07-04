package ru.hh.etest.employer;

import org.apache.commons.lang.StringUtils;

import ru.hh.etest.common.CommonUtils;

import com.headhunter.webapp.fixture.EmployerCategory;
import com.headhunter.webapp.fixture.UserData;

public class Employer {
  private String employerId;
  private String organizationForm;
  private String companyName;
  private EmployerCategory type;
  private String region;
  private UserData mcp = new UserData();
  private String site;
  private String description;
  private String mcpPosition;
  private String[] phone;
  private String howKnowAboutUs;
  private String inn;

  public Employer() {
    this.companyName = CommonUtils.getUnique();
    this.type = EmployerCategory.COMPANY;
    this.region = "Москва";
    this.howKnowAboutUs = "Из рекламы на улицах города";
    this.description = StringUtils.leftPad(companyName, 300, " Работодатель для тестирования");
    this.mcpPosition = "директор";
    this.phone = new String[] { "7", CommonUtils.getNumber(3), CommonUtils.getNumber(7) };
  }

  public String getEmployerCategoryDescription() {
    return EmployerCategoryDescription.getEmployerCategoryDescription(type);
  }

  public String getEmployerId() {
    return employerId;
  }

  public void setEmployerId(String employerId) {
    this.employerId = employerId;
  }

  public String getOrganizationForm() {
    return organizationForm;
  }

  public void setOrganizationForm(String organizationForm) {
    this.organizationForm = organizationForm;
  }

  public EmployerCategory getType() {
    return type;
  }

  public void setType(EmployerCategory type) {
    this.type = type;
  }

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public String getHowKnowAboutUs() {
    return howKnowAboutUs;
  }

  public void setHowKnowAboutUs(String howKnowAboutUs) {
    this.howKnowAboutUs = howKnowAboutUs;
  }

  public UserData getMcp() {
    return mcp;
  }

  public void setMcp(UserData mcp) {
    this.mcp = mcp;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getMcpPosition() {
    return mcpPosition;
  }

  public void setMcpPosition(String mcpPosition) {
    this.mcpPosition = mcpPosition;
  }

  public String[] getPhone() {
    return phone;
  }

  public void setPhone(String[] phone) {
    this.phone = phone;
  }

  public String getInn() {
    return inn;
  }

  public void setInn(String inn) {
    this.inn = inn;
  }
}
