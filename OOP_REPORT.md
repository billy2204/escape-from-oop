# OOP Report: Escape from OOP

## 1. Introduction

We chose the top-down game for this project and it's called **"Escape from OOP"**. This game has functional gameplay, UI elements, and demonstrates core OOP concepts. The academic goals for this project are understanding Class/Object relationships, Memory Management, and Design Pattern application.

---

## 2. Development Tools

| Category | Technology |
|----------|------------|
| Programming Language | Java |
| Library/Framework | Java Swing |
| Build Tool | Manual compilation (javac) |
| IDE | Visual Studio Code |

---

## 3. Theoretical Background

### 3.1 Encapsulation
Data hiding using Access Modifiers (`private`, `protected`, `public`) and Getter/Setter methods.

**Example in our project:**
```java
// Entities.java
public abstract class Entities {
    private int x;           // private - hidden from outside
    private int y;           // private - hidden from outside
    private final String type;
    protected String state;  // protected - accessible by subclasses
    
    // Getters - controlled access
    public int getX() { return x; }
    public int getY() { return y; }
    
    // Setters - controlled modification
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}
```

```java
// Player.java
public class Player extends Character {
    private int health;      // private
    private boolean alive;   // private
    
    public int getHealth() { return health; }
    public boolean isAlive() { return alive; }
}
```

---

### 3.2 Inheritance
Code reusability via parent/child classes using `extends` keyword.

**Inheritance Hierarchy in our project:**

```
                    Entities (abstract)
                         │
          ┌──────────────┴──────────────┐
          │                             │
     Character                        Item
     (abstract)                    (abstract)
          │                             │
     ┌────┴────┐              ┌────────┼────────┐
     │         │              │        │        │
  Player    Enemy      UnusableItem  UsableItem
                        (abstract)   (abstract)
                                          │
                                    ┌─────┴─────┐
                                    │           │
                                  Chest       Door
```

**Java Swing Inheritance:**
```
JFrame ─────────> GameWindow
JPanel ─────────> BackgroundPanel, GamePanel
JButton ────────> Button
```

**Example:**
```java
// Character.java - Parent class
public abstract class Character {
    protected int x, y, speed;
    protected String state;
    
    public void moveUp() { y -= speed; }
    public void moveDown() { y += speed; }
    // Common behavior inherited by all characters
}

// Player.java - Child class
public class Player extends Character {
    private int health;
    
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) die();
    }
}

// Enemy.java - Child class
public class Enemy extends Character {
    private int detectionRange;
    
    public void onContactWithPlayer(Player player) {
        player.die();
    }
}
```

---

### 3.3 Polymorphism
Method overriding and dynamic binding - different behaviors for same method signature.

**Example in our project:**

```java
// Character.java - Template Method Pattern
public abstract class Character {
    // Abstract methods - MUST be overridden
    protected abstract String getDefaultState();
    protected abstract int getDefaultSpeed();
    
    // Can be overridden
    public void update() {
        // Template method - override in subclasses
    }
}

// Player.java - Override with different behavior
public class Player extends Character {
    @Override
    protected String getDefaultState() {
        return "idle";  // Player starts idle
    }
    
    @Override
    protected int getDefaultSpeed() {
        return 4;  // Player is faster
    }
    
    @Override
    public void update() {
        // Player-specific update logic
    }
}

// Enemy.java - Override with different behavior
public class Enemy extends Character {
    @Override
    protected String getDefaultState() {
        return "patrol";  // Enemy starts patrolling
    }
    
    @Override
    protected int getDefaultSpeed() {
        return 2;  // Enemy is slower
    }
    
    @Override
    public void update() {
        // AI behavior: patrol or chase
        if (state.equals("patrol")) {
            patrol();
        } else if (state.equals("chase")) {
            chasePlayer();
        }
    }
}
```

