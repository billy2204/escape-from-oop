<<<<<<< HEAD
# escape-from-oop
OOP Lab Project
=======
# Escape from OOP

A Java Swing-based 2D game project demonstrating Object-Oriented Programming principles and SOLID design patterns.

## 📁 Project Structure

```
escape-from-oop/
│
├── swing/
│   └── source/
│       └── main/
│           ├── Application.java          # Main entry point
│           ├── run.bat                   # Windows build & run script
│           ├── run.sh                    # macOS/Linux build & run script
│           │
│           ├── components/               # Game entities
│           │   ├── characters/
│           │   │   ├── Character.java    # Base character class
│           │   │   ├── Player.java       # Player character (1 health)
│           │   │   └── Enemy.java        # Enemy character (instant kill)
│           │   │
│           │   └── items/
│           │       ├── Item.java         # Base item class
│           │       ├── Chest.java        # Interactive chest
│           │       ├── Door.java         # Interactive door
│           │       └── Rock.java         # Static obstacle
│           │
│           ├── controllers/              # Game logic controllers
│           │   └── GameController.java
│           │
│           ├── graphics/                 # Rendering system
│           │   ├── Renderer.java         # Main renderer
│           │   └── configRender.java     # Animation config
│           │
│           ├── physics/                  # Physics & collision
│           │   ├── collision.java
│           │   ├── hitBox.java
│           │   └── wall.java
│           │
│           ├── ui/                       # User interface
│           │   ├── GameWindow.java       # Main window
│           │   ├── GamePanel.java        # Game screen
│           │   ├── BackgroundPanel.java  # Menu background
│           │   └── Button.java           # Custom button
│           │
│           └── resources/                # Game assets
│               ├── backGround.png
│               ├── characters/
│               └── items/
│                   └── chest_idle/
│                       ├── 1.png
│                       ├── 2.png
│                       ├── 3.png
│                       └── 4.png
│
└── README.md
```

## 🚀 How to Run

### Prerequisites
- **Java Development Kit (JDK)** 8 or higher
- No additional dependencies required (pure Java Swing)

### Windows

1. Navigate to the main source directory:
```cmd
cd swing\source\main
```

2. Run the batch script:
```cmd
run.bat
```

The script will:
- Compile all Java files
- Run the application
- Clean up `.class` files after execution

### macOS / Linux

1. Navigate to the main source directory:
```bash
cd swing/source/main
```

2. Make the script executable (first time only):
```bash
chmod +x run.sh
```

3. Run the shell script:
```bash
./run.sh
```

The script will:
- Compile all Java files
- Run the application
- Clean up `.class` files after execution

## 🎮 Game Features

- **Player Character**: Single life gameplay (instant death on enemy contact)
- **Enemy AI**: Patrol and chase behavior
- **Interactive Items**: Chests, doors, and obstacles
- **Animated Sprites**: Frame-based animation system
- **Custom Renderer**: Flexible rendering engine with configurable animations

## 🏗️ Architecture & Design Patterns

This project follows **SOLID principles**:

### Single Responsibility Principle (SRP)
- Each class has one clear responsibility
- `Renderer` only handles drawing
- `configRender` only manages animation frames
- `GameController` only manages game logic

### Open/Closed Principle (OCP)
- `Character` → `Player` / `Enemy` (extendable)
- `Item` → `UsableItem` / `UnusableItem` (extendable)

### Liskov Substitution Principle (LSP)
- All subclasses can replace their parent classes
- `Renderer.draw()` accepts any `Item` subclass

### Template Method Pattern
- `Character.getDefaultState()`
- `Item.getDefaultState()`
- `Character.update()` and `Item.update()`

## 🎨 Adding New Content

### Adding a New Item
```java
package components.items;

public class MyItem extends UsableItem {
    public MyItem(int x, int y) {
        super(x, y, "myitem");
    }
    
    @Override
    public void interact() {
        // Define interaction logic
    }
}
```

### Adding a New Character
```java
package components.characters;

public class MyCharacter extends Character {
    public MyCharacter(int x, int y) {
        super(x, y, 32, 32, "mycharacter");
    }
    
    @Override
    protected String getDefaultState() {
        return "idle";
    }
    
    @Override
    protected int getDefaultSpeed() {
        return 3;
    }
}
```

## 📝 Controls

- **Arrow Keys / WASD**: Move player (to be implemented)
- **ESC**: Return to menu

## 🛠️ Development

### Compile Only
```bash
javac -d . Application.java ui/*.java controllers/*.java components/*/*.java graphics/*.java
```

### Run Only (after compilation)
```bash
java Application
```

## 📄 License

This project is for educational purposes.

## 👥 Contributors

- Billy (billy2204)

---

**Note**: Make sure to place sprite assets in the correct `resources/` folders before running the game.
>>>>>>> 63f6d10 (First frame of game)
