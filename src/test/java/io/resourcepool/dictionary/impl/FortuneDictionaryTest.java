package io.resourcepool.dictionary.impl;

import io.resourcepool.generator.Query;
import io.resourcepool.model.Fortune;
import io.resourcepool.model.Language;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * @author Loïc Ortola
 */
public class FortuneDictionaryTest {

  private FortunesDictionary dictionary = new FortunesDictionary();

  public static final int FRENCH_DICTIONARY_SIZE = 1812;
  public static final int ALL_DICTIONARY_SIZE = 11812;
  
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
    Collection<Fortune> pick = dictionary.pick(-10);
    Assert.assertEquals(1, pick.size());
    pick = dictionary.pick(0);
    Assert.assertEquals(1, pick.size());
  }

  @Test
  public void testValidPickEnglish() {
    Fortune pick = dictionary.pick(Language.ENGLISH);
    Assert.assertNotNull(pick.source);
    Assert.assertNotNull(pick.text);
  }

  @Test
  public void testValidPickRandom() {
    Fortune pick = dictionary.pick();
    Assert.assertNotNull(pick.source);
    Assert.assertNotNull(pick.text);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPickMultipleFilter() {
    Collection<Fortune> pick = dictionary.pick(Query.builder().count(-1).build());
  }

  @Test
  public void testValidPickMultipleFilterEmptyCount() {
    Collection<Fortune> pick = dictionary.pick(Query.builder().languages(Language.ENGLISH).build());
    Assert.assertEquals(1, pick.size());
  }

  @Test
  public void testValidPickMultipleFilter() {
    Collection<Fortune> pick = dictionary.pick(Query.builder().count(100).languages(Language.FRENCH).build());
    Assert.assertEquals(100, pick.size());
    for (Fortune f : pick) {
      Assert.assertFalse(dictionary.getFortunes().get(Language.ENGLISH).contains(f));
    }
  }


}
