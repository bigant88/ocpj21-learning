# 07 Date and Time
- Câu 13: Chuyển Instant → LocalDateTime phải qua ZonedDateTime: instant.atZone(zone).toLocalDateTime().
```java
Instant instant = Instant.now();
ZoneId zone = ZoneId.systemDefault();
LocalDateTime ldt = instant.atZone(zone).toLocalDateTime();
```
- Câu 67: Duration.between yêu cầu cùng kiểu Temporal; LocalDateTime vs LocalTime gây RuntimeException.
```java
LocalTime t1 = LocalTime.now();
LocalDateTime t2 = LocalDateTime.now();
System.out.println(Duration.between(t2, t1));
```
- Câu 75: New_York 2018-11-04 13:59:59 + 1s ⇒ 14:00:00 (DST đã kết thúc từ buổi sáng).
```java
LocalDate date = LocalDate.of(2018, 11, 4);
LocalTime time = LocalTime.of(13, 59, 59);
ZonedDateTime dt = ZonedDateTime.of(date, time, ZoneId.of("America/New_York"));
dt = dt.plusSeconds(1);
```
- Câu 76: DateTimeFormatter.ofLocalizedDate(FULL) chỉ định dạng ngày, bỏ phần giờ.
```java
LocalDateTime date = LocalDateTime.of(2019, 1, 1, 10, 10);
DateTimeFormatter f = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
System.out.println(f.format(date));
```

- Câu 44: Không thể chuyển trực tiếp Date → LocalDate; phải qua Instant và ZonedDateTime.
   ```java
   Date date = new Date();
   LocalDate localDate =
       date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
   Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
   ```
- Câu 49: LocalDate.ofEpochDay(1) tương ứng 1970-01-02 vì epoch day 0 là 1970-01-01.
   ```java
   LocalDate date = LocalDate.ofEpochDay(1);
   System.out.println(date); // 1970-01-02
   ```
- Câu 83: LocalDate immutable; minus()/plus() không thay đổi biến gốc nếu không gán lại -> equals/isEqual đều false.
   ```java
   LocalDate d1 = LocalDate.of(2019,1,2);
   d1.minus(Period.ofDays(1));
   LocalDate d2 = LocalDate.of(2018,12,31);
   d2.plus(Period.ofDays(1));
   System.out.println(d1.equals(d2)+":"+d1.isEqual(d2));
   ```

# 09 Class Design
- Câu 45: Phương thức final của Object (getClass/notify/wait) không thể override.
```java
class Player {
    public Class getClass() { return super.getClass(); }
}
```
- Câu 49: Singleton holder class: lớp lồng tĩnh giữ instance; count chỉ tăng 1 lần.
```java
static class PrinterCreator { static Printer printer = new Printer(); }
static Printer getInstance() { return PrinterCreator.printer; }
System.out.println(Printer.getCount());
```

# 11 Lambdas & Functional Programming
- Câu 1: this trong anonymous class → anonymous; this trong lambda → instance bao ngoài.
```java
I5 obj1 = new I5(){ int i=200; public void print(){ System.out.println(this.i); } };
I5 obj2 = () -> { int i=300; System.out.println(this.i); };
new Test().obj1.print(); new Test().obj2.print();
```
- Câu 22: check(Supplier<...>): hợp lệ: Supplier<Document> hoặc Supplier<? extends Document>.
```java
check(Document::new);
check(RFP::new);
private static void check(java.util.function.Supplier<? extends Document> s){ s.get().printAuthor(); }
```
- Câu 56: Map.putIfAbsent qua BiConsumer ref: null được thay bằng giá trị mới.
```java
NavigableMap<Integer,String> map = new java.util.TreeMap<>();
java.util.function.BiConsumer<Integer,String> c = map::putIfAbsent;
c.accept(1, null); c.accept(1, "ONE");
```
- Câu 64: Consumer.andThen truyền cùng đối số; i++ hậu tố ⇒ in ra 55.
```java
java.util.function.Consumer<Integer> consumer = System.out::print;
Integer i = 5; consumer.andThen(consumer).accept(i++);
System.out.println();
```

