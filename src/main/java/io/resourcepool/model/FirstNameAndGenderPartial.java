package io.resourcepool.model;

import java.util.Objects;

/**
 * Represents a given name and its related fields (nickname and gender).
 *
 * @author Loïc Ortola
 */
public class FirstNameAndGenderPartial implements Cloneable {

  public final String firstName;
  public final Gender gender;
  public final Language language;

  public FirstNameAndGenderPartial(String firstName, Gender gender, Language language) {
    this.firstName = firstName;
    this.gender = gender;
    this.language = language;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    FirstNameAndGenderPartial that = (FirstNameAndGenderPartial) o;
    return Objects.equals(firstName, that.firstName) && gender == that.gender && language == that.language;
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, gender, language);
  }

  @Override
  public String toString() {
    return "FirstNameAndGenderPartial{" +
            "firstName='" + firstName + '\'' +
            ", gender=" + gender +
            ", language=" + language +
            '}';
  }

  public Person toPerson(String lastName, String nickname, int age, String email) {
    return new Person(firstName, lastName, nickname, age, gender, email);
  }

  public UniquePerson toUniquePerson(String lastName, String nickname, int age, String email) {
    return new UniquePerson(firstName, lastName, nickname, age, gender, email);
  }

  @Override
  public FirstNameAndGenderPartial clone() {
    return new FirstNameAndGenderPartial(firstName, gender, language);
  }
}
