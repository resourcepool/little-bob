package io.resourcepool.generator;

import io.resourcepool.dictionary.impl.FortuneDictionaryTest;
import io.resourcepool.dictionary.impl.FortunesDictionary;
import io.resourcepool.generator.impl.SimpleFortuneGenerator;
import io.resourcepool.generator.impl.SimplePersonGenerator;
import io.resourcepool.model.Fortune;
import io.resourcepool.model.Language;
import io.resourcepool.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author Loïc Ortola on 22/01/2017
 */
public class SimplePersonGeneratorTest {
  private PersonGenerator generator = new SimplePersonGenerator();

  @Test
  public void testSimple() {
    Person person = generator.nextPerson();
    Assert.assertNotNull(person.firstName);
    Assert.assertNotNull(person.lastName);
    Assert.assertNotNull(person.nickname);
    Assert.assertTrue(person.age > 0);
    Assert.assertNotNull(person.email);
    Assert.assertNotNull(person.gender);
  }

  @Test
  public void testUnicity() {
    List<Person> persons = generator.nextPersons(1_000_000);
    Set<String> nicknamesUnique = new HashSet<>();
    for (Person p : persons) {
      nicknamesUnique.add(p.nickname);
    }
    Assert.assertEquals(nicknamesUnique.size(), persons.size());
  }


}
