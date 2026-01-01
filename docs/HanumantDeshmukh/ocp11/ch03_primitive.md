
# Chapter 3 Java Primitive Data Types
- Exam Objectives: Declare and initialize variables (including casting and promoting primitive data types)
## 3.1 Data types
- Java is statically-typed language: type defined at compile time and cannot change at run time

| Feature           | Primitive Data Type            | Reference Data Type       |
 |-------------------|--------------------------------|-------------------------------|
| Example           | int, char, boolean             | String, arrays, objects       |
| Stores            | Actual value                   | Reference to object           |
| Memory Allocation | Stack                          | Stack (ref) + Heap (object)   |
| Default Value     | Type-specific (0, false, etc.) | null                          |
| Mutable?          | Immutable                      | Depends on class definition   |
| Methods           | No                             | Yes                           |
| Comparison (==)   | Value                          | Reference                     |
| Size              | depends on size of primitive data                          | store only the address of a memory location -> depends on system 32 bits or 64 bits                     |


- char stores numbers but cannot store a negative number. The number stored in a char variable is interpreted as a unicode character
- Types of variables: primitive variable vs reference variables.
- Size of reference data types: look at the instance variables defined in that class/enum

## 3.2 Difference between reference variables and primitive variables
- Java uses "pass by value" (not pass by reference)- whenever assign one variable to another, the JVM copy the value contained in the variable on the right-hand side to the left
- Important concept: a variable , be it of any kind, contains just a simple raw number. Assignment is copying that number from one variable to another. It's the JVM's job to interpret what that number means based on the type of the variable
## 3.3 Declare and initialize variables
### 3.3.1 Declare and initialize variables
- declarations without initialization.
```java
   int x;
   String str;
   Object obj;
   int a, b, c; //a, b, and c are declared to be of type int
   String s1, s2; //s1 and s2 are declared to be type String
```
- declare as well as initialize at the same time:
```java
1. int x = 10; //initializing x using an int literal 10
2. int y = x; //initializing y by assigning the value of another variable x
3. String str = "123"; //initializing str by creating a new String
4. SomeClass obj = new SomeClass(); //initializing obj by creating a new instance of SomeClass
5. Object obj2 = obj; //initializing obj2 using another reference
6. int a = 10, b = 20, c = 30; //initializing each variable of same type with a different value
7. String s1 = "123", s2 = "hello";
8. int m = 20; int p = m = 10; //resetting m to 10 and using the new value of m to initialize p
```
- Mixing the two styles
```java
1. int a, b = 10, c = 20; //a is declared but not initialized. b and c are being declared as well as initialized
2. String s1 = "123", s2; //Only s1 is being initialized
```
- Illegal
```java
1. int a = 10, int b; //You can have only one type name in one statement.
2. int a, Object b; //You can have only one type name in one statement.
3. int x = y = 10; //Invalid, y must be defined before using it to initialize x.
```
### 3.3.2 Uninitialized variables and Default values
- if don't use -> don't need to initialize
- don't need to initialize static & instance variable (default value)
- local variables have to initialized before use, it not -> not compile
- principle of "definite assignment": a local variable must have a definitely assigned value when any access of its value occurs
- If at compile time, the compiler notices one execution path in which the local variable will remain uninitialized before accessed -> compile time error

### 3.3.3 Assigning values to variables
- Casting: assigning value of one type to a variable of another type
- Casting: a promise that the actual value held by the source variable at tun time will fit into the target variable
- Primitive assignment:
- (implicit) widening conversion: assign a smaller type to a larger type
- implicit narrowing: đổ nước từ can to hơn sang can nhỏ hơn mà vẫn chứa đủ. Assign a larger source type to smaller target
- explicit narrowing: a cast tells the compiler to just assign the value and not worry about any slipage

```java
 //(implicit) widening conversion: assign a smaller type to a larger type (no need cast keyword)
   byte b = 10; //b is 8 bits
 char c = 'x'; //c is 16 bits
 short s = 300; //c is 16 bits
 int i; //i is 32 bits
 long l; //l is 64 bits
 float f; //f is 32 bits
   double d; //d is 64 bits
   //no special care is needed for any of the assignments below
   i = b;
   i = s;
   l = i;
   f = i;
   d = f;
```
```java
//implicit narrowing
final int i = 10; byte b = i; //compile because i is a compile time constant
```
```java
//explicit narrowing
int i = 10;
char c = (char) i; //explicitly casting i to char
```
- Flowchart:
```shell
         +------------------------------+
         | Start: Assign value to       |
         |        variable              |
         +------------------------------+
                      |
                      v
         +-------------------------------+
         | Is source type same as target |
         | type?                         |
         +-------------------------------+
           | Yes                 | No
           v                     v
  +-------------------+   +-----------------------------+
  | Direct assignment |   | Is source type smaller than  |
  +-------------------+   | target type?                |
                           +-----------------------------+
                              | Yes               | No
                              v                   v
               +--------------------------+   +-------------------------------+
               | Implicit widening        |   | Is source value a compile-time|
               | (safe, no cast needed)   |   | constant AND fits in target   |
               +--------------------------+   | type's range?                 |
                                              +-------------------------------+
                                                  | Yes        | No
                                                  v            v
                                     +-------------------+  +-------------------+
                                     | Implicit narrowing|  | Is explicit cast  |
                                     | (allowed)         |  | provided?         |
                                     +-------------------+  +-------------------+
                                                         | Yes        | No
                                                         v            v
                                            +-------------------+ +-------------------+
                                            | Explicit narrowing| | Compilation error |
                                            | (cast required)   | | or warning        |
                                            +-------------------+ +-------------------+
```

