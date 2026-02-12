# 1 Building Blocks
- Local variable **phải khởi tạo** trước khi dùng
- Instance variable **tự có giá trị mặc định**
- `var` **chỉ dùng cho local variable**, không cho field/parameter
- `var` **không phải keyword**, có thể dùng làm tên biến
- `var` **bắt buộc khởi tạo**, không khai báo rỗng
- `var` dùng được trong lambda expression
- Text block **phải xuống dòng** sau `"""`
- `\` trong text block **loại bỏ line break**
# 2 Operators
- Compound assignment **tự ép kiểu** về kiểu của biến bên trái
- Phép toán số học với `byte/short/char` → **tự promote lên int**
- `short * short` → kết quả là **int**, không phải short
- `+=`, `-=` có thể compile dù **ép kiểu mất dữ liệu**
- `x = x + 1.0` ❌, nhưng `x += 1.0` ✅
- Numeric promotion: **kiểu nhỏ nâng lên kiểu lớn hơn**
- Có floating-point → toàn bộ biểu thức thành floating-point
- `~x` là **two’s complement**: `-(x + 1)`
- `null instanceof T` luôn **false**
- `obj instanceof null` **không compile**
- Primitive có thể **cast sang Object**
- Primitive **không cast trực tiếp** sang wrapper khác loại
- LHS là biến nhận giá trị, RHS là biểu thức gán
- Assignment và ternary **đánh giá từ phải sang trái**
- Thứ tự ưu tiên: unary → arithmetic → relational → logical → assignment
# 3 Making Decision
- Pattern matching dùng với `instanceof` để bind biến nếu type phù hợp
- Pattern variable chỉ tồn tại trong nhánh kiểm tra thành công
- Phủ định `!(obj instanceof T t)` → `t` không tồn tại sau if
- Pattern type phải là **strict subtype** của type được kiểm tra
- Pattern matching không cho phép cùng type với expression
- `instanceof` với interface có thể compile dù nhìn “lạ”
- `null instanceof T` luôn trả về `false`
- Giá trị `case` phải là literal, constant, hoặc `final variable`
- Biến chỉ effectively final **không dùng được** trong `case`
- Switch hỗ trợ primitive wrapper, enum, String
- Multi-case dùng dấu phẩy `,` trên một `case`
- `default` có thể đặt chung dòng với `case`
- Switch expression dùng `->` thay cho `:`
- Switch expression có return value → mọi nhánh phải `yield`
- Switch statement không cần `default` nếu không trả giá trị
# 4 Core API
- `+` là cộng số nếu cả hai toán hạng là numeric
- Có String → `+` trở thành phép nối chuỗi
- String concatenation **đánh giá từ trái sang phải**
- `strip()` xóa whitespace đầu và cuối
- `stripLeading()` chỉ xóa whitespace đầu
- `stripTrailing()` chỉ xóa whitespace cuối
- String và StringBuilder đều implement `CharSequence`
- `formatted()` thay thế placeholder giống `printf`
- Literal String giống nhau → cùng object trong String pool
- String tạo runtime → **không dùng chung pool**
- `StringBuilder.setLength()` có thể cắt hoặc mở rộng chuỗi
- `StringBuilder` **không có** `append(index, value)`
- Mảng không thể vừa khai báo kích thước vừa khởi tạo giá trị
- `Arrays.compare()` trả 0 nếu giống, khác trả số âm/dương
- `Arrays.binarySearch()` không tìm thấy → `-(insertionPoint) - 1`

# 4 Dates & Times
- `LocalDateTime` không chứa timezone
- `ZonedDateTime` gồm date, time, zone, offset
- `Instant` đại diện thời điểm UTC/GMT
- Chỉ `ZonedDateTime` có `toInstant()`
- `Duration` dùng cho time-based, không dùng với `LocalDate`
- `Duration.toString()` luôn bắt đầu bằng `PT`
- `Period` dùng cho date-based: years, months, days
- `Period.of()` là static, **không chain được**
- `Period.plus()` là instance, **chain được**
- `Instant` chỉ cộng/trừ **DAYS hoặc nhỏ hơn**

# 5 Methods
- Java là **pass-by-value**, method không thay đổi biến caller
- Autoboxing không cho phép `int` → `Long` trực tiếp
- `final var` hợp lệ với local variable
- Effective final: thêm `final` vẫn compile
- Method chỉ có **tối đa một varargs**
- Varargs **phải là tham số cuối cùng**
- Varargs chỉ dùng làm **method parameter**
- Varargs dùng như **array bên trong method**
- `protected` khác package chỉ truy cập qua **subclass reference**
- `A a = new B(); a.protectedMethod()` ❌ khác package
- `Long.parseLong()` trả về **primitive long**
- `Long.valueOf()` trả về **wrapper Long**
- Wrapper không có implicit widening
- Overloading ưu tiên **primitive widening** trước boxing
- Không match → promote primitive lên **double**

# 6 Class Design
- Constructor không được gọi vòng lặp `this()` (cyclic reference)
- Constructor **không thể** khai báo `final`
- Chỉ **instance method** mới được khai báo `abstract`
- `abstract` không đi cùng `private`, `static`, hoặc `final`
- Class `abstract` không thể là `final`
- Method `abstract` không thể là `final`
- Method `abstract` không thể là `static`
- Method `abstract` không thể là `private`
- Immutable class: field `private final`, không setter
- `extends` luôn đứng trước `implements`
- Instance method **không override** static method
- Static method `final` **không thể bị hide**
- Method `private` **không override**, chỉ là method mới
- Instance variable **không override**, chỉ bị hide
- Override cho phép **covariant return type**
- Subclass constructor luôn gọi constructor superclass trước
- Nếu không gọi rõ, compiler tự chèn `super()`
- `super()` phải là câu lệnh đầu tiên trong constructor
- Nếu superclass không có constructor không tham số → subclass phải gọi explicit
- Overridden method **không được giảm** mức truy cập
- Overridden method **có thể tăng** mức truy cập
- Overridden method **không throw checked exception rộng hơn**
- Static method được **hide**, không được override
- Field trùng tên giữa superclass/subclass → **variable hiding**
- Method được gọi dựa trên **runtime object**, field dựa trên **reference type**
- Constructor không được override, chỉ được overload
- Constructor không được kế thừa
- Interface method mặc định là `public abstract`
- Implement interface phải giữ method `public`
- Interface field luôn là `public static final`
- Class implement interface phải implement **tất cả abstract methods**
- Default method không override được `Object` methods
- Diamond problem cần override để resolve conflict
- `super` dùng gọi method/field của superclass
- `this` và `super` không dùng trong static context  

# 7 Beyond Classes
- Interface method mặc định là `public abstract`
- Interface variable luôn `public static final`
- Interface **không thể** khai báo `final`
- Default method trùng signature → class **bắt buộc override**
- Abstract method thắng default method khi xung đột
- Interface static method **chỉ gọi qua interface name**
- Private interface method **chỉ dùng trong interface**
- Sealed class phải có ít nhất một subclass
- Subclass sealed phải là `final`, `sealed`, hoặc `non-sealed`
- Sealed class và subclass phải cùng package hoặc module
- `permits` không bắt buộc nếu cùng file
- Record là immutable, **không có instance field riêng**
- Compact constructor không dùng `this.field =`
- Enum constructor **luôn implicit private**
- Enum có abstract method → mọi constant phải override

# 8 Lambdas and Functional Interfaces
- Lambda cần **target type**, không gán trực tiếp cho `var`
- Method reference cũng cần target type, không gán cho `var`
- `var` dùng trong lambda **phải có dấu ngoặc**
- Dùng `var` → **tất cả parameters phải dùng `var`**
- Không được trộn `var` và kiểu cụ thể trong lambda
- Lambda parameter type phải khớp generic của functional interface
- Không boxing ngược: `Integer` target không nhận `int` lambda
- Local variable dùng trong lambda phải **effectively final**
- Method parameter dùng trong lambda phải **effectively final**
- Instance variable dùng trong lambda **không cần effectively final**
- Lambda được compile nhưng **chỉ thực thi khi gọi** (deferred execution)
- Method reference phải khớp **số parameter và return type**
- Một method reference có thể map nhiều functional interface
- Functional interface chỉ có **một abstract method**
- Object methods không tính vào abstract method count
- Collectors.toMap throws IllegalStateException if a key already exists 

# 9 Collections
- `contains()` nhận `Object`, không phải generic type
- `removeIf()` trả boolean, true nếu có phần tử bị xóa
- Collection immutable (`List.of`) **không cho sort hoặc modify**
- `Collections.sort()` với immutable collection → `UnsupportedOperationException`
- `TreeSet` cần `Comparable` hoặc `Comparator`
- `List.remove(int)` xóa theo index, không theo value
- `List.remove(Object)` xóa theo value
- `Comparator.compare()` nằm trong `java.util`
- `Comparable.compareTo()` nằm trong `java.lang`
- Queue: `add/remove/element` → throw exception khi lỗi
- Queue: `offer/poll/peek` → không throw exception
- `Deque` mở rộng `Queue`, hỗ trợ stack và queue
- Deque rỗng: `pop()` throw exception, `poll()` trả `null`
- `Map.forEach()` dùng `BiConsumer`, không phải `Consumer`
- `TreeMap` key phải cùng type, không cho so sánh khác loại

# 9 Generics
- Không thể khởi tạo trực tiếp type parameter: new T() là lỗi biên dịch
- Generic class không dùng type parameter trong static context
- Static method phải khai báo <T> trước kiểu trả về
- Instance method có thể dùng type parameter của class
- Diamond operator <> chỉ dùng phía bên phải phép gán
- Không dùng diamond operator ở bên trái khai báo
- Upper-bounded wildcard (? extends T) không cho phép add phần tử
- Lower-bounded wildcard (? super T) cho phép add T và subclass
- List<?> là immutable, không add được phần tử
- ? extends dùng để đọc dữ liệu, không để ghi
- ? super dùng để ghi dữ liệu, đọc về Object
- Generic type không hỗ trợ covariance trực tiếp
- Wildcard chỉ dùng với reference type, không dùng primitive
- Generic method có thể được gọi với cú pháp <Type>method()
- Type parameter của method che khuất type parameter của class

# 10 Streams
- Primitive streams trả Optional chuyên biệt, không dùng Optional<T>
- OptionalDouble dùng getAsDouble(), không dùng get()
- count() trả long, sum() trả primitive type
- summaryStatistics() gom min, max, sum, average
- LongStream không có mapToLong()
- IntStream.sum() trả int, không phải long
- Optional.of(null) ném NullPointerException
- Optional.ofNullable(null) trả Optional.empty
- orElseThrow() ném NoSuchElementException nếu empty
- Stream.generate() và iterate() tạo infinite stream
- sorted() yêu cầu element Comparable hoặc có Comparator
- sorted() với non-Comparable → ClassCastException
- Comparator::reverseOrder không compile trong sorted()
- findFirst() luôn trả phần tử đầu của ordered stream
- unordered() có thể thay đổi thứ tự khi chạy parallel
- anyMatch/allMatch/noneMatch nhận Predicate
- Stream.min() bắt buộc truyền Comparator
- reduce(identity, accumulator) luôn trả giá trị
- Collectors.partitioningBy() cần Predicate
- Collectors.counting() luôn trả Long

# 11 Exceptions
- Không catch checked exception nếu try không throw → lỗi biên dịch
- Có thể catch RuntimeException dù try không throw
- Có thể khai báo throws dù method không throw
- Thứ tự catch: subclass trước, superclass sau
- try-with-resources: biến phải final hoặc effectively final
- AutoCloseable.close() được gọi tự động khi kết thúc try
- Closeable extends AutoCloseable, close() throw IOException
- ExceptionInInitializerError xảy ra trong static initializer
- NoClassDefFoundError: có lúc compile, thiếu lúc runtime
- StackOverflowError do đệ quy vô hạn
- 
# 11 Localization
- Java dùng DateTimeFormatter, không có DateFormatter
- DateTimeFormatter.ofPattern() định dạng ngày giờ tùy chỉnh
- Ký tự `#` bỏ qua chữ số không tồn tại
- Ký tự `0` chèn số 0 nếu thiếu chữ số
- CompactNumberFormat làm tròn trong phạm vi ba chữ số
- CompactNumberFormat: truyền cả Locale và Style hoặc không truyền
- Currency format dùng Locale Category.FORMAT
- Locale hợp lệ: ngôn ngữ lowercase, quốc gia uppercase
- ResourceBundle tìm theo locale, fallback về default
- MessageFormat dùng {0}, {1} để thay tham số

