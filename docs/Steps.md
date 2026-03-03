# 🧠 Quy Trình 6 Bước Xử Lý 1 Câu Hỏi OCP Java 21
## 🎯 Mục tiêu
Giải câu hỏi theo tư duy hệ thống, tránh bẫy compile-time và runtime.

## ✅ BƯỚC 1 — Hỏi: Có compile được không?
Trước khi nghĩ tới output, kiểm tra:

- Có lỗi cú pháp không?
- Access modifier có hợp lệ không?
- Override đúng rule không?
- Static có bị override nhầm không?
- Constructor có hợp lệ không?
- Generics có đúng type không?
- Switch expression có exhaustive không?
- Lambda có ném checked exception không?
> ❗ Nếu không compile → dừng phân tích runtime.

## ✅ BƯỚC 2 — Kiểm tra package & access modifier
Nhìn kỹ:
- Có nhiều `package` khác nhau không?
- `protected` có bị dùng sai package không?
- Class có public không?
- Có default access bị chặn không?
> 🔥 Khác package là bẫy rất phổ biến.
---

## ✅ BƯỚC 3 — Phân biệt Field vs Method vs Static
Phải tách rõ:
- Field → KHÔNG polymorphic
- Instance method → polymorphic
- Static method → method hiding (resolve theo reference type)
> ❗ Nhầm 3 cái này là sai ngay.

## ✅ BƯỚC 4 — Kiểm tra thứ tự khởi tạo (nếu có inheritance)
🎯 GIAI ĐOẠN 1 — Class Initialization (chỉ chạy 1 lần)
Khi class được load lần đầu:
1. Super class static fields
2. Super class static blocks
3. Sub class static fields
4. Sub class static blocks

🎯 GIAI ĐOẠN 2 — Object Creation (mỗi lần new)
Khi gọi new:
1. Default values
2. Super instance fields
3. Super instance initializer blocks
4. Super constructor
5. Sub instance fields
6. Sub instance initializer blocks
7. Sub constructor

## ✅ BƯỚC 5 — Kiểm tra tính bất biến (immutability)
Nếu có các class sau:
- String
- LocalDate / LocalTime / LocalDateTime
- Period / Duration
- Optional
- Stream
- Record

Hỏi:
- Có gán lại kết quả không?
- Có gọi terminal operation với Stream không?
> ❗ Rất nhiều câu sai vì quên assign lại.
---
## ✅ BƯỚC 6 — Đọc lại chính xác câu hỏi
Đề hỏi gì?
- What is the output?
- How many compile errors?
- Which statement is true?
- Which line fails to compile?
Kiểm tra:
- Có nhiều đáp án đúng không?
- Có yêu cầu chọn 2 đáp án không?
- Có hỏi runtime exception không?
> 🎯 Sai 1 chữ = chọn sai đáp án.
---
# 🏆 Công Thức Tổng Kết
OCP = Rule + Attention to Detail + Bình tĩnh
Làm theo đúng 6 bước → giảm 80% lỗi do hấp tấp.