**Polymorphism in Rendering:**
```java
// Renderable.java - Interface
public interface Renderable {
    void render(Graphics2D g2);
    boolean isVisible();
    int getLayer();
}

// SpriteRenderer.java - Implementation 1
public class SpriteRenderer implements Renderable {
    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(sprite, drawX, drawY, drawWidth, drawHeight, null);
    }
}

// TextRenderer.java - Implementation 2
public class TextRenderer implements Renderable {
    @Override
    public void render(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(color);
        g2.drawString(text, (int)x, (int)y);
    }
}

// Renderer.java - Uses polymorphism
public void render(Graphics2D g2) {
    for (Renderable renderable : renderables) {
        if (renderable.isVisible()) {
            renderable.render(g2);  // Dynamic binding!
        }
    }
}
```

---

### 3.4 Abstraction
Using Abstract classes and Interfaces to define common protocols.

**Abstract Classes:**
```java
// Entities.java - Abstract base class
public abstract class Entities {
    // Common properties for all entities
    private int x, y;
    protected String type;
    protected String state;
    
    // Concrete methods
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
}

// Item.java - Abstract with Template Method
public abstract class Item {
    // Template method - can be overridden
    protected String getDefaultState() {
        return "idle";
    }
    
    // Abstract method in subclass
    public void update() {
        // Template method
    }
}

// UsableItem.java - Abstract with abstract method
abstract class UsableItem extends Item {
    public abstract void interact();  // MUST implement
}
```

**Interfaces:**
```java
// Renderable.java - Interface
public interface Renderable {
    void render(Graphics2D g2);
    boolean isVisible();
    int getLayer();
}

// initRenderer.java - Interface
public interface initRenderer {
    void render(Graphics2D g2);
    void setPanel(JPanel panel);
}

// InputController.java - Interface
public interface InputController {
    boolean isRequestingUp();
    boolean isRequestingDown();
    boolean isRequestingLeft();
    boolean isRequestingRight();
    boolean isRelease();
}
```

---

## 4. Design Patterns Applied

### 4.1 Single Responsibility Principle (SRP)
Each class has ONE and only ONE responsibility.

| Class | Single Responsibility |
|-------|----------------------|
| `Renderer` | Only handles rendering graphics |
| `AnimationController` | Only manages animation frames |
| `GameController` | Only manages game logic |
| `Input` | Only captures keyboard input |
| `keyController` | Only interprets key presses as movement commands |
| `Button` | Only manages button display and styling |

### 4.2 Open/Closed Principle (OCP)
Open for extension, closed for modification.

```java
// Can add new Renderable types without modifying Renderer
public void addRenderable(Renderable renderable) {
    renderables.add(renderable);
}

// Can add new animation states without modifying AnimationController
public void addAnimation(String state, BufferedImage[] frames) {
    animations.put(state, frames);
}
```

### 4.3 Liskov Substitution Principle (LSP)
Subclasses can replace parent classes.

```java
// Any Character subclass works with the same interface
Character character = new Player(0, 0);
character.moveUp();    // Works!
character.update();    // Works!

character = new Enemy(0, 0);
character.moveUp();    // Works!
character.update();    // Works!
```

### 4.4 Interface Segregation Principle (ISP)
Multiple focused interfaces instead of one large interface.

```java
// Separate interfaces for separate concerns
interface Renderable { ... }      // For rendering
interface InputController { ... } // For input
interface initRenderer { ... }    // For renderer initialization
```

### 4.5 Dependency Inversion Principle (DIP)
Depend on abstractions, not concrete implementations.

```java
// initKeyController depends on interface, not concrete class
public class initKeyController {
    private InputController inputController;  // Interface!
    
    public initKeyController(InputController inputController) {
        this.inputController = inputController;
    }
}

// Renderer implements interface
public class Renderer implements initRenderer { ... }
```

### 4.6 Template Method Pattern
Define algorithm skeleton in parent, let subclasses override steps.

