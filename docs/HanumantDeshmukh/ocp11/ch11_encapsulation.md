# 11.1 Apply access modifiers
## 11.1.1 Accessibility
- Access modifiers help enforce encapsulation, protect internal logic, and define a clear public contract between the class and its users
### 11.1.2 Access modifiers
| Access Modifier | Same Class | Same Package | Subclass (Different Package) | Other Classes |
|-----------------|------------|--------------|------------------------------|----------------|
| `private`       | ✅         | ❌           | ❌                           | ❌             |
| `default`       | ✅         | ✅           | ❌                           | ❌             |
| `protected`     | ✅         | ✅           | ✅                           | ❌             |
| `public`        | ✅         | ✅           | ✅                           | ✅             |
### 11.1.3 Understanding protected access
- A protected member can be accessed:
    - by classes in the same package
    - by subclasses in diff package
- Common confusion: a subclass can access a protected member only through its own inherited reference, not through a reference of the superclass

--> 🧠 Key Concept: "Ownership through Inheritance"
```java
// File: Account.java
package com.mybank.accts;
public class Account {
    protected String acctId;
}
```
```java
// File: HRAccount.java
package com.mybank.hr;
public class HRAccount extends Account {
    public static void main(String[] args) {
        Account simpleAcct = new Account();
        simpleAcct.acctId = "111"; // ❌ Will NOT compile

        HRAccount hrAcct = new HRAccount();
        hrAcct.acctId = "111";     // ✅ Will compile
    }
}
```
🎯 Why This Matters: The _protected_ modifier is designed to:
- Allow subclasses to reuse and extend functionality
- Prevent subclasses from interfering with the internal state of the superclass
- Maintain encapsulation while supporting inheritance
### 11.1.4 Applying access modifiers to types
#### 🧱 Access Modifiers on Class/Interfaces Definitions
| Type of Class             | Allowed Access Modifiers                     |
|---------------------------|-----------------------------------------------|
| Top-level class/interface | `public`, *default* (no modifier)             |
| Nested class/interface    | `public`, `protected`, `private`, *default*   |
```java
public class Outer {
    private class Inner1 { }     // valid
    protected class Inner2 { }   // valid
}
```
#### 📐 Access Modifiers for Interface Members
- all interface members are implicitly public
- if declare them as private or protected -> compile error
- if no modifier is specified -> compiler treats them as public
- Since Java 9, methods of interface can be private
## 11.2 Apply encapsulation principles to a class
### 11.2.1 Encapsulation
- 📌 Why Encapsulation Matters:
    - Improve maintainability: users interact with methods, not internal variables -> internal implementation can change
    - Protect data integrity: set value for variables through methods
- 🧱 How Java Supports Encapsulation:
    - instance variables should be private
    - public methods (getters/setter) expose controlled access
    - the more open the variable, the less encapsulated the class is
    - a class with public fields is not encapsulated
