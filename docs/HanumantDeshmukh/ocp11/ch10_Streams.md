Chapter 10 - Streams
# 1 Optional
### What
- Optional<T> is a container object that may or may not hold a non-null value.
- Purpose: Avoid NullPointerException and make code more readable.
## How to create
```java
Optional<String> opt1 = Optional.empty(); // Creates an empty Optional
Optional<String> opt2 = Optional.of("Hello"); // (throws NPE if null)
Optional<String> opt3 = Optional.ofNullable(null); // may be empty if value is null
```
### How to get value

| Method                | Description                                               |
|-----------------------|-----------------------------------------------------------|
| `get()`               | Returns value or throws `NoSuchElementException` if empty |
| `isPresent()`         | Checks if value exists                                    |
| `orElse(default)`     | Returns value or default                                  |
| `orElseGet(Supplier)` | Returns value or calls `Supplier`                         |
| `orElseThrow()`       | Throws exception if empty                                 | 

### Transforming Optional
- map(Function) → transforms value if present.
- flatMap(Function) → similar to map but avoids nested Optional.
- filter(Predicate) → keeps value if condition matches.
### Common Exam Traps
- Optional.get() on empty → throws exception.
- Optional is not serializable for collections.
- Avoid using Optional for fields in POJOs (exam may ask this).
-  orElse() vs orElseGet():
    - Both provide a fallback value, but:
    - orElse(defaultValue) → Always evaluates the default value, even if Optional is present.
    - orElseGet(Supplier) → Evaluates the Supplier only if Optional is empty (lazy evaluation).
# 2  Using Streams
- A Stream is a sequence of elements supporting functional-style operations
- not store data, only process data
- Stream are lazy, intermediate operations don't run until a terminal operation is called
### Stream Pipeline Structure
> Source → Intermediate Operations → Terminal Operation
```java
List<String> names = List.of("Tom", "Jerry", "Spike");
names.stream()                      // Source
     .filter(n -> n.length() > 3)   // Intermediate
     .map(String::toUpperCase)      // Intermediate
     .sorted()                      // Intermediate
     .forEach(System.out::println); // Terminal
```
### Creating Streams
- From collections:
```java
list.stream();
list.parallelStream();
```
- From arrays:
```java
String[] array = {"A", "B", "C"};
Stream<String> stream1 = Arrays.stream(array);
```
- From values:
```java
Stream.of("a", "b", "c");
```
- Infinite streams:
```java
Stream.generate(() -> Math.random());
Stream.iterate(1, n -> n + 1);
```
###  Intermediate Operations
- filter(Predicate) → keep elements matching condition.
- map(Function) → transform elements.
- flatMap(Function) → flatten nested streams.
- distinct() → remove duplicates.
- sorted() → sort elements.
- peek(Consumer) → debug/log.
- limit(n) / skip(n) → control size.
### Terminal Operations
- forEach(Consumer) → iterate.
-  collect(Collector) → gather into collection.
- reduce(identity, accumulator) → combine into one result.
- count(), min(), max().
-  findFirst(), findAny().
- anyMatch(), allMatch(), noneMatch().

#  Primitive Streams
-  specialized streams for primitive types to avoid boxing/unboxing overhead
    - IntStream for int
    - LongStream for long
    -  DoubleStream for double
- These streams have extra methods like sum(), average(), and range operations.
###  Why Use Primitive Streams?
- Why Use Primitive Streams?
- Provide numeric operations directly.
###  Exam Traps
- average() returns OptionalDouble, not double.
- range() excludes upper bound; rangeClosed() includes it.
- Primitive streams cannot use Collectors directly; need to box first.

# Advanced Stream Pipeline
### map()
- map transforms each item into one new item.
```java
List<String> names = List.of("Alice", "Bob", "Charlie");
List<Integer> lengths = names.stream()
    .map(String::length)           // String -> Integer
    .toList();
```
```java
record User(String name, String email) {}
List<User> users = List.of(new User("Alice","a@x.com"), new User("Bob","b@x.com"));
List<String> emails = users.stream()
    .map(User::email)              // User -> String
    .toList();
```
### flatMap()
- Converts each element into a stream and flattens the result.
- transforms each item into a stream of items, then concatenates all those streams into a single stream.
```java
//  Stream of Lists → flatten to elements
List<List<Integer>> nested = List.of(List.of(1,2), List.of(3,4));
List<Integer> flat = nested.stream()
    .flatMap(List::stream)         // List<Integer> -> Stream<Integer>
    .toList();
```
```java
//  Split strings to words
List<String> sentences = List.of("hello world", "hi there");
List<String> words = sentences.stream()
    .flatMap(s -> Arrays.stream(s.split("\\s+")))
    .toList();
```
```java
// Optional flattening (Java 9+ has Optional.stream())
Optional<String> maybeEmail = Optional.of("a@x.com");
List<String> emails = Stream.of(maybeEmail, Optional.empty())
    .flatMap(Optional::stream)     // Optional<String> -> Stream<String> (0 or 1)
    .toList();
```
```java
// Map<K, List> → all values
Map<String, List<String>> tags = Map.of(
    "doc1", List.of("java", "streams"),
    "doc2", List.of("concurrency")
);
List<String> allTags = tags.values().stream()
    .flatMap(List::stream)
    .toList();
```
### peek()
- For debugging/logging without modifying the stream.
```java
Stream.of("Tom", "Jerry")
      .peek(System.out::println)
      .map(String::toUpperCase)
```
### limit() and skip()
- Control size of stream.
```java
Stream.iterate(1, n -> n + 1)
      .limit(5) // limit n elementss
      .skip(2) // discarding the first n elements of the stream
```
### Infinite Streams
- Created by generate() or iterate().
-  Must use limit() to avoid infinite processing.
### Short-Circuiting
- findFirst(), anyMatch(), allMatch(), noneMatch() stop early when condition is met.