```java
// Character defines template
public abstract class Character {
    public Character(...) {
        this.state = getDefaultState();   // Template method
        this.speed = getDefaultSpeed();   // Template method
    }
    
    protected abstract String getDefaultState();
    protected abstract int getDefaultSpeed();
    public void update() { }  // Hook method
}

// Subclasses fill in the blanks
public class Player extends Character {
    @Override protected String getDefaultState() { return "idle"; }
    @Override protected int getDefaultSpeed() { return 4; }
}
```

---

## 5. Project Structure

```
escape-from-oop/
│
├── swing/source/main/
│   ├── Application.java              # Entry point
│   ├── run.bat                        # Windows build script
│   ├── run.sh                         # macOS/Linux build script
│   │
│   ├── Entities/                      # Game entities
│   │   ├── Entities.java              # Base abstract class
│   │   ├── Characters/
│   │   │   ├── Character.java         # Abstract character
│   │   │   ├── Player.java            # Player (has health)
│   │   │   └── Enemy.java             # Enemy (kills player)
│   │   └── items/
│   │       ├── Item.java              # Abstract item
│   │       ├── Chest.java             # Openable chest
│   │       └── Door.java              # Lockable door
│   │
│   ├── controllers/                   # Game logic
│   │   ├── GameController.java        # Main game controller
│   │   ├── AnimationController.java   # Animation management
│   │   ├── Input.java                 # Keyboard listener
│   │   ├── InputController.java       # Input interface
│   │   ├── keyController.java         # Key to command mapping
│   │   └── initKeyController.java     # Input initialization
│   │
│   ├── graphics/                      # Rendering system
│   │   ├── Renderable.java            # Render interface
│   │   ├── initRenderer.java          # Renderer interface
│   │   ├── Renderer.java              # Main renderer
│   │   ├── SpriteRenderer.java        # Sprite rendering
│   │   └── TextRenderer.java          # Text rendering
│   │
│   ├── ui/                            # User interface
│   │   ├── GameWindow.java            # Main window (JFrame)
│   │   ├── GamePanel.java             # Game screen (JPanel)
│   │   ├── BackgroundPanel.java       # Menu background
│   │   └── Button.java                # Custom button
│   │
│   ├── physics/                       # Physics (TODO)
│   │
│   └── resources/                     # Game assets
│       ├── backGround.png
│       ├── characters/
│       └── items/
│           └── chest_idle/
│               ├── 1.png, 2.png, 3.png, 4.png
│
└── README.md
```

---

## 6. UML Class Diagram

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                              JAVA SWING FRAMEWORK                           │
├────────────────────┬────────────────────┬───────────────────────────────────┤
│      JFrame        │      JPanel        │           JButton                 │
└────────┬───────────┴─────────┬──────────┴────────────────┬──────────────────┘
         │                     │                           │
         ▼                     ▼                           ▼
