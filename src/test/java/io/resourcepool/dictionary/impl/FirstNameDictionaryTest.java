package io.resourcepool.dictionary.impl;

import io.resourcepool.generator.Query;
import io.resourcepool.model.FirstNameAndGenderPartial;
import io.resourcepool.model.Language;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * @author Loïc Ortola
 */
public class FirstNameDictionaryTest {

  private FirstNameDictionary dictionary = new FirstNameDictionary();

  private static final int FRENCH_DICTIONARY_SIZE = 1518;
  private static final int ALL_DICTIONARY_SIZE = 6584 + 1518;

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
    Collection<FirstNameAndGenderPartial> pick = dictionary.pick(-10);
    Assert.assertEquals(1, pick.size());
    pick = dictionary.pick(0);
    Assert.assertEquals(1, pick.size());
  }

  @Test
  public void testValidPickEnglish() {
    FirstNameAndGenderPartial pick = dictionary.pick(Language.ENGLISH);
    Assert.assertNotNull(pick.firstName);
    Assert.assertNotNull(pick.gender);
    Assert.assertEquals(Language.ENGLISH, pick.language);
  }

  @Test
  public void testValidPickRandom() {
    FirstNameAndGenderPartial pick = dictionary.pick();
    Assert.assertNotNull(pick.firstName);
    Assert.assertNotNull(pick.language);
    Assert.assertNotNull(pick.gender);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPickMultipleFilter() {
    Collection<FirstNameAndGenderPartial> pick = dictionary.pick(Query.builder().count(-1).build());
  }

  @Test
  public void testValidPickMultipleFilterEmptyCount() {
    Collection<FirstNameAndGenderPartial> pick = dictionary.pick(Query.builder().languages(Language.ENGLISH).build());
    Assert.assertEquals(1, pick.size());
  }

  @Test
  public void testValidPickMultipleFilter() {
    Collection<FirstNameAndGenderPartial> pick = dictionary.pick(Query.builder().count(100).languages(Language.FRENCH).build());
    Assert.assertEquals(100, pick.size());
    for (FirstNameAndGenderPartial n : pick) {
      Assert.assertFalse(dictionary.getFirstNames().get(Language.ENGLISH).contains(n));
    }
  }


}
