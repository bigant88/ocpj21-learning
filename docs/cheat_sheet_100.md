# 1 Building Blocks
* Local variable phải khởi tạo trước khi dùng
* Instance variable tự có giá trị mặc định
* `var` chỉ dùng cho local variable
* `var` không dùng cho field
* `var` không dùng cho parameter
* `final var` hợp lệ nếu có initializer
* Text block phải xuống dòng sau `"""`
# 2 Operators
* `byte/short/char` trong phép toán → tự promote lên int
* `short * short` → kết quả là int
* Compound assignment tự ép kiểu về kiểu bên trái
* `+=` có thể compile dù mất dữ liệu
* `x = x + 1.0` ❌ nhưng `x += 1.0` ✅
* Có floating-point → toàn bộ biểu thức thành floating-point
* `~x` = `-(x + 1)`
* `null instanceof T` luôn false
# 3 Making Decision
* `obj instanceof null` không compile
* Pattern variable chỉ tồn tại trong if
* Phủ định `!(obj instanceof T t)` → `t` không tồn tại
* `case` phải là literal hoặc constant
* Biến effectively final không dùng trong `case`
* Switch hỗ trợ wrapper, enum, String
* Switch expression: mọi nhánh phải `yield`
# 4 Core API
* Literal String giống nhau dùng chung String pool
* String tạo runtime không dùng chung pool
* String là immutable
* `StringBuilder.equals()` dùng Object.equals
* `strip()` xóa whitespace đầu và cuối
* `formatted()` giống printf
* Array `length` là field
* `Arrays.binarySearch()` không thấy → `-(insertionPoint) - 1`
# 4 Dates & Times
* `LocalDateTime` không chứa timezone
* `ZonedDateTime` có zone và offset
* `Instant` đại diện thời điểm UTC/GMT
* Chỉ `ZonedDateTime` có `toInstant()`
* `Duration.toString()` luôn bắt đầu bằng PT
* `Period.of()` là static, không chain được
* `Instant` chỉ cộng/trừ DAYS trở xuống
# 5 Methods
* Varargs phải là tham số cuối cùng
* Autoboxing không cho phép int → Long
* Wrapper không có implicit widening
* Overloading ưu tiên primitive widening trước boxing
* Không match → promote primitive lên double
* `Long.parseLong()` trả primitive
* `Long.valueOf()` trả wrapper
# 6 Class Design
* `extends` luôn đứng trước `implements`
* Constructor không được kế thừa
* Constructor không được override
* `super()` phải là câu lệnh đầu tiên
* Nếu không gọi rõ, compiler tự chèn `super()`
* Superclass không có no-arg constructor → subclass phải gọi explicit
* Instance method không override static method
* Static method chỉ được hide
* Private method không override
* Field không override, chỉ hide
* Method gọi theo runtime object
* Field truy cập theo reference type
# 7 Beyond Classes
* Interface method mặc định là public abstract
* Interface field luôn public static final
* Default method trùng → class phải override
* Abstract method thắng default method
* Interface static method chỉ gọi qua interface
* Sealed subclass phải final, sealed hoặc non-sealed
* `permits` không bắt buộc nếu cùng file
# 8 Lambdas and Functional Interfaces
* Lambda cần target type
* Lambda không gán trực tiếp cho var
* Method reference cũng cần target type
* Dùng `var` trong lambda → phải có ngoặc
* Local variable dùng trong lambda phải effectively final
* Instance variable dùng trong lambda không cần final
* Lambda chỉ thực thi khi được gọi
# 9 Collections
* `List.of()` trả collection immutable
* Immutable collection không cho sort
* `List.remove(int)` xóa theo index
* `List.remove(Object)` xóa theo value
* `TreeSet` cần Comparable hoặc Comparator
* `Deque.pop()` rỗng → throw exception
* `Queue.poll()` rỗng → trả null
# 9 Generics
* Không thể khởi tạo trực tiếp type parameter: `new T()`
* Diamond operator chỉ dùng bên phải phép gán
* `List<?>` không add được phần tử
* Upper-bounded wildcard (`? extends T`) chỉ dùng để đọc
* Lower-bounded wildcard (`? super T`) dùng để ghi
* `? extends` không cho add phần tử
* `? super` cho phép add T
# 10 Streams
* `count()` trả long
* `sum()` trả primitive type
* `Optional.of(null)` ném NullPointerException
* `Optional.ofNullable(null)` trả Optional.empty
* `orElseThrow()` ném NoSuchElementException
* `sorted()` cần Comparable hoặc Comparator
* `Stream.generate()` tạo infinite stream
* `Collectors.counting()` luôn trả Long
# 11 Exceptions
* Không catch checked exception nếu try không throw
* Có thể catch RuntimeException dù try không throw
* Thứ tự catch: subclass trước, superclass sau
* try-with-resources: biến phải final hoặc effectively final
* Resource được close tự động
* Closeable.close() throw IOException
* Có thể khai báo throws dù method không throw
# 11 Localization
* Locale hợp lệ: language lowercase, country uppercase
* ResourceBundle tìm theo locale và fallback về default
* DateTimeFormatter.ofPattern() định dạng tùy chỉnh
* Ký tự `#` bỏ qua chữ số không tồn tại
* Ký tự `0` chèn số 0 nếu thiếu chữ số
* Currency format dùng Locale Category.FORMAT
* CompactNumberFormat làm tròn trong 3 chữ số
# 12 Modules
* Tên module không bắt đầu bằng số
* `exports` cho phép package public ra module khác
* `exports … to` chỉ cho module chỉ định truy cập
* `requires` khai báo module dependency
* `requires transitive` cho phép kế thừa dependency
* Unnamed module nằm trên classpath
* Automatic module export tất cả packages
# 12 Modules - commands
* `javac -p mods -d out` compile module
* `--class-path`, `-cp` hoàn toàn tương đương
* `--add-exports` phá encapsulation
* `java -p mods -m module/class` chạy module
* `java --list-modules` liệt kê module
* `jdeps -s` tóm tắt dependency
* `jlink --add-modules --output` tạo runtime image
# 13 Concurrency
* synchronized static method khóa trên Class object
* Thread chỉ chạy khi gọi `start()`
* Runnable lambda phải trả void
* Không shutdown ExecutorService → app không kết thúc
* `submit()` trả Future, `execute()` không trả gì
* `forEachOrdered()` giữ thứ tự trong parallel stream
* `scheduleAtFixedRate()` có thể chạy song song
# I/O & NIO.2 Rules
* `Path` là immutable
* resolve() với absolute path → trả absolute
* relativize() yêu cầu cùng loại path
* `toRealPath()` ném IOException nếu path không tồn tại
* Không nên đóng System.in/out/err
* Files.delete() ném IOException
* Files.lines() trả Stream
* Files.walk() duyệt đệ quy
