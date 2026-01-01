Chapter 12 Reusing Implementations Through Inheritance
# 12.1 Create and use subclasses and superclasses
## 12.1.1 Understanding Inheritance
### Inheritance of State
- Only classes can have state
- A class can extend another class to inherit its state
### Inheritance of Implementation
- Since Java 8, Interfaces can also have default method implementations
- A class can inherit implementation from: one class and/or multiple interfaces
### Inheritance of Type:
- A class or interface defines a type -> extend a class or implement interfaces
## 12.1.2: Inheriting Features from a Class
- Inherit instance fields , instance method, static fields, static methods
- Not inherit: constructors, static & instance initializers
##  12.1.3: Inheritance and Access Modifiers
- Access modifiers determine what class members (fields/methods) are inherited and accessible in subclasses:

| Access Modifier | Inherited | Accessible in Subclass | Across Package |  
|-----------------|----------|----------------------|---------------|
| `private`       | ❌        | ❌                    | ❌             | 
| `default`       |✅(same package only)|✅(same package only)  | ❌    | 
| `protected`     | ✅        | ✅                    | ✅  (if subclass)| 
| `public`        | ✅        | ✅                    | ✅             | 
## 12.1.4 Inheritance of instance members vs static members
- Instance members are copied and customized per object
- Static members are shared and accessed, not duplicated
- Instance methods support polymorphism via overriding
- Static methods do not support polymorphism; they can only be hidden
```java
public class Person {
    public String name;
    public static int personCount;
}
public class Employee extends Person {
    // Inherits both instance and static fields
}
class TestClass {
    public static void main(String[] args) {
        Person p = new Person();
        Employee e = new Employee();
        p.name = "Amy";
        e.name = "Betty";
        System.out.print(p.name + " "); //Amy
        System.out.println(e.name); //Betty

        Employee.personCount = 2;
        System.out.print(Person.personCount + " "); //2
        System.out.println(Employee.personCount); //2
    }
}
```
### Extra: Method hiding
- is when a subclass defines a static method with the same signature as one in its superclass
- It's not polymorphic - the method that gets called depends on the **reference**, not the object
- 🧠 Key Differences: Overriding vs Hiding:

| Feature            | Overriding (Instance Method) | Hiding (Static Method)  |
|--------------------|------------------------------|-------------------------|
| Applies to         | Instance methods             | Static methods          |
| Polymorphism       | ✅ Yes                       | ❌ No                  |
| Method resolution  | Based on object type         | Based on reference type |
| Can be overridden? | ✅ Yes❌                    | No (can only be hidden) | 

```java
class Parent {
    static void greet() {
        System.out.println("Hello from Parent");
    }
}
class Child extends Parent {
    static void greet() {
        System.out.println("Hello from Child");
    }
}
public class Test {
    public static void main(String[] args) {
        Parent p = new Child();
        p.greet();  // Output: Hello from Parent
        Child c = new Child();
        c.greet();  // Output: Hello from Child
    }
}
```
## 12.1.5 Benefits of inheritance
- Code reuse
- Support information hiding
- Enables polymorphism
# 12.2 Using super and this to access objects and constructors
## 12.2.1  Object Initialization
1. Class Loading and initialization: JSM loads and initializes the class & superclass
2. Memory Allocation: all instance variables, include from superclass
3. Default Initialization: initialize all instance variables to default values
4. Custom initialization via Constructors and Initializers: superclass constructor -> subclass constructor ->
## 12.2.2 Initializing super class using "super"
- Object Initialization with Inheritance: superclasses are initialized first through constructor chaining, start from the root class (Object)
### Using super(< arguments>)
- A subclass constructor must call one of its superclass's constructor
- If not done explicitly, Java inserts a call to super(); automatically
- If superclass does not have a no-argument constructor, and the subclass relies on super() -> compile failed
- If a class does not have any constructor -> auto generate no-argument constructor
- Only one of super(<args>) or this(<args>) can be used in a constructor, and it must be the first statement
- If this(<args>) is used, it must lead to a constructor that call super(<args>)
- refer to ch12/ConstructorChain.java
## 12.2.3 Using the implicit variable "super"
- super() is only available in instance methods, can't use it in static methods or outside of method body
- no chaining like super.super
## 12.2.4 Order of initialization summarized
1. Static initializers and static fields of the superclass: execute once when the superclass is loaded
2. Static initializers and static fields of the subclass: execute once when the subclass is loaded
3. main() method
4. Instance initializers & instance fields of the superclass
5. Superclass constructor
6. Instance initializers & instance fields of the subclass
7. Subclass constructor

