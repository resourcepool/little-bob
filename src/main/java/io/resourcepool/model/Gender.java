package io.resourcepool.model;

/**
 * The Gender of a person.
 * Only male and female supported
 *
 * @author Loïc Ortola
 */
public enum Gender {
  MALE, FEMALE;

  public static Gender parse(String s) {
    if ("m".equalsIgnoreCase(s)) {
      return MALE;
    }
    if ("f".equalsIgnoreCase(s)) {
      return FEMALE;
    }
    return null;
  }
}
