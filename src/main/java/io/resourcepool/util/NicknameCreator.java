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
}
