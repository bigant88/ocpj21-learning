# 1. Building Blocks (15 rules)

* public class phải trùng tên file
* Chỉ 1 public top-level class mỗi file
* import phải đứng trước class declaration
* Không import từ default package
* Không thể import hai class cùng tên → ambiguous
* static import không import package
* var chỉ dùng cho local variable
* var phải có initializer
* var không được gán null khi khai báo
* Không khai báo local variable trùng tên trong cùng scope
* final variable phải được gán đúng 1 lần
* Không thể dùng biến chưa initialize
* instance variable không thể được dùng trong static context
* static block không truy cập trực tiếp instance field
* Không được dùng access modifier cho local variable

---

# ➕ 2. Operators (14 rules)

* Không thể cộng boolean với int
* Không thể so sánh primitive và object bằng ==
* Không thể gán long vào int nếu không cast
* byte/short arithmetic tự động promote thành int
* ++ không dùng trên literal
* Không dùng && với non-boolean
* Không thể assign trong switch expression nếu type mismatch
* Ternary operator phải return compatible type
* Không thể unbox null
* Không thể cast giữa primitive và wrapper
* Không thể dùng instanceof với primitive
* Pattern matching variable không visible ngoài scope
* Switch expression phải exhaustive (enum/sealed)
* case label phải là constant expression

---

# 🔀 3. Making Decisions (12 rules)

* if không nhận non-boolean
* switch không nhận long/float/double
* Duplicate case label → compile error
* switch expression phải có yield hoặc ->
* Không break trong switch expression kiểu arrow
* while/for phải có boolean condition
* Do-while phải kết thúc bằng ;
* Variable declared trong case không được trùng tên case khác (arrow form)
* Không mix : và -> trong cùng switch
* Pattern matching case phải compatible type
* Sealed hierarchy phải cover đủ subtype
* Không thể fall-through trong switch expression

---

# 📦 4. Core APIs (13 rules)

* String là immutable → không có setCharAt
* Không gọi instance method trên null literal
* Arrays không có add()
* length là field, không phải method
* Không thể tạo generic array new T[]
* Không sort primitive bằng Comparator
* compareTo phải cùng type
* Không dùng equals với primitive
* LocalDate không có constructor public
* Period không cộng được vào Instant
* Optional.get() không compile nếu type mismatch
* Formatter placeholder không đúng type
* Không gọi method không tồn tại trong API version

---

# 🧰 5. Methods (16 rules)

* Method overload chỉ khác return type → compile error
* Không override với access modifier hẹp hơn
* Override không được throws checked rộng hơn
* Static method không override
* final method không override
* Abstract method không có body
* Non-abstract class không thể có abstract method
* Constructor không có return type
* Không thể gọi this() sau statement khác
* this() và super() phải là dòng đầu
* Recursive constructor call → compile error
* Varargs phải là parameter cuối
* Không thể overload chỉ khác varargs vs array ambiguous
* Không thể reference instance method trong static context
* Main method sai signature → không phải entry point
* Không gọi method chưa khai báo

---

# 🏗 6. Class Design (20 rules)

* Record không extend class khác
* Record field luôn private final
* Record không khai báo instance initializer
* Canonical constructor phải match toàn bộ component
* Compact constructor không assign field
* Sealed class phải khai báo permits
* Subclass của sealed phải final/sealed/non-sealed
* Enum constructor luôn private
* Enum không extend class
* Interface field luôn public static final
* Interface method không được protected
* Nested interface luôn static
* Non-static inner class không có static member
* Static nested class không truy cập instance outer
* Class không extend 2 class
* Interface không implement class
* Private constructor không thể super() từ subclass
* Abstract class không new được
* Cannot instantiate interface
* Final class không extend

---

# 🧩 7. Beyond Classes (Nested, Records, Sealed) (12 rules)

* Local class không được khai báo static
* Anonymous class không có constructor
* Local variable trong inner class phải effectively final
* Cannot reference non-final local variable in lambda
* Sealed class và permits phải cùng module/package
* Record component không được trùng tên method Object
* Canonical constructor không throws checked
* Nested record luôn static
* Không dùng extends với record
* Pattern matching cần compatible hierarchy
* Non-sealed chỉ dùng trong sealed hierarchy
* Static member không được trong inner non-static class

---

# 🔗 8. Lambdas & Functional Interfaces (18 rules)

