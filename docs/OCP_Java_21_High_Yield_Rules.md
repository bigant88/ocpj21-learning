# OCP Java 21 – High-Yield Rules Cheat Sheet
## 1. Building Blocks
- Text block phải xuống dòng sau """
- `\` trong text block loại bỏ line break
- Compile-time constant String → String Pool
- Runtime String → tạo object mới
- `intern()` → đưa String về pool
- Wrapper cache: Boolean, Byte, Character: \u0000 → \u007f, Short, Integer: -128 → 127
- `final` variable có thể assign muộn
- Unreachable code → compile-time error
## 2. Operators
- byte/short/char trong arithmetic → promote lên int
- short * short → int
- += -= *= auto cast về kiểu bên trái
- x = x + 1.0 ❌ nhưng x += 1.0 ✅
- Compile-time constant fit type → assign không cần cast
- ~x = -(x + 1)
- null instanceof T → false
- obj instanceof null → compile error
- Compiler chỉ widening hoặc boxing, không làm cả hai
- Implicit narrowing không xảy ra với long và double
- Implicit narrowing không xảy ra khi gọi method
- Boolean.valueOf("true") → true, còn lại false
- "abc" + null → "abcnull"

## 3. Switch
- case phải là literal / constant / final variable
- effectively final không dùng được trong case
- Switch support: byte short int char, wrapper, enum, String (NOT boolean)
- Switch expression dùng ->
- Switch expression phải return value (yield)
- Switch statement fall-through nếu không break
- default có thể đặt bất kỳ vị trí nào
## 4. String / Arrays
- strip() → remove whitespace
- StringBuilder.setLength(5)
- Arrays.compare(): giống → 0, khác → âm/dương, prefix → a.length - b.length
- Arrays.binarySearch(): not found → -(insertionPoint) - 1
- Arrays.mismatch(): index phần tử khác đầu tiên
## 5. Dates / Time
- LocalDate → chỉ date
- LocalDateTime → không timezone
- ZonedDateTime → có timezone
- Instant → UTC time
- Chỉ ZonedDateTime có toInstant()
- Duration.toString() luôn bắt đầu PT
- Period.of() static → không chain
- Period.plus() instance → chain
## 6. Methods – Overload – Override
Overload resolution order:
1 Exact match
2 Most specific
3 Widening
4 Autoboxing
5 Varargs
Override rules:
- Không giảm access modifier
- Không throw checked exception rộng hơn
- Cho phép covariant return
- Static method → hide, không override
- Field → hide, không override
- Method → runtime binding
- Field → compile-time binding
- Constructor không kế thừa
- super() phải dòng đầu
- Không dùng super() và this() cùng lúc
## 7. Generics
PECS rule:
Producer → ? extends
Consumer → ? super
- List<?> → không add được gì ngoài null
- List<Integer> ≠ List<Number>
- Generics không kế thừa theo type parameter
## 8. Lambda / Functional Interface
- Lambda cần target type
- Không gán lambda cho var
- Local variable trong lambda → effectively final
- Lambda deferred execution
- Method reference phải khớp param + return
## 9. Collections
- removeIf() → trả boolean
- Immutable collection (List.of) → sort → UnsupportedOperationException
- TreeSet → cần Comparable hoặc Comparator
- List.remove(int) vs List.remove(Object)
- Queue:
  add/remove/element → exception
  offer/poll/peek → không exception
- ConcurrentModificationException xảy ra với for-each / iterator
## 10. Streams
- count() → long
- sum() → primitive
- Collectors.counting() → Long
- Optional.of(null) → NullPointerException
- Optional.ofNullable(null) → Optional.empty
- Stream.min() → cần Comparator
- sorted() → element phải Comparable
- Parallel stream:
  findFirst() → giữ order
  findAny() → không giữ order
  forEachOrdered() → giữ order
## 11. Exceptions
- Không catch checked exception nếu try không throw
- Có thể catch RuntimeException dù không throw
- Có thể khai báo throws dù method không throw
- Catch order: subclass trước
- finally throw exception → override tất cả exception trước
- Try-with-resources: resource phải final hoặc effectively final
## 12. Modules
- module-info keywords:
```shell
requires / requires transitive / exports / exports ... to / provides ... with / uses ```
- Commands:
```shell
javac -p mods -d out | java -p mods -m module/class | java --list-modules | jdeps -s | jlink --add-modules --output
```
## 13. Concurrency
- synchronized static → lock Class object
- Thread chạy khi gọi start(), không phải run()
- submit() → Future
- execute() → void
- Không shutdown ExecutorService → program không kết thúc
## 14. NIO.2 / Serialization
- Path immutable
- relativize() → cả hai path phải cùng absolute hoặc relative
- toRealPath() → file phải tồn tại
- Files.walk() → recursive
- Files.list() → 1 level
- transient + static → không serialize
- Subclass Serializable, superclass không → constructor superclass chạy khi deserialize
- serialVersionUID mismatch → InvalidClassException
