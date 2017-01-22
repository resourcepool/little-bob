package io.resourcepool.dictionary.impl;

import io.resourcepool.generator.Query;
import io.resourcepool.model.GivenName;
import io.resourcepool.model.Language;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * @author Loïc Ortola
 */
public class GivenNamesDictionaryTest {

  private GivenNamesDictionary dictionary = new GivenNamesDictionary();

  private static final int FRENCH_DICTIONARY_SIZE = 2305;
  private static final int ALL_DICTIONARY_SIZE = 4305;
  
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
    Collection<GivenName> pick = dictionary.pick(-10);
    Assert.assertEquals(1, pick.size());
    pick = dictionary.pick(0);
    Assert.assertEquals(1, pick.size());
  }

  @Test
  public void testValidPickEnglish() {
    GivenName pick = dictionary.pick(Language.ENGLISH);
    Assert.assertNotNull(pick.givenName);
    Assert.assertNotNull(pick.nickname);
    Assert.assertNotNull(pick.gender);
  }

  @Test
  public void testValidPickRandom() {
    GivenName pick = dictionary.pick();
    Assert.assertNotNull(pick.givenName);
    Assert.assertNotNull(pick.nickname);
    Assert.assertNotNull(pick.gender);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPickMultipleFilter() {
    Collection<GivenName> pick = dictionary.pick(Query.builder().count(-1).build());
  }

  @Test
  public void testValidPickMultipleFilterEmptyCount() {
    Collection<GivenName> pick = dictionary.pick(Query.builder().languages(Language.ENGLISH).build());
    Assert.assertEquals(1, pick.size());
  }

  @Test
  public void testValidPickMultipleFilter() {
    Collection<GivenName> pick = dictionary.pick(Query.builder().count(100).languages(Language.FRENCH).build());
    Assert.assertEquals(100, pick.size());
    for (GivenName n : pick) {
      Assert.assertFalse(dictionary.getGivenNames().get(Language.ENGLISH).contains(n));
    }
  }


}