┌────────────────┐   ┌─────────────────────┐      ┌───────────────┐
│  GameWindow    │   │  BackgroundPanel    │      │    Button     │
│────────────────│   │  GamePanel          │      │───────────────│
│-menuPanel      │   │─────────────────────│      │+applyDefault()|
│-gamePanel      │   │-backgroundImage     │      └───────────────┘
│-gameController │   │-renderer            │
│+startGame()    │   │+paintComponent()    │
└────────────────┘   └─────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│                              ENTITIES HIERARCHY                             │
├─────────────────────────────────────────────────────────────────────────────┤
│                          <<abstract>> Entities                              │
│                    ┌────────────────────────────┐                           │
│                    │ -x, -y: int                │                           │
│                    │ -type: String              │                           │
│                    │ #state: String             │                           │
│                    │ +getX(), setX()            │                           │
│                    └──────────────┬─────────────┘                           │
│                                   │                                         │
│              ┌────────────────────┴────────────────────┐                    │
│              ▼                                         ▼                    │
│    <<abstract>> Character                    <<abstract>> Item              │
│   ┌─────────────────────┐                  ┌─────────────────────┐          │
│   │ #width, height      │                  │ +getDefaultState()  │          │
│   │ #speed              │                  │ +update()           │          │
│   │ +moveUp/Down/L/R()  │                  └──────────┬──────────┘          │
│   │ +update()           │                             │                     │
│   └──────────┬──────────┘              ┌──────────────┴──────────────┐      │
│              │                         ▼                             ▼      │
│      ┌───────┴───────┐       <<abstract>>              <<abstract>>         │
│      ▼               ▼       UnusableItem              UsableItem           │
│   ┌───────┐    ┌─────────┐                         ┌─────────────────┐      │
│   │Player │    │ Enemy   │                         │+interact()      │      │
│   │───────│    │─────────│                         └────────┬────────┘      │
│   │-health│    │-detect  │                                  │               │
│   │-alive │    │Range    │                    ┌─────────────┴─────────────┐ │
│   │+die() │    │+onCont  │                    ▼                           ▼ │
│   │+take  │    │ act()   │               ┌─────────┐              ┌─────────┐│
│   │Damage │    │+patrol()│               │  Chest  │              │  Door   ││
│   └───────┘    └─────────┘               │─────────│              │─────────││
│                                          │+open()  │              │-isLocked││
│                                          │+close() │              │+lock()  ││
│                                          └─────────┘              │+unlock()││
│                                                                   └─────────┘│
└─────────────────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────────────────┐
│                              INTERFACES                                     │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  <<interface>>              <<interface>>           <<interface>>           │
│   Renderable                 initRenderer           InputController         │
│ ┌───────────────┐          ┌───────────────┐      ┌───────────────┐         │
│ │+render(g2)    │          │+render(g2)    │      │+isRequestUp() │         │
│ │+isVisible()   │          │+setPanel()    │      │+isRequestDown│         │
│ │+getLayer()    │          └───────┬───────┘      │+isRequestL/R()│         │
│ └───────┬───────┘                  │              └───────┬───────┘         │
│         │                          │                      │                 │
│    ┌────┴────┐                     │                      │                 │
│    ▼         ▼                     ▼                      ▼                 │
│ Sprite   Text                  Renderer             keyController          │
│ Renderer Renderer                                                           │
└─────────────────────────────────────────────────────────────────────────────┘
```

---

## 7. Memory Management

### Object Creation
```java
// Objects created on heap
Player player = new Player(100, 100);
Enemy enemy = new Enemy(200, 200);
Chest chest = new Chest(300, 300);

// Reference variables on stack, objects on heap
```

### Garbage Collection
Java's automatic garbage collection handles memory deallocation when objects are no longer referenced.

```java
// When object is no longer referenced, GC will collect it
enemy = null;  // Enemy object eligible for garbage collection
```

### Resource Management
```java
// Timer for animation - should be stopped when panel is destroyed
Timer animationTimer = new Timer(50, e -> repaint());
animationTimer.start();
// TODO: animationTimer.stop() when GamePanel is closed
```

---

## 8. Conclusion

This project demonstrates the four pillars of Object-Oriented Programming:

1. **Encapsulation**: Private fields with getters/setters across all entity classes
2. **Inheritance**: Deep class hierarchies (Entities → Character → Player/Enemy)
3. **Polymorphism**: Method overriding in Character subclasses, Renderable implementations
4. **Abstraction**: Abstract classes (Entities, Character, Item) and Interfaces (Renderable, InputController)

Additionally, we applied SOLID design principles and the Template Method pattern to create maintainable, extensible code.

---

## 9. Team Members

| Name | Role |
|------|------|
| Billy | Developer |

---

## 10. References

- Java Documentation: https://docs.oracle.com/javase/8/docs/api/
- Java Swing Tutorial: https://docs.oracle.com/javase/tutorial/uiswing/
- Design Patterns: Gang of Four
