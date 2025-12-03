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
│           ├── interfaces/               # All interfaces (SOLID - ISP)
│           │   ├── IRenderable.java      # For renderable objects
│           │   ├── IUpdatable.java       # For objects that update each frame
│           │   ├── ICollidable.java      # For collision detection
│           │   ├── IAnimatable.java      # For animated objects
│           │   ├── ISpriteLoader.java    # For loading sprites
│           │   └── IInputHandler.java    # For input handling
│           │
│           ├── entities/                 # Game entities
│           │   ├── Entity.java           # Base entity class
│           │   ├── characters/
│           │   │   ├── Character.java    # Base character class
│           │   │   ├── Player.java       # Player with health
│           │   │   └── Enemy.java        # Enemy with AI
│           │   └── items/
│           │       ├── Item.java         # Base item class
│           │       ├── UsableItem.java   # Interactive items
│           │       ├── Chest.java        # Openable chest
│           │       └── Door.java         # Lockable door
│           │
│           ├── input/                    # Input handling
│           │   └── KeyboardInput.java    # Keyboard listener
│           │
│           ├── managers/                 # Game managers
│           │   ├── GameManager.java      # Main game controller
│           │   ├── EntityManager.java    # Entity lifecycle
│           │   └── InputManager.java     # Input processing
│           │
│           ├── ui/                       # User interface
│           │   ├── GameWindow.java       # Main window with CardLayout
│           │   ├── GamePanel.java        # Game rendering & loop
│           │   └── MenuPanel.java        # Main menu
│           │
│           └── resources/                # Game assets
│               ├── characters/
│               ├── items/
│               └── map/
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

### macOS / Linux

1. Navigate to the main source directory:
```bash
cd swing/source/main
```

2. Make the script executable:
```bash
chmod +x run.sh
```

3. Run:
```bash
./run.sh
```

## 🎮 Game Features

- **Player Character**: Health-based survival (100 HP)
- **Enemy AI**: Patrol → Chase → Attack behavior states
- **Interactive Items**: Chests (open/close) and Doors (lock/unlock)
- **Collision System**: Entity-based collision detection
- **Game States**: Menu, Playing, Paused, Game Over

## 🏗️ Architecture & SOLID Principles

### Single Responsibility Principle (SRP)
- `EntityManager` → Only manages entity lifecycle
- `InputManager` → Only processes input
- `GameManager` → Coordinates managers

### Open/Closed Principle (OCP)
- `Entity` base class can be extended without modification
- `Character` → `Player`, `Enemy`
- `Item` → `UsableItem` → `Chest`, `Door`

### Liskov Substitution Principle (LSP)
- All entities can be used interchangeably in `EntityManager`
- All items can trigger collision with `ICollidable`

### Interface Segregation Principle (ISP)
- `IRenderable` - for rendering
- `IUpdatable` - for game loop updates
- `ICollidable` - for collision
- `IAnimatable` - for animation

### Dependency Inversion Principle (DIP)
- High-level modules depend on interfaces, not concrete implementations
- `InputManager` uses `IInputHandler` interface

### Design Patterns Used
- **Template Method**: `Character.getDefaultSpeed()`, `Entity.getDefaultState()`
- **Singleton**: `GameManager.getInstance()`
- **State Pattern**: `Enemy.AIState` (IDLE, PATROL, CHASE, ATTACK)
- **Observer Pattern**: KeyListener for input events

## 🎨 Adding New Content

### Adding a New Entity
```java
package entities;

public class MyEntity extends Entity {
    public MyEntity(int x, int y) {
        super(x, y, 32, 32);
    }
    
    @Override
    protected String getDefaultState() { return "idle"; }
    
    @Override
    public void update() { /* logic */ }
    
    @Override
    public void render(Graphics2D g2) { /* drawing */ }
    
    @Override
    public void onCollision(ICollidable other) { /* collision */ }
    
    @Override
    public void updateAnimation() { /* animation */ }
}
```

## 📝 Controls

- **WASD / Arrow Keys**: Move player
- **Space**: Action/Interact
- **ESC**: Pause/Menu

## 📄 License

This project is for educational purposes.

## 👥 Contributors

- Billy (billy2204)
