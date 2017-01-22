package io.resourcepool.model;

/**
 * Supported languages for little-bob.
 *
 * @author Loïc Ortola
 */
public enum Language {
  ENGLISH("en"), FRENCH("fr");

  private final String localeCode;

  Language(String localeCode) {
    this.localeCode = localeCode;
  }

  public String getLocaleCode() {
    return localeCode;
  }
  
  public static Language random() {
    return values()[(int) Math.random() * Language.values().length];
  }
}
