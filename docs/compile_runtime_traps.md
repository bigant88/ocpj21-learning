# OCP Java 21 (1Z0-830)
## 20 Compile-Time Traps & 20 Runtime Traps
# 🔥 20 Compile-Time Traps
## I. Class & Inheritance
1. Instance method cannot override static method.
2. Cannot reduce visibility when overriding.
3. Cannot throw broader checked exception in override.
4. Constructor cannot call both `this()` and `super()`.
5. Superclass without no-arg constructor requires explicit super(...).
6. Abstract method cannot have body (unless default/private in interface).
7. Sealed class must declare valid `permits`.
8. Subclass of sealed class must be `final`, `sealed`, or `non-sealed`.
## II. Records
9. Compact constructor cannot assign fields explicitly.
10. Record components are implicitly private final.
11. Record cannot extend another class.
## III. Interfaces
12. Interface method with body must be default/static/private.
13. Functional interface must have exactly one abstract method.
14. Lambda cannot throw unchecked checked exceptions.
## IV. Access Modifiers
15. Protected member in different package not accessible via object reference.
16. Default access not visible outside package.
## V. Generics & Types
17. Primitive types not allowed in generics (e.g., List<int>).
18. Generic type mismatch in assignment.
19. Comparator must implement compare(T o1, T o2).
## VI. Switch & Flow
20. Switch expression must be exhaustive and use yield properly.

# 🚀 20 Runtime Traps (Compiles but Wrong Output)
## I. Inheritance & Polymorphism
1. Constructor calling overridden method uses default subclass field values.
2. Fields are not polymorphic (reference type decides).
3. Static methods are hidden, not overridden.
4. Super constructor runs before subclass field initialization.
## II. String & Immutability
5. new String() creates separate heap object.
6. == compares references, not content.
7. String methods return new objects (immutable).
8. StringBuilder is mutable.
## III. Operators & Autoboxing
9. `i = i++` does not increment as expected.
10. Short-circuit operators may skip evaluation.
11. Integer caching (-128 to 127) affects ==.
12. Unboxing null causes NullPointerException.
## IV. Collections
13. Arrays.asList() returns fixed-size list.
14. List.of() returns immutable list.
15. TreeSet requires Comparable or Comparator.
16. Modifying collection during iteration causes ConcurrentModificationException.
## V. Streams
17. Stream cannot be reused after terminal operation.
18. Parallel stream may not preserve order.
## VI. Exceptions & Misc
19. finally block always executes (except System.exit).
20. Return in finally overrides return in try.
# 🧠 Quick Mental Checklist
* Visibility rules
* Override vs hide
* Constructor execution order
* Immutability behavior
* Autoboxing & null safety
* Collection mutability
* Stream lifecycle
