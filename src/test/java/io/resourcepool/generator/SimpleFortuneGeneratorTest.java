package io.resourcepool.generator;

import io.resourcepool.dictionary.impl.FortuneDictionaryTest;
import io.resourcepool.dictionary.impl.FortunesDictionary;
import io.resourcepool.generator.impl.SimpleFortuneGenerator;
import io.resourcepool.model.Fortune;
import io.resourcepool.model.Language;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

/**
 * @author Loïc Ortola on 22/01/2017
 */
public class SimpleFortuneGeneratorTest {
  private FortuneGenerator generator = new SimpleFortuneGenerator();
  
  @Test
  public void testSimple() {
    Fortune fortune = generator.nextFortune();
    Assert.assertNotNull(fortune.source);
    Assert.assertNotNull(fortune.text);
  }
  
  @Test
  public void testFrench() {
    Fortune fortune = generator.nextFortune(Language.FRENCH);
    Assert.assertNotNull(fortune.source);
    Assert.assertNotNull(fortune.text);
  }
  
  @Test
  public void testValidMultiple() {
    Collection<Fortune> fortunes = generator.nextFortunes(100);
    Assert.assertEquals(100, fortunes.size());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMultipleTooBig() {
    generator.nextFortunes(100000);
  }
  
  @Test
  public void testInvalidMultipleNegative() {
    Collection<Fortune> fortunes = generator.nextFortunes(-10);
    Assert.assertEquals(1, fortunes.size());
    fortunes = generator.nextFortunes(0);
    Assert.assertEquals(1, fortunes.size());
  }
  
  @Test
  public void testValidMultipleFilter() {
    Collection<Fortune> fortunes = generator.nextFortunes(Query.builder().count(1000).languages(Language.ENGLISH).build());
    Assert.assertEquals(1000, fortunes.size());
    Collection<Fortune> frenchDictionary = new FortunesDictionary().pick(Query.builder().count(FortuneDictionaryTest.FRENCH_DICTIONARY_SIZE).languages(Language.FRENCH).build());
    for (Fortune f : fortunes) {
      Assert.assertFalse(frenchDictionary.contains(f));
    }
  }
  
  
}
