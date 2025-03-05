package io.resourcepool.model;

import java.util.Objects;

/**
 * The full name. Contains given name, surname, nickname, gender, age and email.
 *
 * @author Loïc Ortola
 */
public class UniquePerson {

  public final String firstName;
  public final String lastName;
  public final String nickname;
  public final int age;
  public final Gender gender;
  public final String email;

  public UniquePerson(String firstName, String lastName, String nickname, int age, Gender gender, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickname = nickname;
    this.age = age;
    this.gender = gender;
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UniquePerson that = (UniquePerson) o;
    return Objects.equals(nickname, that.nickname);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(nickname);
  }

  @Override
  public String toString() {
    return "UniquePerson{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", nickname='" + nickname + '\'' +
            ", age=" + age +
            ", gender=" + gender +
            ", email='" + email + '\'' +
            '}';
  }

  public Person toPerson() {
    return new Person(firstName, lastName, nickname, age, gender, email);
  }
}
