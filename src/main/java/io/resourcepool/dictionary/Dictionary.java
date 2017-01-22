package io.resourcepool.dictionary;

import io.resourcepool.generator.Query;
import io.resourcepool.model.Language;

import java.util.Collection;

/**
 * Represents a multi-lingual dictionary for any generic type of data.
 *
 * @author Loïc Ortola
 */
public interface Dictionary<T> {
  
  T pick(Language language);

  T pick();

  Collection<T> pick(int count);

  Collection<T> pick(Query query);

  int size(Language... languages);

  int size();
}
