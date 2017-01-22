package io.resourcepool.generator.impl;

import io.resourcepool.dictionary.impl.GivenNamesDictionary;
import io.resourcepool.dictionary.impl.SurnamesDictionary;
import io.resourcepool.generator.NameGenerator;
import io.resourcepool.generator.Query;
import io.resourcepool.model.FullName;
import io.resourcepool.model.GivenName;
import io.resourcepool.model.Language;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Simple Name generator using the dictionaries provided in the classpath resources.
 *
 * @author Loïc Ortola
 */
public class SimpleNameGenerator implements NameGenerator {

  private GivenNamesDictionary givenNamesDictionary;
  private SurnamesDictionary surnamesDictionary;

  public SimpleNameGenerator() {
    surnamesDictionary = new SurnamesDictionary();
    givenNamesDictionary = new GivenNamesDictionary();
  }

  @Override
  public Collection<FullName> nextNames(Query query) {
    int maxItemsGivenNames = givenNamesDictionary.size(query.languages);
    int maxItemsSurnames = surnamesDictionary.size(query.languages);
    int maxItems = maxItemsGivenNames * maxItemsSurnames;
    if (query.count > maxItems) {
      throw new IllegalArgumentException("Cannot pick more than " + maxItems + " elements in the Dictionary");
    }
    Collection<GivenName> givenNames = givenNamesDictionary.pick(new Query(Math.min(query.count, maxItemsGivenNames), query.languages));
    Collection<String> surnames = surnamesDictionary.pick(new Query(Math.min(query.count, maxItemsSurnames), query.languages));
    return combineNames(givenNames, surnames, query.count);
  }

  @Override
  public Collection<FullName> nextNames(int count) {
    return nextNames(new Query(count));
  }

  private Collection<FullName> combineNames(Collection<GivenName> givenNames, Collection<String> surnames, int count) {
    List<FullName> fullNames = new ArrayList<FullName>(count);
    Iterator<GivenName> givenNameIterator = givenNames.iterator();
    Iterator<String> surnameIterator = surnames.iterator();
    int remaining = count;
    int page = 0;
    while (remaining > 0) {
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
        surnameIterator = surnames.iterator();
        for (int i = 0; i < page; i++) {
          surnameIterator.next();
        }
      }
      fullNames.add(givenNameIterator.next().toFullName(surnameIterator.next()));
    }
    return fullNames;
  }

  @Override
  public Collection<GivenName> nextGivenNames(int count) {
    return givenNamesDictionary.pick(count);
  }

  @Override
  public Collection<String> nextSurnames(int count) {
    return surnamesDictionary.pick(count);
  }

  @Override
  public Collection<String> nextNicknames(int count) {
    Collection<GivenName> items = givenNamesDictionary.pick(count);
    List<String> nicknames = new ArrayList<String>(items.size());
    for (GivenName gn : items) {
      nicknames.add(gn.nickname);
    }
    return nicknames;
  }

  @Override
  public FullName nextName() {
    Language randomLanguage = Language.random();
    return givenNamesDictionary.pick(randomLanguage).toFullName(surnamesDictionary.pick(randomLanguage));
  }

  @Override
  public GivenName nextGivenName() {
    return givenNamesDictionary.pick();
  }

  @Override
  public String nextSurname() {
    return surnamesDictionary.pick();
  }

  @Override
  public String nextNickname() {
    return givenNamesDictionary.pick().nickname;
  }
}
