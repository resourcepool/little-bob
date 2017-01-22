package io.resourcepool.generator;

import io.resourcepool.model.FullName;
import io.resourcepool.model.GivenName;

import java.util.List;

/**
 * Name Generator.
 * Generates random mames (first name, last name, nickname) by gender
 * @author Loïc Ortola
 */
public interface NameGenerator {

  List<FullName> nextNames(Query query);
  List<FullName> nextNames(int count);
  List<GivenName> nextGivenNames(int count);
  List<String> nextSurnames(int count);
  List<String> nextNicknames(int count);
  FullName nextName();
  GivenName nextGivenName();
  String nextSurname();
  String nextNickname();
  
}