- Assigning short or byte to char:
- short & char are 16 bytes, but short is signed, char is unsigned. A char can store 0 -> 65535, a short can store -32768 -> 32767.
- byte is 1 byte but has negative values

| Statement                      | Compiles? | Reason                                                       |
 |--------------------------------|-----------|--------------------------------------------------------------|
| `short s1 = '\u0061';`         | Yes       | Constant fits in short                                       |
| `short s2 = c1;`               | No        | Not a constant, narrowing requires cast                      |
| `char c2 = '\uFEF0';`          | Yes       | Constant fits in char                                        |
| `short s2 = '\uFEF0';`         | No        | Constant out of short range                                  |
| `short s3 = (short) '\uFEF0';` | Yes       | Explicit cast, narrowing allowed                             |
| `char c3 = 1;`                 | Yes       | Constant, fits in char                                       |
| `char c4 = -1;`                | No        | Out of char range                                            |
| `short s4 = -1;`               | Yes       | Fits in short                                                |
| `char c5 = (char) s4;`         | Yes       | Explicit cast, allowed (but may give unexpected value)       |

- Assigning float to int or double to long and vice-versa
- int & long:  Store exact (precise) integer values, float and double: Store floating-point numbers
- Widening (int→float, long→double): Allowed, may lose precision, no cast needed.
- Narrowing (float→int, double→long): Not allowed without explicit cast, may lose precision and fractional part.

| Statement                        | Compiles? | Reason                                                        |
|-----------------------------------|-----------|---------------------------------------------------------------|
| `int i = 2147483647;`             | Yes       | Assigning int value                                           |
| `float f = i;`                    | Yes       | Implicit widening (int to float), may lose precision          |
| `long g = 9223372036854775807L;`  | Yes       | Assigning long value                                          |
| `double d = g;`                   | Yes       | Implicit widening (long to double), may lose precision        |
| `i = f;`                          | No        | Implicit narrowing (float to int) not allowed                 |
| `g = d;`                          | No        | Implicit narrowing (double to long) not allowed               |
| `i = (int) f;`                    | Yes       | Explicit cast, narrowing allowed, may lose information        |
| `g = (long) d;`                   | Yes       | Explicit cast, narrowing allowed, may lose information        |

## 3.4 Wrapper Classes
### Why Do We Need Wrapper Classes?
- Primitives are stored on the stack, directly manipulated, and do not have any methods or behavior.
- Objects are stored on the heap, accessed via references, and come with methods and behaviors
- Problems with Primitives and Objects:
- Collections (like ArrayList) can only store objects, not primitives.
- Methods that accept Object parameters cannot take primitives directly.
- Primitives cannot be used where objects are required.
### Auto-boxing
- auto converts (boxes) a primitive value into its wrapper object

| Feature      | Before Java 1.5                      | After Java 1.5 (Autoboxing)            |
 |--------------|--------------------------------------|----------------------------------------|
| Manual wrap  | `Integer i = Integer.valueOf(100);`  | `Integer i = 100;`                     |
| Collections  | `myList.add(Integer.valueOf(100));`  | `myList.add(100);`                     |
| Object cache | New object (if using constructor)     | Cached object (if value in cache range)|

### Unboxing
- assign wrapper objects to primitives, provided the target type is wide enough.
- Wrapper classes also offer parsing methods to convert strings to primitives, with error handling for invalid strings.
```java
Integer i1 = 10;   // Autoboxing: int 10 ➔ Integer object
int i2 = i1;       // Unboxing: Integer i1 ➔ int i2
```
- unboxing in method call:
```java
void printInt(int x) { System.out.println(x); }
Integer i = 99;
printInt(i); // Unboxing: Integer ➔ int
```
-  Type Compatibility: Unboxing only works if the primitive type on the left side is wide enough to hold the value of the wrapper:
```java
Integer i1 = 10;
byte b = i1;   // NOT allowed, compile error!
float f = i1;  // Allowed, float is wide enough for any int
```
- Wrapper Utility Methods: Parsing Strings
```java
Integer.parseInt(String s); // returns an int
int n = Integer.parseInt("123");  // parses "123" to int 123
Integer.parseInt("abc");  // Throws NumberFormatException
```