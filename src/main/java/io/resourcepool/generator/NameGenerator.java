package io.resourcepool.generator;

import io.resourcepool.model.FullName;
import io.resourcepool.model.GivenName;

import java.util.Collection;

/**
 * Name Generator.
 * Generates random mames (first name, last name, nickname) by gender
 * @author Loïc Ortola
 */
public interface NameGenerator {
  
  Collection<FullName> nextNames(Query query);
  Collection<FullName> nextNames(int count);
  Collection<GivenName> nextGivenNames(int count);
  Collection<String> nextSurnames(int count);
  Collection<String> nextNicknames(int count);
  FullName nextName();
  GivenName nextGivenName();
  String nextSurname();
  String nextNickname();
  
}