# 12 Modules
- Package trong module **không được export mặc định**
- Tên module không bắt đầu bằng số, không chứa dấu gạch ngang
- `exports` cho phép package public ra module khác
- `exports … to` chỉ cho phép module chỉ định truy cập
- `requires` khai báo module dependency
- `requires transitive` cho phép module phụ tự động kế thừa dependency
- Automatic module export **tất cả packages**
- Unnamed module nằm trên classpath, không có module-info
- Sealed class trong unnamed module phải cùng package
- Một service chỉ được `provides` **một implementation mỗi module**
# 12 Modules - commands
- `javac -p mods -d out` dùng compile module với module-path
- `--class-path`, `-classpath`, `-cp` hoàn toàn tương đương
- `--add-exports` phá encapsulation để dùng internal API
- `jar -cvf` dùng đóng gói module thành JAR
- `java -p mods -m module/class` chạy module
- `--show-module-resolution` in quá trình resolve module khi chạy
- `java -p mods -d moduleName` mô tả module
- `java --list-modules` liệt kê module có sẵn
- `jdeps -s` tóm tắt dependency của module hoặc JAR
- `jlink --add-modules --output` tạo custom runtime image

# Concurrency 

- synchronized block có thể khóa trên object hoặc class
- synchronized static method khóa trên **Class object**
- Runnable lambda **phải trả void**, không return giá trị
- Thread chỉ bắt đầu khi gọi `start()`, không phải `run()`
- `interrupt()` chỉ ảnh hưởng thread WAITING hoặc TIMED_WAITING
- Interrupt thread chưa start hoặc đã chết → không tác dụng
- `forEachOrdered()` giữ nguyên thứ tự trong parallel stream
- reduce() dùng parallel phải associative, stateless, non-interfering
- reduce(identity, accumulator, combiner): identity cùng type kết quả
- `sequential()` chuyển parallel stream về sequential
- `Executors.newCachedThreadPool()` tạo thread khi cần
- `submit()` trả Future, `execute()` không trả gì
- `Future.get()` có timeout ném checked exceptions
- Không shutdown ExecutorService → chương trình không kết thúc
- `scheduleAtFixedRate()` có thể chạy song song cùng task cũ
- `scheduleWithFixedDelay()` đợi task cũ xong mới chạy tiếp
- ConcurrentSkipList* → collection **được sắp xếp**
- collect() parallel cần collector có `CONCURRENT`
- `tryLock()` có thể thất bại, không block vĩnh viễn
- Deadlock, starvation, livelock là ba vấn đề liveness chính

# I/O & NIO.2 Rules
- `File.delete()` không ném exception nếu file không tồn tại
- `Path` là immutable, gọi method không gán → không thay đổi
- resolve() với absolute path → trả absolute path
- relativize() yêu cầu cả hai path cùng loại absolute hoặc relative
- `toRealPath()` ném IOException nếu path không tồn tại
- `toAbsolutePath()` không ném IOException
- Không nên đóng `System.in`, `System.out`, `System.err`
- Writer trong try-with-resources → method phải throws IOException
- Reader.mark() cần kiểm tra `markSupported()` trước
- Object deserialization đọc nhiều object → dùng vòng while + EOFException
- Deserialization gọi constructor của superclass không Serializable
- Console phải lấy qua `System.console()`, không new
- Files.delete() và deleteIfExists() đều ném IOException
- Files.lines() trả Stream, Files.readAllLines() load toàn bộ memory
- Files.walk() duyệt đệ quy, list() chỉ duyệt một cấp
