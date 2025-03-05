package io.resourcepool.util;

import io.resourcepool.model.Language;

/**
 * Nickname Creator.
 * Useful when names don't have a known nickname
 *
 * @author Loïc Ortola
 */
public class NicknameCreator {
  // 12 nickname prefixes
  private static final String[] NICK_PREFIX_EN = {"Cutie", "Dark", "Master", "Light", "Fire", "TheOnly", "Stupid", "Clever", "Ultra", "Ultimate", "Sober", "TheReal"};
  private static final String[] NICK_PREFIX_FR = {"Super", "Maitre", "Gentil", "Tarte", "LeVrai", "Dark", "LeDernier", "Master", "Badass", "LePetit", "Carotte", "Spécial"};
  private static final String[] SEPARATORS = {".", ""};

  public static String from(String firstName, String lastName, Language language) {
    int n = (int) (Math.random() * 100);
    if (n < 50) {
      return fromFirstName(firstName, language);
    }
    return fromFullName(firstName, lastName);
  }


  public static String fromFirstName(String name, Language language) {
    int n = (int) (Math.random() * 111);
    if (n < 100) {
      return name + String.valueOf(n);
    }
    switch (language) {
      case ENGLISH:
        return NICK_PREFIX_EN[n - 100] + name;
      case FRENCH:
        return NICK_PREFIX_FR[n - 100] + name;
    }
    // Should not happen
    return name;
  }

  public static String fromFullName(String firstName, String lastName) {
    int n = (int) (Math.random() * 100);
    int s = (int) (Math.random() * 2);
    if (n < 40) {
      return firstName.substring(0, 1) + SEPARATORS[s] + lastName;
    }
    else if (n < 80) {
      return firstName + SEPARATORS[s] + lastName.replaceAll("'", "").replaceAll("\\s", "") + String.valueOf(n);
    }
    return firstName + SEPARATORS[s] + lastName;
  }
}
