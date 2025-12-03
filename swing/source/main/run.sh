#!/bin/bash

echo "Compiling..."
# Biên dịch các file java ở thư mục gốc và thư mục ui
javac *.java ui/*.java

# Kiểm tra nếu lệnh biên dịch thất bại (exit code khác 0)
if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    # Dừng màn hình để đọc lỗi
    read -p "Press Enter to exit..."
    exit 1
fi

echo "Running Application..."
# Chạy class chính
java Application

echo "Cleaning up..."
# Tìm và xóa tất cả file .class trong thư mục hiện tại và thư mục con
# 2>/dev/null dùng để ẩn lỗi nếu không tìm thấy file nào
find . -type f -name "*.class" -delete 2>/dev/null

echo "Done!"
read -p "Press Enter to finish..."