#!/bin/bash

# 1. Dọn dẹp màn hình
clear
echo "=== Auto-Building Java Project ==="

# 2. Tìm TẤT CẢ file .java từ thư mục này trở xuống
# và lưu danh sách vào file tạm tên là sources.txt
find . -name "*.java" > sources.txt

# Kiểm tra xem có tìm thấy file code nào không
if [ ! -s sources.txt ]; then
    echo "Error: Không tìm thấy file .java nào ở thư mục này!"
    echo "Hãy chắc chắn bạn đang để run.sh cùng chỗ với mã nguồn."
    rm sources.txt
    exit 1
fi

# 3. Biên dịch dựa trên danh sách tìm được
echo "Compiling..."
javac @sources.txt

# 4. Kiểm tra lỗi biên dịch
if [ $? -ne 0 ]; then
    echo "Compilation failed! (Biên dịch lỗi)"
    rm sources.txt
    rm -f *.class
    # Lệnh xóa class đệ quy cho macOS
    find . -name "*.class" -type f -delete
    exit 1
else
    echo "Compilation successful! (Thành công)"
    echo "Running Application..."
    
    # --- CHÚ Ý ---
    # Thay 'Application' bằng tên class chính của bạn nếu cần
    # Nếu Application nằm trong package (ví dụ package main;), 
    # bạn phải sửa dòng dưới thành: java main.Application
    java Application
fi

# 5. Dọn dẹp
rm sources.txt
rm -f *.class
# Lệnh xóa class đệ quy cho macOS
find . -name "*.class" -type f -delete
echo "Done."
