package io.resourcepool.model;

/**
 * The full name. Contains given name, surname, nickname, and gender.
 *
 * @author Loïc Ortola
 */
public class FullName {

  public final String givenName;
  public final String surname;
  public final String nickname;
  public final Gender gender;

  public FullName(String givenName, String nickname, String surname, Gender gender) {
    this.givenName = givenName;
    this.nickname = nickname;
    this.surname = surname;
    this.gender = gender;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (o instanceof String) return ((String) o).equalsIgnoreCase(givenName + " " + surname);
    if (getClass() != o.getClass()) return false;
    FullName fullName = (FullName) o;
    if (givenName != null ? !givenName.equals(fullName.givenName) : fullName.givenName != null) return false;
    if (surname != null ? !surname.equals(fullName.surname) : fullName.surname != null) return false;
    if (nickname != null ? !nickname.equals(fullName.nickname) : fullName.nickname != null) return false;
    return gender == fullName.gender;
  }

  @Override
  public int hashCode() {
    int result = givenName != null ? givenName.hashCode() : 0;
    result = 31 * result + (surname != null ? surname.hashCode() : 0);
    result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
    result = 31 * result + (gender != null ? gender.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "FullName{" +
      "givenName='" + givenName + '\'' +
      ", surname='" + surname + '\'' +
      ", nickname='" + nickname + '\'' +
      ", gender=" + gender +
      '}';
  }

  @Override
  protected FullName clone() throws CloneNotSupportedException {
    return new FullName(givenName, nickname, surname, gender);
  }
}