# 12.3 Create and extend abstract classes
## 12.3.1 Using abstract classes and abstract methods
- Why Use Abstract Methods? They allow uniform handling of different types
```java
for (Furniture f : allFurniture) {
    f.assemble(); //A FurnitureAssembler class can call assemble() on any Furniture object without knowing its exact type.
}
```
## 12.3.2: Using final classes and final methods
- When apply **final** to:
    - Variables -> value cannot change (constant)
    - Classes -> cannot be extended
    - Methods -> cannot be overridden
- Use **final** to protect class or method behavior from being changed
- Use **abstract** to allow subclasses to define specific behavior
- **Cannot** mix abstract and final in the same class or method
## 12.3.3: Valid combinations of access modifiers, abstract, final, and static
### Access Modifiers + Abstract:
- Private + Abstract → ❌ Invalid: Cannot override private abstract method
- Protected or Default + Abstract → ✅ Valid
### Private + Final ✅ Valid but redundant
- private methods are non-inheritable
### Abstract + Static  → ❌ Invalid
- static methods can't be overridden -> can't be abstract
### Final + Static  -> ✅ Valid
- static methods can be inherited but hidden, not overridden
### Rules
- abstract class may or may not have abstract methods
- concrete class cannot have abstract methods
- private methods are always final (implicitly), cannot be abstract
- static methods can be final but not abstract

# 12.4 Enable polymorphism by overriding methods
## 12.4.1 What is polymorphism
- the ability of an object to behave like multiple types - means "many forms" - especially its own class and its superclasses or interfaces
- Polymorphism works only with instance methods, not static methods
- Static methods belong to the class, not the object, so they don't participate in polymorphism
### Polymorphism and Inheritance
- Inheritance allows a class to reuse code from superclass. Polymorphism allows a subclass to change behaviour by overriding methods from the superclass
## 12.4.2 Overriding method
### ✅ Rules for Overriding Methods
- **Accessibility**: the overriding method cannot be less accessible than the overridden one
- **Return Type**: must be the same or subclass (called **covariant return**). For primitive types, it must match exactly.
- **Parameters**: match exactly in type and order
- **Throws clause**: the overriding method can throw fewer or narrower checked exceptions, not throw broader or new checked exceptions. No restriction on unchecked exceptions
- 🧠 Why These Rules Exist: there rules ensure that a subclass object can replace a superclass object without breaking existing code. This is the **substitutability principle**.
### 🧠 Tip to Remember when overriding
- widen accessibility
- narrow return type (covariant)
- match parameters exactly
- narrow or remove checked exceptions, not widen them
## 12.4.3 Invalid overrides
### Static vs Instance Method Conflict
- cannot override a static method with an instance method, or vice versa
- static methods are class-level, not object-level
- overriding only applies to instance methods
### Private Methods
- cannot override private methods
- can declare a method with the same signature in the subclass, but it's a new method, not an override
### Final methods
- cannot be overridden
- If you try to override a final method -> compile error
- this also applies to static final methods, they cannot be hidden
### Final Fields
- final fields can't be changed, but a subclass can declare a field with the same name
- this is not overriding, since fields are not inherited behavior

