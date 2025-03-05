package io.resourcepool.generator;

import io.resourcepool.model.Person;
import io.resourcepool.model.FirstNameAndGenderPartial;

import java.util.List;

/**
 * Name Generator.
 * Generates random people (first name, last name, nickname, gender, age, email)
 * @author Loïc Ortola
 */
public interface PersonGenerator {

  List<Person> nextPersons(Query query);
  List<Person> nextPersons(int count);
  List<FirstNameAndGenderPartial> nextFirstNames(int count);
  List<String> nextLastNames(int count);
  List<String> nextNicknames(int count);
  Person nextPerson();
  FirstNameAndGenderPartial nextFirstName();
  String nextLastName();
  String nextNickname();

}