- Câu 6: BiFunction<Integer,Integer,Character> yêu cầu trả Character; i+j là int nên lỗi compile.
   ```java
   BiFunction<Integer,Integer,Character> f = (i,j) -> i + j; // compile error
   System.out.println(f.apply(0,65));
   ```

# 12 Collections
- Câu 23: Wildcard ? extends chỉ đọc (PECS); gọi set(...) bị cấm ⇒ lỗi biên dịch.
```java
public static <T> void print1(A<? extends Animal> obj){
    obj.set(new Dog()); // compile error
    System.out.println(obj.get());
}
```
- Câu 35: Tên tham số kiểu có thể là 'String'; không override toString ⇒ in “…@…”.
```java
class Printer<String>{ private String t; Printer(String t){ this.t=t; } }
Printer<Integer> obj = new Printer<>(100);
System.out.println(obj);
```
- Câu 57: new ArrayList<>(Arrays.asList(...)) cho phép removeIf; lọc số lẻ ⇒ còn số chẵn.
```java
List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
list.removeIf(i -> i % 2 == 1);
System.out.println(list);
```
- Câu 60: TreeSet với Comparator theo tên: tên trùng ⇒ coi như trùng phần tử ⇒ size = 1.
```java
java.util.Set<Student> set = new java.util.TreeSet<>(Student::compareByName);
set.add(new Student("James",20)); set.add(new Student("James",22));
System.out.println(set.size());
```
- Câu 61: NavigableMap.headMap(25,true) lấy các entry có key ≤ 25.
```java
NavigableMap<Integer,String> map = new java.util.TreeMap<>();
map.put(11,"A"); map.put(25,"B"); map.put(32,"C");
System.out.println(map.headMap(25,true));
```
- Câu 87: List<? super String> khi đọc là Object ⇒ for-each phải dùng Object, không dùng String.
```java
List<? super String> list = new ArrayList<>(); list.add("A"); list.add("B");
for(Object o: list){ System.out.print(o); }
// ...
```

- Câu 15: IntStream.range(10,1) trả stream rỗng vì start > end → count = 0.
   ```java
   System.out.println(IntStream.range(10,1).count());
   ```
- Câu 20: Raw type khiến T=Object; set(...) liên tục ghi đè; giá trị cuối (%) được in.
   ```java
   Test obj = new Test();
   obj.set("OCP");
   obj.set(85);
   obj.set('%');
   System.out.println(obj.get());
   ```
- Câu 26: “super” chỉ dùng trong wildcard (? super T) → không dùng trong type parameter.
   ```java
   class A<T extends String>{}
   class B<T super String>{} // compile error
   ```
- Câu 30: remove() trên Deque rỗng → NoSuchElementException.
   ```java
   Deque<Character> q = new ArrayDeque<>();
   q.add('A'); q.remove(); q.remove(); // ném NoSuchElementException
   ```
- Câu 32: partitioningBy(s→s.equals("OCA")) luôn false vì s là Certification, không phải String.
   ```java
   stream.collect(Collectors.partitioningBy(s -> s.equals("OCA")));
   System.out.println(map.get(true)); // []
   ```
- Câu 40: TreeMap với enum key sắp theo thứ tự định nghĩa enum, không theo put().
   ```java
   enum TL { RED, YELLOW, GREEN }
   Map<TL,String> map = new TreeMap<>();
   ```
- Câu 41: Stream<T> không có sum(); chỉ IntStream/LongStream/DoubleStream có.
   ```java
   Stream<Integer> s = Arrays.asList(1,2,3).stream();
   s.sum(); // compile error
   ```
- Câu 43: compareTo trả o.age - this.age → sắp Employee giảm dần theo tuổi.
   ```java
   public int compareTo(Employee o){ return o.age - this.age; }
   ```
- Câu 60: Comparator.comparing và compareTo hợp lệ; Integer::max không phải comparator đúng.
   ```java
   list.stream().max(Integer::compareTo); // OK
   list.stream().max(Integer::max); // sai
   ```
