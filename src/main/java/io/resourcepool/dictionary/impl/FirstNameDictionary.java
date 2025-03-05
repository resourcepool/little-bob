package io.resourcepool.dictionary.impl;

import io.resourcepool.dictionary.Dictionary;
import io.resourcepool.dictionary.loader.NameLoader;
import io.resourcepool.generator.Query;
import io.resourcepool.model.FirstNameAndGenderPartial;
import io.resourcepool.model.Language;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Names dictionary.
 * Contains names in each language
 *
 * @author Loïc Ortola
 */
public class FirstNameDictionary implements Dictionary<FirstNameAndGenderPartial> {

  private final Map<Language, List<FirstNameAndGenderPartial>> firstNames;

  public FirstNameDictionary() {
    this.firstNames = new HashMap<Language, List<FirstNameAndGenderPartial>>();
    firstNames.put(Language.ENGLISH, new ArrayList<FirstNameAndGenderPartial>(NameLoader.loadFirstNames(Language.ENGLISH)));
    firstNames.put(Language.FRENCH, new ArrayList<FirstNameAndGenderPartial>(NameLoader.loadFirstNames(Language.FRENCH)));
  }

  protected Map<Language, List<FirstNameAndGenderPartial>> getFirstNames() {
    return firstNames;
  }

  @Override
  public FirstNameAndGenderPartial pick(Language randomLanguage) {
    List<FirstNameAndGenderPartial> items = this.firstNames.get(randomLanguage);
    return items.get((int) (Math.random() * items.size())).clone();
  }

  @Override
  public FirstNameAndGenderPartial pick() {
    Language randomLanguage = Language.random();
    return pick(randomLanguage).clone();
  }

  @Override
  public List<FirstNameAndGenderPartial> pick(int count) {
    return pick(new Query(count));
  }

  @Override
  public List<FirstNameAndGenderPartial> pick(Query query) {
    if (query.count > size()) {
      throw new IllegalArgumentException("Cannot pick more than " + size() + " elements in GivenNames Dictionary");
    }
    List<FirstNameAndGenderPartial> items = getEditableDictionary(query.languages);
    Collections.shuffle(items);
    return new LinkedList<>(items.subList(0, query.count));
  }

  @Override
  public int size(Language... languages) {
    int size = 0;
    for (Language l : languages) {
      size += firstNames.get(l).size();
    }
    return size;
  }

  @Override
  public int size() {
    return size(Language.values());
  }

  private List<FirstNameAndGenderPartial> getEditableDictionary(Language... languages) {
    List<FirstNameAndGenderPartial> values = new LinkedList<FirstNameAndGenderPartial>();
    for (Language l : languages) {
      values.addAll(deepClone(firstNames.get(l)));
    }
    return values;
  }

  private Collection<FirstNameAndGenderPartial> deepClone(List<FirstNameAndGenderPartial> givenNames) {
    List<FirstNameAndGenderPartial> items = new LinkedList<FirstNameAndGenderPartial>();
    for (FirstNameAndGenderPartial n : givenNames) {
      items.add(n.clone());
    }
    return items;
  }

}
