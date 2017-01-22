package io.resourcepool.dictionary.impl;

import io.resourcepool.dictionary.Dictionary;
import io.resourcepool.dictionary.loader.FortuneLoader;
import io.resourcepool.generator.Query;
import io.resourcepool.model.Fortune;
import io.resourcepool.model.Language;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Fortunes dictionary.
 * Contains fortunes in each language
 *
 * @author Loïc Ortola
 */
public class FortunesDictionary implements Dictionary<Fortune> {
  private final Map<Language, List<Fortune>> fortunes;

  public FortunesDictionary() {
    this.fortunes = new HashMap<Language, List<Fortune>>();
    fortunes.put(Language.FRENCH, new ArrayList<Fortune>(FortuneLoader.load(Language.FRENCH)));
    fortunes.put(Language.ENGLISH, new ArrayList<Fortune>(FortuneLoader.load(Language.ENGLISH)));
  }

  protected Map<Language, List<Fortune>> getFortunes() {
    return fortunes;
  }

  @Override
  public Fortune pick(Language randomLanguage) {
    List<Fortune> items = this.fortunes.get(randomLanguage);
    return items.get((int) Math.random() * items.size()).clone();
  }

  @Override
  public Fortune pick() {
    Language randomLanguage = Language.random();
    return pick(randomLanguage).clone();
  }

  @Override
  public List<Fortune> pick(int count) {
    return pick(new Query(count));
  }

  @Override
  public List<Fortune> pick(Query query) {
    if (query.count > size(query.languages)) {
      throw new IllegalArgumentException("Cannot pick more than " + size() + " elements in Fortunes Dictionary");
    }
    List<Fortune> items = getEditableDictionary(query.languages);
    Collections.shuffle(items);
    return items.subList(0, query.count);
  }

  @Override
  public int size(Language... languages) {
    int size = 0;
    for (Language l : languages) {
      size += fortunes.get(l).size();
    }
    return size;
  }

  @Override
  public int size() {
    return size(Language.values());
  }


  private List<Fortune> getEditableDictionary(Language... languages) {
    List<Fortune> values = new LinkedList<Fortune>();
    for (Language l : languages) {
      values.addAll(deepClone(fortunes.get(l)));
    }
    return values;
  }

  private Collection<Fortune> deepClone(List<Fortune> givenNames) {
    List<Fortune> items = new LinkedList<Fortune>();
    for (Fortune f : givenNames) {
      items.add(f.clone());
    }
    return items;
  }
}