- Câu 61: Type parameter tên “String” che java.lang.String → toString() lỗi.
   ```java
   class Printer<String>{
       public String toString(){ return null; }
   }
   ```
- Câu 76: TreeSet không cho null → NullPointerException.
   ```java
   Set<String> s = new TreeSet<>(Arrays.asList(null,null,null));
   ```

# 13 Exceptions
- Câu 53: TWR với AutoCloseable: close() có thể ném checked ⇒ main phải catch/declare.
```java
class MyResource implements AutoCloseable{ public void close(){ System.out.println("Closing"); } }
try(AutoCloseable r = new MyResource()){ /* ... */ }
// ...
```
- Câu 65: assert x : y (y là boolean) ⇒ AssertionError.getCause()==null; chỉ set cause khi y là Throwable.
```java
try{ assert 1==2 : 2==2; }
catch(AssertionError ae){ System.out.println(ae.getCause()); }
// ...
```
- Câu 68: Precise rethrow (Java 7+): catch(Exception e){ throw e; } hợp lệ nếu method khai báo throws SQLException.
```java
private static void m() throws java.sql.SQLException{
    try{ throw new java.sql.SQLException(); }
    catch(Exception e){ throw e; }
}
```
- Câu 72: Multi-catch chỉ có 1 biến bắt: catch(A | B e).
```java
try{ throw new YourException(); }
catch(MyException | YourException e){ System.out.println("Caught"); }
// ...
```
- Câu 90: Scanner trong TWR có thể close() nhiều lần mà không lỗi; vẫn in input.
```java
try(java.util.Scanner scan = new java.util.Scanner(System.in)){
    String s = scan.nextLine(); System.out.println(s);
    scan.close(); scan.close();
}
```

- Câu 19: Gán lại biến e trong catch làm mất precise rethrow → throw e lỗi compile.
   ```java
   catch(Exception e){
       e = new SQLException();
       throw e; // compile error
   }
   ```
- Câu 21: switch(null) → NullPointerException trước default.
   ```java
   switch(status) { }
   ```
- Câu 58: Biến trong TWR không dùng được ở catch → writer.close() lỗi compile.
   ```java
   try(PrintWriter w = new PrintWriter(System.out)){}
   catch(Exception ex){ w.close(); } // lỗi
   ```
- Câu 62: Nhiều resource đóng ngược thứ tự → r2 đóng trước r1.
   ```java
   try(r1; r2){ System.out.println("Test"); }
   ```
- Câu 78: Exception chính là từ m1(); close() vẫn chạy; output: A E C B.
   ```java
   r1.m1(); // A + throw new Exception("B")
   r2.close(); r1.close(); // EC
   ```
- Câu 80: FileReader.close() ném IOException; catch chỉ FileNotFoundException → không compile.
   ```java
   try(FileReader fr = new FileReader("C:/t.txt")){}
   catch(FileNotFoundException e){} // thiếu IOException
   ```

# 15 Beyond Classes
- Câu 42: enum không thể extends, có thể implements; ngầm extends java.lang.Enum.
```java
enum E implements Runnable{ public void run(){} }
// tất cả enum ngầm extends java.lang.Enum
// ...
```
- Câu 48: Outer có thể truy cập private của inner; outer.num1 + inner.num2 hợp lệ.
```java
class M{ private int num1=100; class N{ private int num2=200; } }
M outer = new M(); M.N inner = outer.new N();
System.out.println(outer.num1 + inner.num2);
```

- Câu 36: Outer truy cập private của inner; new Y() → this.new Y().
   ```java
   Y obj = new Y();
   obj.m(); // OK
   ```
- Câu 73: ++x làm x không còn effectively final → compile error.
   ```java
   System.out.println(++x);
   ```
- Câu 84: Enum constant list phải đứng đầu file enum.
   ```java
   enum TrafficLight {
       GREEN("go"), AMBER("slow"), RED("stop");
   }
   ```

