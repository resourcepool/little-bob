package io.resourcepool.dictionary.impl;

import io.resourcepool.dictionary.Dictionary;
import io.resourcepool.dictionary.loader.NameLoader;
import io.resourcepool.generator.Query;
import io.resourcepool.model.GivenName;
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
public class GivenNamesDictionary implements Dictionary<GivenName> {

  private final Map<Language, List<GivenName>> givenNames;

  public GivenNamesDictionary() {
    this.givenNames = new HashMap<Language, List<GivenName>>();
    givenNames.put(Language.ENGLISH, new ArrayList<GivenName>(NameLoader.loadGivenNames(Language.ENGLISH)));
    givenNames.put(Language.FRENCH, new ArrayList<GivenName>(NameLoader.loadGivenNames(Language.FRENCH)));
  }

  protected Map<Language, List<GivenName>> getGivenNames() {
    return givenNames;
  }

  @Override
  public GivenName pick(Language randomLanguage) {
    List<GivenName> items = this.givenNames.get(randomLanguage);
    return items.get((int) Math.random() * items.size()).clone();
  }

  @Override
  public GivenName pick() {
    Language randomLanguage = Language.random();
    return pick(randomLanguage).clone();
  }

  @Override
  public Collection<GivenName> pick(int count) {
    return pick(new Query(count));
  }

  @Override
  public Collection<GivenName> pick(Query query) {
    if (query.count > size()) {
      throw new IllegalArgumentException("Cannot pick more than " + size() + " elements in GivenNames Dictionary");
    }
    List<GivenName> items = getEditableDictionary(query.languages);
    Collections.shuffle(items);
    return items.subList(0, query.count);
  }

  @Override
  public int size(Language... languages) {
    int size = 0;
    for (Language l : languages) {
      size += givenNames.get(l).size();
    }
    return size;
  }

  @Override
  public int size() {
    return size(Language.values());
  }

  private List<GivenName> getEditableDictionary(Language... languages) {
    List<GivenName> values = new LinkedList<GivenName>();
    for (Language l : languages) {
      values.addAll(deepClone(givenNames.get(l)));
    }
    return values;
  }

  private Collection<GivenName> deepClone(List<GivenName> givenNames) {
    List<GivenName> items = new LinkedList<GivenName>();
    for (GivenName n : givenNames) {
      items.add(n.clone());
    }
    return items;
  }

}
