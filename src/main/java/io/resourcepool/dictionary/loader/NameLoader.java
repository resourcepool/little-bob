package io.resourcepool.dictionary.loader;

import io.resourcepool.model.Gender;
import io.resourcepool.model.GivenName;
import io.resourcepool.model.Language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * Parse names files into a POJO.
 * This reads from the classpath resources in /little-bob/
 *
 * @author Loïc Ortola
 */
public class NameLoader {

  private static final String FIRST_NAMES_FILE = "first_names";
  private static final String LAST_NAMES_FILE = "last_names";
  private static final String EXTENSION = ".txt";
  private static final Charset CHARSET = Charset.forName("UTF-8");

  public static List<GivenName> loadGivenNames(Language language) {
    return parseNames(language);
  }

  public static List<String> loadSurnames(Language language) {
    List<String> names = new LinkedList<String>();
    BufferedReader br = new BufferedReader(new InputStreamReader(getResource(LAST_NAMES_FILE, language), CHARSET));
    try {
      StringBuilder sb = new StringBuilder();
      String name;
      while ((name = br.readLine()) != null) {
        names.add(name);
      }
      return names;
    } catch (IOException e) {
      try {
        br.close();
      } catch (IOException e1) {
        throw new IllegalStateException(e1);
      }
      throw new IllegalStateException(e);
    }
  }

  private static List<GivenName> parseNames(Language language) {
    List<GivenName> givenNames = new LinkedList<GivenName>();
    BufferedReader br = new BufferedReader(new InputStreamReader(getResource(FIRST_NAMES_FILE, language), CHARSET));
    try {
      String name;
      while ((name = br.readLine()) != null) {
        String[] nameContent = name.split(",");
        givenNames.add(new GivenName(nameContent[0], nameContent[1], Gender.parse(nameContent[2])));
      }
      return givenNames;
    } catch (IOException e) {
      try {
        br.close();
      } catch (IOException e1) {
        throw new IllegalStateException(e1);
      }
      throw new IllegalStateException(e);
    }
  }

  private static InputStream getResource(String source, Language language) {
    String fileName = "little-bob/" + source + "_" + language.getLocaleCode() + EXTENSION;
    return NameLoader.class.getClassLoader().getResourceAsStream(fileName);
  }
}