# 12.5 Utilize polymorphism to cast and call methods
## 12.5.1 Type of reference and type of an object
- Reference type: is the declared type of a variable -> determines what methods and properties you can access _at compile time_
- Object type: is the actual class of the object that the reference points to, which is determined _at runtime_
## 12.5.2 Bridging the gap between compile time and run time
- Compile time: the compiler knows only the declared type of variables and enforces type safety
- Run Time: The JVM knows the actual object type and executes polymorphic behavior
- Java must: prevent invalid method call (type safety), allows flexibility for polymorphism (dynamic behavior)
### is-a Test
- check parent-child relationship between types
```java
class Fruit { }
class Apple extends Fruit { }
class Mango extends Fruit { }

Apple a = new Apple();
Mango m = new Mango();

Fruit f1 = a; // ok, because Apple is a Fruit
m = a;        // will NOT compile because an apple is not a mango

Fruit f2 = m; // ok, because Mango is a Fruit
m = f1;       // will NOT compile because all fruits are not mangoes
m = f2;       // will NOT compile because all fruits are not mangoes
```
### Cast Operator
- programmer gives an explicit guarantee to the compiler
```java
        // Use casting to assure the compiler:
        m = (Mango) f2; // OK: Programmer guarantees f2 is Mango

       // Dangerous cast:
        m = (Mango) f1; // Compiles, but throws ClassCastException at runtime
```
### Casting a reference to an interface
- Why it’s tricky: A class can implement multiple interfaces, so the compiler cannot always know if a cast will succeed
- It assumes the cast might work because a subclass could implement that interface
- Example 1: Compile-time OK, Runtime Error
```java
interface Poisonous { }
class Fruit { }
class Mango extends Fruit { }
public class TestClass {
    public static void main(String[] args) {
        Fruit f = new Mango(); // OK: Mango is-a Fruit
        Poisonous p = (Poisonous) f; // Compiles, but throws ClassCastException
    }
}
```
- ✅ Example 2: Works with a subclass implementing the interface
```java
interface Poisonous { }
class Fruit { }
class StarFruit extends Fruit implements Poisonous { }
public class TestClass {
  public static void main(String[] args) {
    Fruit f = new StarFruit();
    Poisonous p = (Poisonous) f; // Compiles and runs fine
  }
}
```
- ❌ Special Case: Final Classes: If the declared class is final and does not implement the interface, the compiler reject the cast (Reason: A final class cannot have subclasses, so the cast is impossible.)
```java
interface Poisonous { }
public class TestFinal {
    public static void main(String[] args) {
        String s = new String(); // String is final
        Poisonous p = (Poisonous) s; // Compile-time error
    }
}
```
### Downcast vs Upcast

| Aspect       | Upcasting             | Downcasting            |
|-------------|------------------------|-------------------------|
| Direction   | Subclass → Superclass | Superclass → Subclass  |
| Cast Needed | No                    | Yes                    |
| Safe?       | Always                | Runtime check needed   |
| Example     | `Fruit f = new Apple();` | `Apple a = (Apple) f;` |

## 12.5.3 When is casting necessary
- Casting should be rare in well-designed code.
- Frequent casting indicates poor design because it bypasses type safety.
- Prefer polymorphism and interfaces over casting.
## 12.5.4 The instanceof operator
- instanceof does not give the exact type, only checks if the object is-a given type.
    - Example: f instanceof Fruit returns true even if f points to a Mango.
- Compiler allows instanceof only if the check is plausible.
    - f instanceof Mango ✅ (because Mango is-a Fruit)
    - f instanceof String ❌ (compile-time error)
-  Best Practice: Frequent use of instanceof indicates poor design.
## 12.5.5 Invoking Overridden Methods
- Best Practice: Avoid calling overridable methods from constructors.
##  12.5.6 Impact of polymorphism on == and equals method
- when using the == operator on references, it checks whether the two operands point to the same object in memory
```java
String s = "hello";
Integer n = 10;
System.out.println(n == s); // This code won't compile
//The compiler rejects the comparison because s and n are variables of two different unrelated types, making it impossible for them to point to the same object
```
```java
Object s = "hello";
Integer n = 10;
System.out.println(n == s); // This prints false
//The comparison is now valid because Object can point to any type,
```
# 12.6 Distinguish overloading, overriding, and hiding
| Feature        | Overloading        | Overriding           | Hiding                     |
|---------------|--------------------|----------------------|---------------------------|
| Applies to    | Same class methods | Instance methods     | Static methods & variables|
| Polymorphism  | Compile-time       | Runtime              | No polymorphism          |
| Signature     | Different params   | Same params          | Same name                |


