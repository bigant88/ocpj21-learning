# Java Modules 
## 1️⃣ Tổng quan
- mỗi module có 1 module-info.java 
- kiểm soát truy cập - encapsulation
- quản lý dependencies 
- tăng tính bảo mật & maintainability 
## 2️⃣ Module declaration
```java
module zoo.animal.feeding {
  exports zoo.animal.feeding;
}
//📌 Nếu không exports, package đó KHÔNG truy cập được từ module khác.
```
## 3️⃣ Quy tắc đặt tên
- ❌ KHÔNG được: 
  - bắt đầu bằng số 
  - chứa dấu -
- ✅ Hợp lệ: $ và _ được phép ( 📌 OCP rất hay hỏi)
## 4️⃣ Module directives (cực quan trọng)
- 🔹 exports
    - Export package có điều kiện
    - Chỉ module zoo.staff mới truy cập được
    - 📌 Mặc định: KHÔNG package nào được export
```java
exports zoo.animal.talks.content to zoo.staff;
```
- 🔹 requires 
  - Module hiện tại phụ thuộc module khác
```java
requires zoo.animal.feeding;
```
- 🔹 requires transitive
  - Nếu A requires transitive B → module nào requires A thì tự động thấy B 👉 Hay dùng cho API modules
```java
requires transitive zoo.animal.care;
```
- 🔹 requires mandated
  - Dependency do Java platform yêu cầu
```java
requires mandated moduleB;
```
- 🔹 opens
  - Cho phép reflection runtime
    - Khác với exports:
      - exports → compile + runtime 
      - opens → runtime only
```java
opens zoo.animal.talks.schedule;
opens zoo.animal.talks.media to zoo.staff;
```
##  5️⃣ Service trong Java Modules (SIÊU QUAN TRỌNG)
### 5.1 Service Provider Interface (SPI)
```java
public interface Tour {
  String name();
}
```
```java
module zoo.tours.api {
  exports zoo.tours.api;
}
//📌 BẮT BUỘC export package chứa interface
```
### 5.2 Service Provider (Implementation)
```java
public class TourImpl implements Tour {
  public String name() {
    return "service name";
  }
}
```
```java
module zoo.tours.agency {
  requires zoo.tours.api;
  provides zoo.tours.api.Tour
      with zoo.tours.agency.TourImpl;
}
```
- Cú pháp : provides <interface> with <implementation>
- ➡ 1 module chỉ được cung cấp 1 implementation cho mỗi service
```java
provides Tour with Impl1;
provides Tour with Impl2; // ❌ Lỗi compile
```
### 5.3 Service Locator (ServiceLoader)
- Tìm implementation runtime
- Không biết trước implementation cụ thể
```java
for (Dog d : ServiceLoader.load(Dog.class)) {
  all.add(d);
}
```
- stream()
```java
List<Dog> list = ServiceLoader.load(Dog.class)
    .stream()
    .map(Provider::get)
    .toList();
```
- 📌 Bẫy OCP:
  - load() → static
  - stream() → instance
### 5.4 Module dùng Service (uses)
```java
module zoo.tours.reservations {
  exports zoo.tours.reservations;
  requires zoo.tours.api;
  uses zoo.tours.api.Tour;
}
```
- 📌 uses ≠ requires
  - _uses_ chỉ khai báo sẽ dùng ServiceLoader
  - Không tạo dependency compile-time tới implementation
### 5.5 Consumer
```java
module zoo.visitor {
  requires zoo.tours.api;
  requires zoo.tours.reservations;
}
```
## 6️⃣ Các loại Module
### 🔹 Named Module




## 🔟 Tóm tắt nhanh (để đi thi OCP)
- exports → compile + runtime
- opens → runtime reflection
- requires transitive → truyền dependency
- uses → ServiceLoader
- provides → service implementation
- Automatic module → export ALL
- Unnamed module → classpath
## 🎯 CHIẾN THUẬT ĐI THI OCP MODULES
- Vẽ dependency graph
- Xác định: compile vs runtime
- Tìm: exports / opens / transitive 
- Kiểm tra: module path hay classpath