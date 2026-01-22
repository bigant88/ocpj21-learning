## 00 - General
- String is final -> can't extend.
- All primitive wrappers are also final (Boolean, Character, Short, Integer, Byte ...)
- java.lang.System is final
- StringBuilder, StringBuffer is final
- Number is not final. Integer, Long, Double extends Number
- All the classes of package java.time are immutable and therefore thread-safe


## 02 - Operators
- Casting: smaller to bigger not need casting, bigger to smaller need casting
## 04 Strings - Arrays
- Can create arrays of any type with length zero
- Size of the array is NEVER specified on the Left Hand Side.
- trim() is not method of StringBuilder (of String)
## 05 - Methods
- autoboxing: primitive types only
- default values of primitive types: all numeric types, including char, get the value of 0 (or 0.0 for float and double)
- print a char -> print the character. If value of a char is 0 -> print blank
-

## 06 Class Design - Override/Polymorphism
- If a subclass does not have any declared constructors, the implicit default constructor of the subclass will have a call to super( ).
- You can either call super(<appropriate list of arguments>) or this(<appropriate list of arguments>) but not both from a constructor.
- default constructor: take no argument
### 
- Which variable (or static method) will be used depends on the class that the variable is declared of.
-  Which instance method will be used depends on the actual class of the object that is referenced by the variable
```java
public class TestClass{
   public static void main(String args[ ] ){
      A o1 = new C( );
      B o2 = (B) o1;
      System.out.println(o1.m1( ) );
      System.out.println(o2.i );
   }
}
class A { int i = 10;  int m1( ) { return i; } }
class B extends A { int i = 20;  int m1() { return i; } }
class C extends B { int i = 30;  int m1() { return i; } }
// It will print 30, 20.
```

## 07 Abstract - Interface
- Nested classes - Learn inner class again
- Keyword implements: class implement interface. Keyword extends: interface extends interface, class extends class
- Review interface default method/multiple inheritance
- A functional interface is an interface that contains exactly one abstract method. It may contain zero or more default methods and/or static methods
## 08 Lambda function & Stream
- If you have a stream of primitive elements (int, long, double) => Instead of using Stream<Integer>, Stream<Long>, Stream<Double> => use IntStream, LongStream, DoubleStream and  java.util.function.IntFunction to avoid cost of autoboxing/unboxing

## 09 Collections
- collection interfaces of the Java standard class:
    - Some operations may throw an UnsupportedOperationException: For example, TreeSet implements SequencedSet but since it keeps its elements sorted, its addFirst/addLast method throw UnsupportedOperationException.
    - The standard JDK provides thread safe collection classes such as CopyOnWriteArrayList and ConcurrentMap. It also provides Collections.synchronizedXXX methods that provides thread safe wrappers over regular collection classes. Further, although Vector and Hashtable have been out of favor for quite a while now, they are thread safe!
- There is no interface like OrderSet, OrderedList
- A Set: without any order or sorting. SortedSet is interface, TreeSet is concrete class that implements SortedSet
- Calling addFirst/addLast on TreeSet throws UnsupportedOperationException. Because can not addFirst/addLast to a sorted Set
- A NavigableSet keeps the elements sorted. Because NavigableSet extends SortedSet
- TreeMap, HashMap is concrete class of Map interface. SortedMap is an interface
- two-dimensional array is basically an array of arrays.
- LinkedHashMap & TreeMap: return the elements in a same order after iterating

## 11 Exceptions & Localization
- language is required to construct a Locale
- Exam Tip:
    - finally always executes, even if return is in try or catch.

## 12 Modules

## 13 Concurrency
- Understand and differentiate among Deadlock, Starvation, and Livelock
    - Deadlock: blocked forever
    - Startvation: one thread can not start - a "greedy" thread
    - Livelock: a thread depend on another thread - unable to make further progress. The threads are not blocked - too busy responding to each other to resume work


## 14 Files - IO
- System.in: open when the program started, can not be reassigned (final)