package io.resourcepool.generator;

import io.resourcepool.model.Fortune;
import io.resourcepool.model.Language;

import java.util.List;

/**
 * Name Generator.
 * Generates random mames (first name, last name, nickname) by gender
 * @author Loïc Ortola
 */
public interface FortuneGenerator {
  
  List<Fortune> nextFortunes(Query query);
  List<Fortune> nextFortunes(int count);
  Fortune nextFortune();
  Fortune nextFortune(Language language);
}
