# Chapter 10 Creating and Using Methods
## 10.2 Create overloaded methods
### 10.2.1 Method signature
- A method signature is a unique identifier for a method within a class
- Consist of: method name & the ordered list of parameter types
- Can not declare 2 methods have the same signature in a class
### 10.2.2 Method overload
- a class has multiple methods with the same name but different parameter lists
### 10.2.3 Method selection
1. Exact match: Pick method with parameter types exactly matching arguments
2. Most specific: Pick closest match (subtype for objects, narrower for primitives)
    - For object types: A subclass (more specific) is chosen over a superclass.
    - For primitives, Java defines a subtype hierarchy: double > float > long > int > char || int > short > byte
3. Widening before autoboxing:  Prefer primitive widening over autoboxing
4. Autoboxing before varargs: Prefer autoboxing over matching varargs
5. No match:    Compilation error if no method can match the arguments
```java
//1. Exact match:   
void processData(Object obj) { }
void processData(String str) { }
processData("hello"); // "hello" is a String, so processData(String) is chosen.
//2. Most specific:
void processData(Object obj) { }
void processData(CharSequence str) { }
processData("hello"); // String is closer to CharSequence than Object, so processData(CharSequence) is chosen.
void processData(int value) { }
void processData(short value) { }
processData((byte) 10); // short is more specific than int, so processData(short) is chosen.
// 3. Widening before autoboxing:
void processData(short value) { }
void processData(Byte value) { }
processData((byte) b); // (byte) can be widened to short, so processData(short) is chosen.
//4. Autoboxing before varargs:
void processData(int... values) { }
void processData(Integer value) { }
processData(10); // 10 can be autoboxed to Integer, so processData(Integer) is chosen.
```
## 10.3 Passing object references and primitive values into methods
- Key Concept: Java is Always Pass-by-Value
- pass a primitive type -> Java copies the actual value
- pass an object reference -> Java copies the reference (not the object) -> both the original and the method parameter point to the same object
- Changes to the object inside the method affect to the original object
- But if you re-assign the reference inside the method, it does not affect the original reference
- Returning Values in Java Methods: Java uses pass-by-value for return values too, it returns a copy of the value

| Type        | Passed to Method       | Can Modify Original?                          |
|-------------|------------------------|-----------------------------------------------|
| Primitive   | Value                  | ❌ No                                          |
| Object Ref  | Reference (by value)   | ✅ Yes (object fields), ❌ No (reference itself) |

## 10.4 Create and overload constructors
### 10.4.1 Creating instance initializers
🔍 What Happens When You Create an Object in Java?
1. Class Initialization – Loads the class if it hasn’t been loaded yet.
2. Memory Allocation – Allocates space for instance variables.
3. Default Initialization – Sets default values (0, false, null, etc.).
4. Custom Initialization – Runs instance initializer blocks and constructors.

🧱 What Is an Instance Initializer?
- is a block of code inside a class that runs every time an object is created, before constructor
- can access all instance & static member (default value)
- cannot use the value of a variable declared below the initializer (forward reference rule)
- allowed to call method
```java
class TestClass {
    {
        System.out.print(i); // ❌ Compile error: invalid forward reference
        i = 20;              // ✅ Allowed: assigning is okay
    }

    int i = 10;
}
```
```java
class TestClass {
    {
        printI(); // ✅ Valid
    }

    void printI() {
        System.out.print(i); // prints 0 (default value before initialization)
    }

    int i = 10;
}
```
### 10.4.2 Creating constructors
🧱 Default Constructor
- if you don't write any constructor -> the compiler adds a default no-args constructor. But if there is any constructor -> not add a default one
```java
class Account {
    // Compiler adds: Account() { }
}
```
- constructor is thread-safe
### 10.4.3 Overloading constructors
⚠️ Rules for this():
- must be the **first line** in the constructor
- can only be used **once** per constructor

⚠️ What is super()?
- refer to its superclass.
- user to call a superclass constructor: super(..)
- access super class methods or fields

⚠️ Rules for super()
- super(..) must be the first line in a constructor
- if you don't explicitly call super(..), Java will insert a call to the **no-args constructor** of the superclass

🧠 When Does Java Insert super(..) automatically?
- Java auto inserts super(); at the first line of a subclass constructor **only if**:
    - the subclass doesn't explicitly call another constructor (this(..) or super(..) )
    - the superclass has a no-args constructor (either defined by you or provided by the compiler)

| Superclass Constructor   | Subclass Constructor     | Java Inserts `super();`? | Compiles? |
|--------------------------|--------------------------|---------------------------|-----------|
| No-args                  | No explicit `super()`    | ✅ Yes                    | ✅ Yes    |
| Parameterized only       | No explicit `super()`    | ❌ No                     | ❌ No     |
| Parameterized only       | Explicit `super(...)`    | ✅ You wrote it           | ✅ Yes    |

