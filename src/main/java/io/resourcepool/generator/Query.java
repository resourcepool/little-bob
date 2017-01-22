package io.resourcepool.generator;

import io.resourcepool.model.Language;

/**
 * Text Generator Filter.
 * Use this class to specify filters for your content generator
 * @author Loïc Ortola
 */
public class Query {
  
  public final int count;
  public final Language[] languages;

  public Query(int count, Language... languages) {
    this.count = count > 0 ? count : 1;
    this.languages = (languages == null || languages.length == 0) ? Language.values() : languages;
  }
  
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    
    private int count;
    private Language[] languages;  
    
    private Builder() {
    }
    
    public Builder count(int count) {
      if (count <= 0) {
        throw new IllegalArgumentException("Count must be greater than zero");
      }
      this.count = count;
      return this;
    }
    
    public Builder languages(Language... languages) {
      this.languages = languages;
      return this;
    }
    
    public Query build() {
      return new Query(count, languages);
    }
  }
}
