# Little bob

A simple human and sentence generator in your language.

Make up names, nicknames, and find sample textual content for your applications, using the provided dictionaries.

The name is a tribute to the "Le petit Robert" Dictionaries.

## What it does
Lorem Ipsum is sooooooo 2016.

With Little Bob, you can generate lots of FullNames (given name, surname, nickname, gender) and Fortune sayings and quotes, in the language of your choice.

Current supported languages: **FRENCH, ENGLISH**

## Compatibility / Setup
The sources are compatible with Java 8+.

To use the library with maven, add these lines to your **pom.xml** file:

```xml
<dependency>
 <groupId>io.resourcepool</groupId>
 <artifactId>little-bob</artifactId>
 <version>2.0.0</version>
</dependency>
```

## API / Sample use

### Fortune

For starters, initialize your generator (ideally as a class attribute):
```java
FortuneGenerator generator = new SimpleFortuneGenerator();
```

Generate a fortune in any language:
```java
Fortune f = generator.nextFortune();
```

Generate a fortune in a specific language:
```java
Fortune f = generator.nextFortune(Language.FRENCH);
```

Generate a list of 100 fortunes in any language:
```java
List<Fortune> f = generator.nextFortunes(100);
```

Generate a list of 100 fortunes in FRENCH
```java
Query q = Query.builder()
               .count(100)
               .languages(Language.FRENCH)
               .build();
List<Fortune> f = generator.nextFortunes(q);
```


Example:
```java
public class Main {
  
  public static void main(String[] args){
    FortuneGenerator generator = new SimpleFortuneGenerator();
    // Generate one fortune in any language
    Fortune f = generator.nextFortune();
    System.out.println("Fortune of the day:");
    System.out.println(f.text);    
    System.out.println("That fortune is from:");
    System.out.println(f.source);
    
    // Generate one fortune in French
    f = generator.nextFortune(Language.FRENCH);
    System.out.println("French Fortune of the day:");
    System.out.println(f.text);    
    System.out.println("That french fortune is from:");
    System.out.println(f.source);
    
    // Generate 100 fortunes in English or French
    List<Fortune> fortunes = generator.nextFortunes(
      Query.builder()
           .count(100)
           .languages(Language.ENGLISH, Language.FRENCH)
           .build()
    );
  }
}
```

### Names

For starters, initialize your generator (ideally as a class attribute):
```java
PersonGenerator generator = new SimplePersonGenerator();
```

Generate a name in any language:
```java
// Full name (firstName, lastName, nickname, age, gender, email)
FullName fn = generator.nextPerson();
// Given name (given name, gender, language)
GivenName gn = generator.nextFirstName();
// Nickname
String nick = generator.nextNickname();
// Surname
String surname = generator.nextLastName();
```

Generate a list of 100 names in any language:
```java
List<Person> fns = generator.nextPersons(100);
```

Generate a list of 100 names in FRENCH
```java
Query q = Query.builder()
               .count(100)
               .languages(Language.FRENCH)
               .build();
List<Person> f = generator.nextPersons(q);
```


Example:
```java
public class Main {
  
  public static void main(String[] args){
    FortuneGenerator generator = new SimpleFortuneGenerator();
    // Generate one name in any language
    FullName fn = generator.nextName();
    System.out.println("The murderer has a name:");
    System.out.println(fn.givenName + " " + fn.surname);    
    System.out.println(Gender.MALE.equals(fn.gender) ? "He" : "She" + " goes by the alias:");
    System.out.println(fn.nickname);
    
    // Generate 100 names in English or French
    List<Person> accomplices = generator.nextPersons(
      Query.builder()
           .count(100)
           .languages(Language.ENGLISH, Language.FRENCH)
           .build()
    );
  }
}
```

## License
This project is licensed under the **Apache 2.0 License**.

## Contributions
We welcome any contributions via Issues and/or Pull-requests