# 16 Streams
- Câu 20: Optional.ofNullable(null) ⇒ Optional.empty.
```java
java.util.Optional<Integer> o = java.util.Optional.ofNullable(null);
System.out.println(o);
// Optional.empty
```
- Câu 21: Stream sau terminal op (forEach) không dùng lại được ⇒ count() ném IllegalStateException.
```java
java.util.stream.IntStream s = "OCP".chars();
s.forEach(c -> System.out.print((char)c));
System.out.println(s.count());
```
- Câu 34: anyMatch short-circuit với stream vô hạn; i>1 đúng tại phần tử 2 ⇒ trả true và dừng.
```java
java.util.stream.Stream<Integer> s = java.util.stream.Stream.iterate(1, i->i+1);
System.out.println(s.anyMatch(i -> i > 1));
// ...
```
- Câu 44: Stream rỗng: anyMatch=false, allMatch=true, noneMatch=true (predicate không được đánh giá).
```java
java.util.List<String> list = new java.util.ArrayList<>();
System.out.println(list.stream().anyMatch(x->x.length()>0));
System.out.println(list.stream().allMatch(x->x.length()>0));
```
- Câu 71: reduce không identity trả Optional; filter endsWith("d") ⇒ Optional[2nd3rd].
```java
java.util.List<String> codes = java.util.Arrays.asList("1st","2nd","3rd","4th");
System.out.println(codes.stream().filter(s->s.endsWith("d")).reduce((a,b)->a+b));
// ...
```
- Câu 78: Map không có stream(); dùng entrySet()/keySet()/values().stream().
```java
java.util.Map<Integer,String> map = new java.util.HashMap<>();
long c = map.entrySet().stream().count();
System.out.println(c);
```
- Câu 79: Files.lines trả Stream<String> (không cần stream()); readAllLines trả List<String> (có thể stream()).
```java
java.nio.file.Files.lines(java.nio.file.Paths.get("F:/Book.java")).forEach(System.out::println);
java.nio.file.Files.readAllLines(java.nio.file.Paths.get("F:/Book.java")).stream().forEach(System.out::println);
// ...
```

- Câu 16: orElseThrow(MyException::new) ném checked exception → phải catch/declare.
   ```java
   optional.orElseThrow(MyException::new);
   ```
- Câu 27: reduce(res++, ...) dùng giá trị trước tăng → 24.
   ```java
   stream.reduce(res++, (i,j)->i*j);
   ```
- Câu 32: Predicate sai → bucket true luôn rỗng.
   ```java
   s -> s.equals("OCA");
   ```
- Câu 41: Stream<T>.sum() không tồn tại; cần mapToInt.
   ```java
   stream.mapToInt(Integer::intValue).sum();
   ```
- Câu 60: Comparator hợp lệ → comparing, compareTo.
   ```java
   list.stream().max(Comparator.comparing(a->a));
   ```

# 17 Localization
- Câu 12: ResourceBundle: thứ tự tra cứu → khớp base file khi không có gói phù hợp ⇒ in 'French/Canada'.
```java
java.util.Locale.setDefault(new java.util.Locale("fr","CA"));
java.util.Locale loc = new java.util.Locale("en","IN");
java.util.ResourceBundle rb = java.util.ResourceBundle.getBundle("ResourceBundle", loc);
```
- Câu 69: Lọc Locale tiếng Pháp: getLanguage().equals('fr') hoặc toString().startsWith('fr').
```java
java.util.Locale[] loc = java.util.Locale.getAvailableLocales();
java.util.Arrays.stream(loc).filter(x->x.getLanguage().equals("fr")).forEach(System.out::println);
// ...
```

- Câu 10: Locale cho phép language/country vô nghĩa; in đúng chuỗi nhập.
   ```java
   Locale l = new Locale("temp","UNKNOWN");
   ```
- Câu 17: setDefault gọi qua instance vẫn hợp lệ (compiler chuyển thành static).
   ```java
   loc.setDefault(loc);
   ```

