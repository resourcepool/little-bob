package io.resourcepool.generator.impl;

import io.resourcepool.dictionary.impl.FirstNameDictionary;
import io.resourcepool.dictionary.impl.LastNameDictionary;
import io.resourcepool.dictionary.loader.DomainLoader;
import io.resourcepool.generator.PersonGenerator;
import io.resourcepool.generator.Query;
import io.resourcepool.model.Person;
import io.resourcepool.model.FirstNameAndGenderPartial;
import io.resourcepool.model.Language;
import io.resourcepool.model.UniquePerson;
import io.resourcepool.util.NicknameCreator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Simple Name generator using the dictionaries provided in the classpath resources.
 *
 * @author Loïc Ortola
 */
public class SimplePersonGenerator implements PersonGenerator {

  private final FirstNameDictionary firstNameDictionary;
  private final LastNameDictionary lastNamesDictionary;
  private final List<String> domains;

  public SimplePersonGenerator() {
    lastNamesDictionary = new LastNameDictionary();
    firstNameDictionary = new FirstNameDictionary();
    domains = DomainLoader.loadDomains();
  }

  @Override
  public List<Person> nextPersons(Query query) {
    int maxItemsGivenNames = firstNameDictionary.size(query.languages);
    int maxItemsSurnames = lastNamesDictionary.size(query.languages);
    int maxItems = maxItemsGivenNames * maxItemsSurnames;
    if (query.count > maxItems) {
      throw new IllegalArgumentException("Cannot pick more than " + maxItems + " elements in the Dictionary");
    }
    Collection<FirstNameAndGenderPartial> givenNames = firstNameDictionary.pick(new Query(Math.min(query.count, maxItemsGivenNames), query.languages));
    Collection<String> surnames = lastNamesDictionary.pick(new Query(Math.min(query.count, maxItemsSurnames), query.languages));
    return combineNames(givenNames, surnames, query.count);
  }

  @Override
  public List<Person> nextPersons(int count) {
    return nextPersons(new Query(count));
  }

  private List<Person> combineNames(Collection<FirstNameAndGenderPartial> givenNames, Collection<String> lastNames, int count) {
    Set<UniquePerson> result = new HashSet<>(count);
    Iterator<FirstNameAndGenderPartial> givenNameIterator = givenNames.iterator();
    Iterator<String> surnameIterator = lastNames.iterator();
    int page = 0;
    while (result.size() < count) {
      // Loop through the iterator while items are still remaining
      if (!givenNameIterator.hasNext()) {
        // Each time though, start from a different place to ensure names are different.
        page++;
        givenNameIterator = givenNames.iterator();
        for (int i = 0; i < page; i++) {
          givenNameIterator.next();
        }
      }
      if (!surnameIterator.hasNext()) {
        page++;
        surnameIterator = lastNames.iterator();
        for (int i = 0; i < page; i++) {
          surnameIterator.next();
        }
      }
      FirstNameAndGenderPartial firstName = givenNameIterator.next();
      String lastName = surnameIterator.next();
      String nickname = NicknameCreator.from(firstName.firstName, lastName, firstName.language);
      String email = generateEmail(nickname);
      int age = generateRandomAge();
      result.add(firstName.toUniquePerson(lastName, nickname, age, email));
    }
    return result.stream().map(UniquePerson::toPerson).collect(Collectors.toList());
  }

  @Override
  public List<FirstNameAndGenderPartial> nextFirstNames(int count) {
    return firstNameDictionary.pick(count);
  }

  @Override
  public List<String> nextLastNames(int count) {
    return lastNamesDictionary.pick(count);
  }

  @Override
  public List<String> nextNicknames(int count) {
    List<FirstNameAndGenderPartial> items = firstNameDictionary.pick(count);
    List<String> nicknames = new ArrayList<String>(items.size());
    for (FirstNameAndGenderPartial gn : items) {
      nicknames.add(NicknameCreator.fromFirstName(gn.firstName, Language.ENGLISH));
    }
    return nicknames;
  }

  @Override
  public Person nextPerson() {
    Language randomLanguage = Language.random();
    FirstNameAndGenderPartial firstName = firstNameDictionary.pick(randomLanguage);
    String lastName = lastNamesDictionary.pick(randomLanguage);
    String nickname = NicknameCreator.from(firstName.firstName, lastName, randomLanguage);
    String email = generateEmail(nickname);
    int age = generateRandomAge();
    return firstName.toPerson(lastName, nickname, age, email);
  }

  @Override
  public FirstNameAndGenderPartial nextFirstName() {
    return firstNameDictionary.pick();
  }

  @Override
  public String nextLastName() {
    return lastNamesDictionary.pick();
  }

  @Override
  public String nextNickname() {
    return nextPerson().nickname;
  }

  private String generateEmail(String nickname) {
    int index = (int) (Math.random() * 100);
    return nickname + "@" + domains.get(index);
  }
  private int generateRandomAge() {
    return (int) (10 + (Math.random() * 70));
  }
}
