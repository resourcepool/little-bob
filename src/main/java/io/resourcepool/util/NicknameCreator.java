package io.resourcepool.util;

import io.resourcepool.model.Language;

import java.text.Normalizer;

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
      return normalizeName(name) + String.valueOf(n);
    }
    switch (language) {
      case ENGLISH:
        return NICK_PREFIX_EN[n - 100] + normalizeName(name);
      case FRENCH:
        return NICK_PREFIX_FR[n - 100] + normalizeName(name);
    }
    // Should not happen
    return normalizeName(name);
  }

  public static String fromFullName(String firstName, String lastName) {
    int n = (int) (Math.random() * 100);
    int s = (int) (Math.random() * 2);
    if (n < 40) {
      return normalizeName(firstName.substring(0, 1)) + SEPARATORS[s] + normalizeName(lastName);
    }
    else if (n < 80) {
      return normalizeName(firstName) + SEPARATORS[s] + normalizeName(lastName.replaceAll("'", "").replaceAll("\\s", "")) + String.valueOf(n);
    }
    return normalizeName(firstName) + SEPARATORS[s] + normalizeName(lastName);
  }

  /**
   * Normalize a name by removing accents, diacritics, spaces, and converting to a simple ASCII format.
   *
   * @param name the name to normalize.
   * @return the normalized name.
   */
  public static String normalizeName(String name) {
    if (name == null || name.isEmpty()) {
      return name;
    }

    // Remove accents and diacritics
    String normalized = Normalizer.normalize(name, Normalizer.Form.NFD);
    normalized = normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

    // Remove spaces and any non-alphanumeric characters
    normalized = normalized.replaceAll("[\\s\\p{Punct}]", "");

    // Convert to lower case (optional, depending on requirements)
    return normalized.toLowerCase();
  }
}
