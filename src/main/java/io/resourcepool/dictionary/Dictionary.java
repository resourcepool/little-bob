package io.resourcepool.dictionary;

import io.resourcepool.generator.Query;
import io.resourcepool.model.Language;

import java.util.List;

/**
 * Represents a multilingual dictionary for any generic type of data.
 *
 * @author Loïc Ortola
 */
public interface Dictionary<T> {

  T pick(Language language);

  T pick();

  List<T> pick(int count);

  List<T> pick(Query query);

  int size(Language... languages);

  int size();
}
