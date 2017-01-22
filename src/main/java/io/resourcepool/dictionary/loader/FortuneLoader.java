package io.resourcepool.dictionary.loader;

import io.resourcepool.model.Fortune;
import io.resourcepool.model.Language;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * Parse fortune file into a POJO.
 * This reads from the classpath resources in /little-bob/
 *
 * @author Loïc Ortola
 */
public class FortuneLoader {

  private static final String FORTUNES_FILE = "fortunes";
  private static final String FORTUNES_EXTENSION = ".txt";
  private static final Charset CHARSET = Charset.forName("UTF-8");

  public static List<Fortune> load(Language language) {
    List<Fortune> fortunes = new LinkedList<Fortune>();
    BufferedReader br = new BufferedReader(new InputStreamReader(getResource(language), CHARSET));
    try {
      StringBuilder text = new StringBuilder();
      StringBuilder source = new StringBuilder();
      String line;
      while ((line = br.readLine()) != null) {
        if (line.startsWith("%")) {
          // Fortune was finished. switch to next.
          if (source.length() > 0) {
            fortunes.add(new Fortune(text.toString(), source.toString()));
          }
          text = new StringBuilder();
          source = new StringBuilder();
        } else if (line.contains("-+-") || line.contains("-- ")) {
          // Source
          if (source.length() > 0) {
            source.append("\n");
          }
          source.append(line.replaceAll("-\\+-", "").replaceAll("-- ", "").trim());
        } else {
          if (text.length() > 0) {
            text.append("\n");
          }
          // Fortune content
          text.append(line);
        }
      }
      return fortunes;
    } catch (IOException e) {
      try {
        br.close();
      } catch (IOException e1) {
        throw new IllegalStateException(e1);
      }
      throw new IllegalStateException(e);
    }
  }

  private static InputStream getResource(Language language) {
    String fileName = "little-bob/" + FORTUNES_FILE + "_" + language.getLocaleCode() + FORTUNES_EXTENSION;
    return FortuneLoader.class.getClassLoader().getResourceAsStream(fileName);
  }
}
