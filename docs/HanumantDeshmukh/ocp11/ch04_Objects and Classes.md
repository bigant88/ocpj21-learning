# Chapter 4 Describing and Using Objects and Classes
## 4.4 Identify the scope of variables
- visibility scopes for variables: class, method, and block
- lifespan scopes for variables : class, instance, method, for loop, and block.
- Rules about different scopes overlap: 
  - Same name in same scope = NOT allowed
  - Same name in different scopes (class & method, method & loop) = Allowed
  - Cannot redeclare a variable in an inner block if it exists in an outer block of the same method.
- Variable Shadowing: 
  - Shadowing happens when local/parameter variable has same name as field.
  - Local variable takes precedence within the method/block.
  - To access instance field: use this.fieldName.
  - To access static field: use ClassName.fieldName.
  - Watch out for logical bugs—compiler won’t warn you!
## 4.5 Use local variable type inference - LVTI
- use for LOCAL VARIABLES ONLY (Inside methods, constructors, or initializer blocks)

| **Usage**                   | **Valid/Invalid** | **Example**                                 |
|----------------------------|-------------------|---------------------------------------------|
| Local variable in method   | Valid             | `var i = 1;`                                 |
| Class/instance field       | Invalid           | `var value = 1;` *(outside a method/block)* |
| Method parameter           | Invalid           | `public void test(var x)`                   |
| Method return type         | Invalid           | `public var getValue() { ... }`             |
| Uninitialized variable     | Invalid           | `var x;`                                     |
| Assigning null             | Invalid           | `var n = null;`                              |
| Ambiguous array            | Invalid           | `var arr = {1, 2};`                          |

## 4.6 Explain objects' lifecycles
- **Creation**: Objects are always created in heap memory using new, deserialization, or cloning
- **References**: Objects are accessed through references. As long as at least one reference points to an object, it stays alive.
- **Garbage Collection**: When no active part of the program references an object, it becomes “garbage.”