* Functional interface chỉ có 1 abstract method
* default method không tính là abstract
* static method không tính là abstract
* Lambda phải match signature
* Lambda không return khi method yêu cầu return
* Không throw checked nếu FI không khai báo
* Parameter type phải compatible
* Không khai báo cùng tên variable trong scope
* Lambda không có this riêng
* Method reference phải match parameter count
* Cannot use primitive where wrapper expected (mismatch)
* Comparator.comparing cần key extractor
* Không dùng break/continue ngoài loop
* Lambda body nhiều statement phải có {}
* Return bắt buộc nếu không void
* Cannot overload only by functional interface if ambiguous
* Target type phải xác định được
* Không thể assign lambda vào Object

---

# 📚 9. Collections & Generics (22 rules)

* Không tạo new List<String>()
* Diamond operator không dùng với anonymous class (pre 9 nuance)
* Generic type không dùng primitive
* Wildcard không dùng trong new
* <? extends> không add (trừ null)
* <? super> không get cụ thể type
* Raw type gây unchecked warning nhưng compile được
* Cannot instantiate abstract collection
* Comparator type mismatch
* TreeSet cần Comparable hoặc Comparator
* Cannot cast List<String> to List<Object>
* Generic method type inference thất bại → compile error
* Cannot create array of parameterized type
* Static field không dùng generic type parameter
* Generic class không dùng type parameter trong static context
* Capture of ? cần helper method
* Cannot use instanceof với parameterized type
* Map.of không chấp nhận null
* List.of không chấp nhận null
* Immutable collection không add()
* removeIf cần Predicate
* for-each cần Iterable

---

# 🌊 10. Streams (20 rules)

* Stream không reusable sau terminal operation
* map phải return type đúng
* flatMap phải return Stream
* reduce phải compatible accumulator
* collect cần Collector đúng type
* peek không terminal
* forEachOrdered chỉ trên stream ordered
* Parallel stream không đảm bảo order
* Optional không stream trừ khi dùng stream()
* distinct cần equals
* sorted cần Comparable
* groupingBy cần classifier function
* toMap cần merge function nếu duplicate key
* Cannot modify source inside stream (compile nếu final issue)
* Lambda checked exception → compile error
* findFirst trả Optional
* noneMatch trả boolean
* count trả long
* limit cần long
* iterate cần 2 hoặc 3 args (Java 9+)

---

# ⚠️ 11. Exceptions & Localization (15 rules)

* Checked exception phải catch hoặc declare
* Cannot catch subclass trước superclass
* Multi-catch không dùng type liên quan nhau
* Finally không có return override compile? (allowed but tricky)
* Try-with-resources cần AutoCloseable
* Resource phải effectively final
* Cannot throw checked không declare
* Custom exception extend Exception/RuntimeException
* Overriding method không throw broader checked
* ResourceBundle base name không include extension
* Properties file phải đúng encoding
* MessageFormat index phải hợp lệ
* Cannot use non-final variable in catch rethrow (multi-catch)
* Close() không throw broader checked
* Cannot instantiate abstract exception

---

# 📦 12. Modules (18 rules)

* module-info.java chỉ 1 per module
* requires không duplicate
* exports không duplicate
* Cannot access non-exported package
* requires transitive chỉ dùng trong module
* open module không mix opens individual?
* opens không cho compile-time access
* Cannot use cyclic module dependency
* requires static chỉ compile time
* ServiceLoader cần provides … with
* uses cần interface/service
* Cannot declare module trong package
* Module name phải unique
* No split package across modules
* package phải khớp folder structure
* exports to cần module tồn tại
* Cannot access internal JDK package
* module descriptor không có class

---

# ⚙️ 13. Concurrency (14 rules)

* Runnable.run không throws checked
* Callable.call có throws
* ExecutorService cần shutdown
* synchronized không dùng trên primitive
* wait/notify phải trong synchronized
* Cannot call wait ngoài monitor
* ForkJoinTask cần override compute
* VirtualThread tạo qua factory đúng method
* Cannot modify effectively final in lambda thread
* Future.get throws checked
* submit trả Future
* Parallel stream không guarantee thread safety
* ConcurrentModificationException runtime, không compile
* Atomic class không dùng primitive type mismatch

---

# 💾 14. I/O (16 rules)

* Path.of không nhận null
* Files.readAllLines throws IOException
* Cannot write String vào OutputStream không convert
* PrintWriter không auto-flush nếu không config
* new FileReader throws checked
* Try-with-resources cần AutoCloseable
* NIO Path không có constructor
* Cannot mix Reader với OutputStream
* Files.copy cần proper option nếu overwrite
* Cannot delete non-empty directory
* Stream<Path> từ Files.walk phải close
* BufferedReader.readLine trả String
* Console có thể null
* Serialization class phải implement Serializable
* serialVersionUID phải long
* transient không serialize