# 19 Concurrency
- Câu 2: ExecutorService.submit(Runnable) ⇒ Future.get() trả null; ép sang Integer in null.
```java
java.util.concurrent.ExecutorService es = java.util.concurrent.Executors.newSingleThreadExecutor();
java.util.concurrent.Future f = es.submit(() -> System.out.println("OCP"));
System.out.println((Integer)f.get()); es.shutdown();
```
- Câu 33: CyclicBarrier(2, action): cần 2 await(); tạo thêm Player(cb) để kích hoạt action và kết thúc.
```java
java.util.concurrent.CyclicBarrier cb = new java.util.concurrent.CyclicBarrier(2, new Match());
Player p1 = new Player(cb);
new Player(cb);
```
- Câu 51: AtomicInteger.getAndDecrement() atomic; luôn in 3 số 3,2,1 nhưng thứ tự không đảm bảo.
```java
private static java.util.concurrent.atomic.AtomicInteger ai = new java.util.concurrent.atomic.AtomicInteger(3);
System.out.print(ai.getAndDecrement());
// ...
```

- Câu 18: shutdown() không đợi task hoàn thành → size có thể 0–1000.
   ```java
   s.shutdown();
   System.out.println(a.getList().size());
   ```
- Câu 56: Livelock = trạng thái thay đổi liên tục nhưng không tiến triển.

# 20 IO
- Câu 11: Khi copy bytes, phải ghi theo số byte đọc được: write(arr,0,res); tránh ghi cả mảng.
```java
int res; byte[] arr = new byte[500000];
while((res = fis.read(arr)) != -1){
    fos.write(arr); // sai
    // fos.write(arr, 0, res); // đúng
}
```
- Câu 73: newBufferedReader(Path) theo symbolic link sẽ đọc file đích bình thường.
```java
java.nio.file.Path p = java.nio.file.Paths.get("C","TEMP","msg");
try(java.io.BufferedReader r = java.nio.file.Files.newBufferedReader(p)){
    System.out.println(r.readLine());
}
```
- Câu 74: Files.find(root, 2, pred) chỉ quét F:/ và thư mục con trực tiếp ⇒ liệt kê *.txt ở F:/Parent.
```java
java.nio.file.Path root = java.nio.file.Paths.get("F:");
java.util.function.BiPredicate<java.nio.file.Path, java.nio.file.attribute.BasicFileAttributes> pred =
    (p,a) -> p.toString().endsWith("txt");
java.nio.file.Files.find(root, 2, pred).forEach(System.out::println);
```
- Câu 77: Path.subpath(b,e) bỏ root; b inclusive, e exclusive ⇒ (1,4) → B\C\Book.java.
```java
java.nio.file.Path path = java.nio.file.Paths.get("F:/A/B/C/Book.java");
System.out.println(path.subpath(1,4));
// B\C\Book.java
```

- Câu 3: path.toFile().isDirectory(), Files.isDirectory(path) hợp lệ; File.isDirectory(Path) không tồn tại.
   ```java
   Files.isDirectory(path);
   ```
- Câu 37: createDirectory yêu cầu parent tồn tại → NoSuchFileException.
   ```java
   Files.createDirectory(Paths.get("F:/X/Y/Z"));
   ```
- Câu 38: getParentFile() → File, getParent() → String.
   ```java
   dir.getParentFile().getParentFile();
   ```
- Câu 39: copy(symbolic link) → copy target file → src là link, tgt là file thường.
   ```java
   Files.copy(src, tgt);
   ```
- Câu 57: PrintWriter không ném IOException; write() sau close() không ném lỗi.
   ```java
   bw.close(); bw.write(1);
   ```
- Câu 67: flush() trên stream đã close → IOException.
   ```java
   bw.flush();
   ```
- Câu 77: Console lấy qua System.console(); readPassword trả char[].
   ```java
   Console c = System.console();
   char[] pwd = c.readPassword("Enter: ");
   ```

# 10- Abstract Classes & Interfaces
- Câu 19: Method của lớp cha (concrete) ưu tiên hơn default method từ interface khi trùng chữ ký.
```java
interface Printer1 { default void print(){ System.out.println("Printer1"); } }
class Printer2 { public void print(){ System.out.println("Printer2"); } }
class Printer extends Printer2 implements Printer1 {}
```