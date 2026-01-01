# TextBlock 
## Cú pháp cơ bản 
```java
String s = """
text
""";
```
- Bắt đầu bằng """, phải theo sau bởi newline 
- kết thúc bằng """
- là compile-time constant nếu nội dung là literal 
- ❌ Không hợp lệ:
```java
String s = """ hello """;
```
## Indentation (hay ra thi)
- Incidental whitespace: số khoảng trắng chung nhỏ nhất ở đầu mọi dòng -> bị loại bỏ
- Essential whitespace: khoảng trắng còn lại sau khi loại incidental -> được gữ 
- Trailing whitespace: space/tab cuối dòng bị loại bỏ 
- Mỗi dòng kết thúc bằng 1 LN (\n)
## Escape character: 
- Escape vẫn hoạt động , nhưng ko nên dùng \n
```java
"""
Hello\tTab\n
"""
```
## Line continuation 
- Xuống dòng trong code nhưng ko xuống trong String 
```java
String s = """
Hello \
World
""";
```
- Output: Hello World
## 🧠 Quy tắc nhớ nhanh
- Opening """ -> phải xuống dòng 
- Trailing spaces bị loại 
- mỗi dòng kết thúc bằng new line LF 
- Indentation nhỏ nhất bị cắt 
- "\" = nối dòng code, ko nối dòng String 
## Ví dụ hay thi 
- Có new line cuối chuỗi 
```java
String s = """
hello
""";
//➡ "hello\n" → length = 6
```
- có essential whitespace 
```java
String s = """
   hello
""";
// " hello\n" → length = 9
```
- ✔ Không có newline cuối
```java
String s = """
hello""";
// ➡ "hello" → length = 5
```
- ✔ Chuỗi rỗng
```java
String s = """
""";
// ➡ length = 0
```
- ❌ Không compile
```java
String s = """ hello
""";
// ➡ ❌ thiếu newline sau opening """
```
# FACTS ABOUT JAVA STRINGS
### Literal strings Luôn tham chiếu cùng một object trong String Pool
```java
// package p1
String a = "java";
// package p2
String b = "java";
// a == b → true
```
### Constant expressions → xử lý như literal ➡ Được tính tại compile time ➡ Đưa vào String Pool
```java
String a = "ja" + "va";
String b = "java";
System.out.println(a == b); // true
```
### Runtime expressions → object mới ➡ Tạo String mới trên heap ➡ KHÔNG dùng String Pool
```java
String a = "ja";
String b = a + "va";
System.out.println(b == "java"); // false
```
### intern() → ép về String Pool ➡ Nếu literal tồn tại → trả về reference đó ➡ Nếu chưa tồn tại → đưa vào pool rồi trả về
```java
String a = new String("java");
String b = a.intern();
System.out.println(b == "java"); // true
```
## ✅ Một câu nhớ gọn
- Literal & compile-time constant → String Pool
- Runtime computation → new object
- intern() → quay về pool
- (Literal là giá trị được ghi trực tiếp trong mã nguồn, và được xác định ngay tại compile time.)