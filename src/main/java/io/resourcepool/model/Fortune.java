package io.resourcepool.model;

/**
 * Represents a fortune saying or quote. Contains a text and a source.
 *
 * @author Loïc Ortola on 22/01/2017
 */
public class Fortune implements Cloneable {
  
  public final String text;
  public final String source;


  public Fortune(String text, String source) {
    this.text = text;
    this.source = source;
  }

  @Override
  public Fortune clone() {
    return new Fortune(text, source);
  }
}