### 10.4.4 Instance initializers vs constructors
| Feature                 | Instance Initializer                                           | Constructor                                      |
|-------------------------|---------------------------------------------------------------|--------------------------------------------------|
| **Purpose**             | Initialize instance variables                                  | Initialize instance variables                    |
| **Execution**           | All initializer blocks are executed (in order)                | Only one constructor is executed (based on arguments) |
| **Chaining**            | No explicit chaining                                           | Can chain using `this(...)`                      |
| **Forward Reference Rule** | Cannot use variables declared below (can assign)           | No restriction                                   |
| **Exception Handling**  | Must complete normally (no guaranteed exceptions)             | Can throw exceptions freely                      |
| **Best Practice**       | Use sparingly; can make code harder to read                   | Preferred for initialization logic               |

### 10.4.5 final variables revisited
🔍 Final Variables: Explicit Initialization Rules
- static final variables be initialized before class loading complete, they are accessible as soon as the class is loaded
- Static Final variables: must be initialized at declaration or in one static initializer block
```java
static final int VALUE = 10;
```
```java
static final int VALUE;
static {
    VALUE = 10;
}
```
- Instance final variables: must be initialized At declaration, Or In one instance initializer, or In every constructor.
- Local final variables (in methods): must be initialized before use

## 10.5 Apply the static keyword to methods and fields
### 10.5.1 Apply the static keyword to methods and fields
🔍 Key Concepts: static member belongs to the class itself

✅ Where static can be used:
- Fields (variables): static int bar;
- Methods: static void baz() { }
- Nested types: static class NestedClass { }

✅ Where static cannot be used:
- Top-level classes: Cannot be static because they are not owned by another class.
- Local variables or classes inside methods: Cannot be static because they are not class members.

Modifier Order Doesn’t Matter:
```java
static public final void boz() { }
public static final void boz() { } // same meaning
```
###  10.5.2 Accessing Static Members in Java
- Don't need an object to access a static member, just use class name
- Accessing Static Members — Standard Way:
```java
class TestClass {
    public static void main(String[] args) {
        System.out.println(Foo.bar); // prints 0 because it's an int and defaults to 0.
        System.out.println(Foo.biz); // prints null because it's a String and defaults to null.
        Boo.NestedClass nc = new Boo.NestedClass();
    }
}
```
-  Accessing Static Members via a Reference:
```java
class TestClass {
    public static void main(String[] args) {
        Foo f = null;
        System.out.println(f.bar); // prints Foo's bar
        //because bar is static, the compiler translates f.bar to Foo.bar.
    }
}
```
- Static Binding (Compile-Time Binding)
```java
class TestClass {
    public static void main(String[] args) {
        Foo f = null;
        f.boz(); // No NullPointerException
        //boz() is a static method, The compiler binds f.boz() to Foo.boz() at compile time.
    }
}
```
### 10.5.3 Accessing Static Members from the Same Class
- Static members can be accessed:
    - Directly from other static members of the same class
    - Directly from instance members of the same class
    - Using the class name from outside the class
    - Using an object reference (not recommended)
```java
class InstanceCounter {
    static int count;

    InstanceCounter() {
        // Accessing static variable directly from constructor
        count++;
    }

    static void printCount() {
        // Accessing static variable directly from static method
        System.out.println(count);
    }

    void reduceCount() {
        // Accessing static variable directly from instance method
        count--;
    }
}

```
### 10.5.5 Accessing Instance Members from a Static Method
- A static method belongs to the class, not to any instance
- An instance method belongs to a specific object and has access to the implicit _this_ reference
- Because static methods are not tied to any instance, they **cannot use _this_** and **cannot directly access** to instance members
```java
class Book {
    int name;
    static void printName1() {
        System.out.println(this.name); // ❌ Error: Cannot use 'this' in static context
        System.out.println(name);      // ❌ Error: Cannot access instance variable directly
    }
}
```
### 10.5.6 Class Loading and Static Initializers
- Before main() run:
    - the JVM loads the class using a class loader
    - it initializes all static fields to their default values
    - it executes static initializer blocks (if any)
    - call main() method
- Static initializer Block: runs once when the class is loaded. You cannot call a static block manually
```java
class TestClass {
    static {
        System.out.println("Print first");
    }

    public static void main(String[] args) {
        System.out.println("print later");
    }
}
```
- Rules of static Blocks:
    - Static blocks can access all static variables and static methods
    - cannot read a static variable declared after the block (only can assign to it)
```java
class TestClass {
    static int a;
    static {
        System.out.println(a); // ✅ OK
        // System.out.println(b); // ❌ Error
        b = 10; // ✅ OK
        m(); // ✅ OK
    }
    static void m() {
        System.out.println(b); // ✅ OK
    }
    static int b;
    static {
        System.out.println(b); // ✅ OK
    }
}
```