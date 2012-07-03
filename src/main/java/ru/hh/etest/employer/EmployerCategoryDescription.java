package ru.hh.etest.employer;

import com.headhunter.webapp.fixture.EmployerCategory;
import java.util.HashMap;

public class EmployerCategoryDescription {
  private static final HashMap<EmployerCategory, String> map = new HashMap<EmployerCategory, String>();

  static {
    map.put(EmployerCategory.COMPANY, "прямой работодатель");
    map.put(EmployerCategory.AGENCY, "Кадровое агентство");
    map.put(EmployerCategory.PROJECT_DIRECTOR, "Руководитель проекта");
    map.put(EmployerCategory.PRIVATE_RECRUITER, "Частный рекрутер");
    map.put(EmployerCategory.ANONYMOUS, "Анонимный работодатель");
  }

  public static String getEmployerCategoryDescription(EmployerCategory category) {
    return map.get(category);
  }
}
