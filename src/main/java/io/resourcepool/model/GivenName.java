package io.resourcepool.model;

/**
 * Represents a given name and its related fields (nickname and gender).
 *
 * @author Loïc Ortola
 */
public class GivenName implements Cloneable {

  public final String givenName;
  public final String nickname;
  public final Gender gender;

  public GivenName(String givenName, String nickname, Gender gender) {
    this.givenName = givenName;
    this.nickname = nickname;
    this.gender = gender;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (o instanceof String) return ((String) o).equalsIgnoreCase(givenName);
    if (getClass() != o.getClass()) return false;
    GivenName givenName1 = (GivenName) o;

    if (givenName != null ? !givenName.equals(givenName1.givenName) : givenName1.givenName != null) return false;
    if (nickname != null ? !nickname.equals(givenName1.nickname) : givenName1.nickname != null) return false;
    return gender == givenName1.gender;
  }

  @Override
  public int hashCode() {
    return givenName != null ? givenName.hashCode() : 0;
  }

  @Override
  public String toString() {
    return "GivenName{" +
      "givenName='" + givenName + '\'' +
      ", nickname='" + nickname + '\'' +
      ", gender=" + gender +
      '}';
  }

  public FullName toFullName(String surname) {
    return new FullName(givenName, nickname, surname, gender);
  }

  @Override
  public GivenName clone() {
    return new GivenName(givenName, nickname, gender);
  }
}
