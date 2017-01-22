package io.resourcepool.dictionary.impl;

import io.resourcepool.dictionary.Dictionary;
import io.resourcepool.dictionary.loader.NameLoader;
import io.resourcepool.generator.Query;
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
 * Contains surnames in each language
 * @author Loïc Ortola
 */
public class SurnamesDictionary implements Dictionary<String> {
  
  private final Map<Language, List<String>> surnames;

  public SurnamesDictionary() {
    this.surnames = new HashMap<Language, List<String>>();
    surnames.put(Language.ENGLISH, new ArrayList<String>(NameLoader.loadSurnames(Language.ENGLISH)));
    surnames.put(Language.FRENCH, new ArrayList<String>(NameLoader.loadSurnames(Language.FRENCH)));
  }

  protected Map<Language, List<String>> getSurnames() {
    return surnames;
  }

  @Override
  public String pick(Language randomLanguage) {
    List<String> items = this.surnames.get(randomLanguage);
    return items.get((int) Math.random() * items.size());
  }

  @Override
  public String pick() {
    Language randomLanguage = Language.random();
    return pick(randomLanguage);
  }

  @Override
  public Collection<String> pick(int count) {
    return pick(new Query(count));
  }

  @Override
  public Collection<String> pick(Query query) {
    if (query.count > size()) {
      throw new IllegalArgumentException("Cannot pick more than " + size() + " elements in Surnames Dictionary");
    }
    List<String> items = getEditableDictionary(query.languages);
    Collections.shuffle(items);
    return items.subList(0, query.count);
  }

  @Override
  public int size(Language... languages) {
    int size = 0;
    for (Language l : languages) {
      size += surnames.get(l).size();
    }
    return size;
  }

  @Override
  public int size() {
    return size(Language.values());
  }

  
  private List<String> getEditableDictionary(Language... languages) {
    List<String> values = new LinkedList<String>();
    for (Language l : languages) {
      values.addAll(surnames.get(l));
    }
    return values;
  }

}
