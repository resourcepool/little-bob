package io.resourcepool.model;

import java.util.Objects;

/**
 * The full name. Contains given name, surname, nickname, gender, age and email.
 *
 * @author Loïc Ortola
 */
public class Person {

  public final String firstName;
  public final String lastName;
  public final String nickname;
  public final int age;
  public final Gender gender;
  public final String email;

  public Person(String firstName, String lastName, String nickname, int age, Gender gender, String email) {
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
    Person person = (Person) o;
    return age == person.age && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(nickname, person.nickname) && gender == person.gender && Objects.equals(email, person.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, nickname, age, gender, email);
  }

  @Override
  public String toString() {
    return "Person{" +
            "firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", nickname='" + nickname + '\'' +
            ", age=" + age +
            ", gender=" + gender +
            ", email='" + email + '\'' +
            '}';
  }

  @Override
  protected Person clone() throws CloneNotSupportedException {
    return new Person(firstName, lastName, nickname, age, gender, email);
  }
}
