package io.resourcepool.generator;

import io.resourcepool.model.Fortune;
import io.resourcepool.model.Language;

import java.util.Collection;

/**
 * Name Generator.
 * Generates random mames (first name, last name, nickname) by gender
 * @author Loïc Ortola
 */
public interface FortuneGenerator {
  
  Collection<Fortune> nextFortunes(Query query);
  Collection<Fortune> nextFortunes(int count);
  Fortune nextFortune();
  Fortune nextFortune(Language language);
}
