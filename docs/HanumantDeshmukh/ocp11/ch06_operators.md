# 6 Operators
## 6.1.2 Expressions and Statements
- an expression has a value, A statement is a complete line of code that may or may not have any value of its own.
```java
int a = 10;
int b = 20;
a + b; //this line will not compile - not a statement
```
-  a = b = c = d; is valid.
## 6.1.3 Post and Pre Unary Increment/Decrement Operators
- the postfix operator returns the existing value of the variable
- prefix operator returns the updated value of the variable
```java
int i = 1, post = 0, pre = 0;
post = i++;
System.out.println(i+", "+post); //prints 2, 1
i = 1; //resetting to i back to 1.
pre = ++i;
System.out.println(i+", "+pre); //prints 2, 2
```
## 6.1.4 String concatenation using + and += operators
- To trigger the String concatenating behavior of the + operator, the declared type of at least one of its operands must be a String

## 6.1.5 Numeric promotion and casting
```java
byte b = 1;
short s = -b;
System.out.println(b);
//error: incompatible types: possible lossy conversion from int to short
```
- **Numeric Promotion Rules**:
    - Unary: byte, short, char → int when using unary operators (like -, +, ~)
    - Binary: Both operands promoted to int unless one is long, float, or double. (like +, -, *, &)
    - Result Type: The result is never smaller than int (except for compound assignments and increment/decrement).

```java
byte b = 1;
short s = -b; // ERROR: -b is promoted to int, assigning int to short needs a cast
short s = (short) -b; // Explicit cast required
```
- **Constants Exception**:
    - If all operands are compile-time constants and the result fits in the target type, Java allows the assignment without casting.
```java
byte b = 200 - 100; // OK, both are constants and result fits in byte
final int I = 10;
byte b = I + 2; // OK, I is final and result fits in byte
```
-   **Exceptions: Compound Assignment and ++/-- Operators**: Compound assignments (+=, -=, *=, etc.) and increment/decrement (++, --) automatically cast the result back to the target variable’s type. No explicit cast needed.
```java
byte b1 = 1;
byte b2 = ++b1; // OK
b2 = b1--;      // OK
b1 *= b2;       // OK
float f = 2.0f;
double d = 1.0;
f += d;         // OK, result is cast to float
```
- **Key Takeaway:** Always be aware of numeric promotion when working with small types like byte or short. Use explicit casting when necessary, except in the cases of compound assignments or increment/decrement operators
- Why Java Allows This Exception:
    - Convenience and Readability: Compound assignments (+=, -=, etc.) and increment/decrement (++, --) are very common in everyday programming. Requiring explicit casts every time would make code verbose and harder to read.
    - Safe by Design: The result is always assigned back to the same type. The compiler knows the target type and can implicitly cast without risk of ambiguity.
```java
byte a = 10;
a += 5;   // ✅ No error
a++;      // ✅ No error
a = (byte)(a + 1); // ❌ Verbose
a++;               // ✅ Cleaner

```

## 6.1.6 Operator precedence and evaluation of expressions
- Java Operator Precedence Table (from highest to lowest):
```java
Member and array access: . and []
Cast: ()
Postfix: expr++, expr--
Unary: ++expr, --expr, +expr, -expr, ~, !
Multiplicative: *, /, %
Additive: +, -
Shift: <<, >>, >>>
Relational: <, >, <=, >=, instanceof
Equality: ==, !=
Bitwise AND: &
Bitwise XOR: ^
Bitwise OR: |
Logical AND: &&
Logical
```