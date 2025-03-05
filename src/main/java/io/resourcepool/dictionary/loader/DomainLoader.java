package io.resourcepool.dictionary.loader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * Parse domains into a POJO.
 * This reads from the classpath resources in /little-bob/
 *
 * @author Loïc Ortola
 */
public class DomainLoader {

  private static final String DOMAINS_FILE = "domains.txt";
  private static final Charset CHARSET = Charset.forName("UTF-8");

  public static List<String> loadDomains() {
    return parseDomains();
  }

  private static List<String> parseDomains() {
    List<String> domains = new LinkedList<String>();
    BufferedReader br = new BufferedReader(new InputStreamReader(getResource(DOMAINS_FILE), CHARSET));
    try {
      String line;
      while ((line = br.readLine()) != null) {
        domains.add(line);
      }
      return domains;
    } catch (IOException e) {
      try {
        br.close();
      } catch (IOException e1) {
        throw new IllegalStateException(e1);
      }
      throw new IllegalStateException(e);
    }
  }

  private static InputStream getResource(String source) {
    String fileName = "little-bob/" + source;
    return DomainLoader.class.getClassLoader().getResourceAsStream(fileName);
  }
}
