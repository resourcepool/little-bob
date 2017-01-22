package io.resourcepool.generator.impl;

import io.resourcepool.dictionary.impl.FortunesDictionary;
import io.resourcepool.generator.FortuneGenerator;
import io.resourcepool.generator.Query;
import io.resourcepool.model.Fortune;
import io.resourcepool.model.Language;

import java.util.Collection;

/**
 * Simple Fortune generator using the dictionaries provided in the classpath resources.
 *
 * @author Loïc Ortola
 */
public class SimpleFortuneGenerator implements FortuneGenerator {

  private FortunesDictionary dictionary;

  public SimpleFortuneGenerator() {
    dictionary = new FortunesDictionary();
  }
  
  @Override
  public Collection<Fortune> nextFortunes(Query query) {
    int maxItems = dictionary.size(query.languages);
    if (query.count > maxItems) {
      throw new IllegalArgumentException("Cannot pick more than " + maxItems + " elements in the Dictionary");
    }
    return dictionary.pick(query);
  }

  @Override
  public Collection<Fortune> nextFortunes(int count) {
    return nextFortunes(new Query(count));
  }

  @Override
  public Fortune nextFortune() {
    return dictionary.pick();
  }

  @Override
  public Fortune nextFortune(Language language) {
    return dictionary.pick(language);
  }
}
