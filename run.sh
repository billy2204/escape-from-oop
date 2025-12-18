#!/bin/bash

# 1. Tự động chuyển vào thư mục chứa file script này
# Giúp chạy đúng dù folder tên là "OOP 2" hay gì đi nữa
cd "$(dirname "$0")"

echo "=== PRE-CLEANING ==="
# Xóa file .class cũ trước khi chạy để đảm bảo sạch sẽ
find . -name "*.class" -type f -delete

echo "=== COMPILING main.java ==="
# Compile main.java (tự động compile luôn các file phụ thuộc trong subfolder)
javac main.java

# Kiểm tra lỗi biên dịch
if [ $? -ne 0 ]; then
  echo "❌ Compilation failed."
  read -n 1 -s -r -p "Press any key to exit..."
  exit 1
fi

echo "=== RUNNING main ==="
# Chạy file main
java main

echo ""
echo "=== CLEANING UP ==="
echo "Deleting all .class files recursively..."

# Lệnh FIND này sẽ quét từ thư mục hiện tại (.)
# Tìm tất cả file có đuôi .class và xóa sạch
find . -name "*.class" -type f -delete

echo "✅ Done."
read -n 1 -s -r -p "Press any key to close..."