# projectOOP

## Tổng quan
Escape from OOP là một trò chơi 2D nhỏ được viết bằng Java (sử dụng Swing cho giao diện). Mục tiêu của dự án là minh họa các khái niệm OOP, SOLID và các design pattern cơ bản trong một game đơn giản (game loop, entity system, animation, vật lý cơ bản và va chạm).

## Công nghệ & framework
- Ngôn ngữ: Java 8+ (hoặc tương thích) 
- UI: Swing (AWT/Swing) — sử dụng `JFrame` và `JPanel` để hiển thị và nhận input
- Thư viện: Không sử dụng thư viện bên ngoài; hình ảnh và tài nguyên lưu trong thư mục `resources/`.

## Cấu trúc chính
- `core/` — Game loop, `Game`, `GamePanel`, `PhysicsSystem`, `CollisionManager`, `MapManager`.
- `entities/` — Lớp trừu tượng `Entity` và các subclass (Player, Enemy, items).
- `physics/` — `RigidBody`, `Transform`, `BoxCollider`, `Vector2D`.
- `graphics/animation` — `AnimationManager`, `AnimationRegistry`, `ResourceManager`.
- `input/` — Input handling (`KeyboardInput`, `MouseInput`).
- `resources/` — Hình ảnh, âm thanh, map.

## Hướng dẫn chạy (Windows)
1. Đảm bảo bạn có JDK (Java) đã cài, phiên bản 8 hoặc mới hơn. Kiểm tra bằng:
   ```bash
   java -version
   ```
2. Mở terminal (cmd) tại thư mục gốc `projectOOP`.
3. Biên dịch mã nguồn:
   ```bash
   javac -d bin $(find . -name "*.java")
   ```
   Lưu ý: Nếu `find` không hoạt động trên Windows `cmd`, bạn có thể dùng PowerShell hoặc biên dịch bằng IDE (IntelliJ/NetBeans/Eclipse). Một cách đơn giản là mở project trong IDE và run `main.java`.
4. Chạy game:
   - Dùng script Windows:
     ```cmd
     run.bat
     ```
   - Hoặc dùng lệnh Java trực tiếp (nếu đã biên dịch vào `bin`):
     ```bash
     java -cp bin main
     ```

## Phím điều khiển
- WASD / Phím mũi tên: di chuyển
- Space: bắt đầu/nhảy (theo thiết kế game)
- T: bật/tắt hiển thị tường (debug)
- R: restart khi game over
- ESC: quay về menu

## Ghi chú phát triển
- Thêm entity mới: tạo subclass của `entities.Entity` và cấu hình animation trong `graphics/animation/AnimationRegistry.java`.
- Để unit test logic vật lý hoặc va chạm, cân nhắc tách các phụ thuộc (ví dụ `MapManager`, `CollisionManager`) bằng interfaces để dễ mock.

## Liên hệ
Nếu cần trợ giúp chi tiết (ví dụ tạo script build phù hợp Windows, thêm file .bat hoặc nâng cấp sang Maven/Gradle), cho biết tôi sẽ hỗ trợ tiếp.
