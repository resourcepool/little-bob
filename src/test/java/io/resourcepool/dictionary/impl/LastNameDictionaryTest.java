package io.resourcepool.dictionary.impl;

import io.resourcepool.generator.Query;
import io.resourcepool.model.Language;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * @author Loïc Ortola
 */
public class LastNameDictionaryTest {

  private LastNameDictionary dictionary = new LastNameDictionary();

  private static final int FRENCH_DICTIONARY_SIZE = 9620;
  private static final int ALL_DICTIONARY_SIZE = 10000 + 9620;

  @Test
  public void testSizeOneLanguage() {
    Assert.assertEquals(FRENCH_DICTIONARY_SIZE, dictionary.size(Language.FRENCH));
  }

  @Test
  public void testSizeAllLanguages() {
    Assert.assertEquals(ALL_DICTIONARY_SIZE, dictionary.size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalCountTooBig() {
    dictionary.pick(ALL_DICTIONARY_SIZE + 1);
  }

  @Test
  public void testInvalidCountNegative() {
    Collection<String> pick = dictionary.pick(-10);
    Assert.assertEquals(1, pick.size());
    pick = dictionary.pick(0);
    Assert.assertEquals(1, pick.size());
  }

  @Test
  public void testValidPickEnglish() {
    String pick = dictionary.pick(Language.ENGLISH);
    Assert.assertNotNull(pick);
  }

  @Test
  public void testValidPickRandom() {
    String pick = dictionary.pick();
    Assert.assertNotNull(pick);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPickMultipleFilter() {
    Collection<String> pick = dictionary.pick(Query.builder().count(-1).build());
  }

  @Test
  public void testValidPickMultipleFilterEmptyCount() {
    Collection<String> pick = dictionary.pick(Query.builder().languages(Language.ENGLISH).build());
    Assert.assertEquals(1, pick.size());
  }

}
