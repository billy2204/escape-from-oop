# Escape from OOP - Complete UML Class Diagram

## Full Class Diagram with All Relationships

```plantuml
@startuml Escape-from-OOP

' Java Swing Framework Classes
package "javax.swing" {
    class JFrame {
        +setTitle(String)
        +setSize(int, int)
        +setVisible(boolean)
    }
    
    class JPanel {
        +paintComponent(Graphics)
        +add(Component)
    }
    
    class JButton {
        +setText(String)
        +addActionListener(ActionListener)
    }
}

' Application Entry Point
package "root" {
    class Application {
        +{static} main(String[] args)
        -{static} createAndShowGUI()
    }
}

' UI Package
package "ui" {
    class GameWindow {
        -GameController gameController
        -BackgroundPanel menuPanel
        -GamePanel gamePanel
        -int windowWidth
        -int windowHeight
        +GameWindow(int, int)
        -initializeWindow()
        -initializeComponents()
        +startGame()
    }
    
    class BackgroundPanel {
        -Image backgroundImage
        +BackgroundPanel(String imagePath)
        #paintComponent(Graphics)
    }
    
    class GamePanel {
        -Renderer renderer
        -Chest chest1
        +GamePanel()
        #paintComponent(Graphics)
    }
    
    class Button {
        +Button(String, int, int, int, int)
        -applyDefaultStyle()
    }
}

' Controllers Package
package "controllers" {
    class GameController {
        -GameWindow gameWindow
        +GameController(GameWindow)
        +startGame()
        +pauseGame()
        +resumeGame()
    }
}

' Graphics Package
package "graphics" {
    class Renderer {
        -configRender config
        +Renderer()
        +configAnimation(String, String, int)
        +draw(Graphics, Item, int, int, int, int)
    }
    
    class configRender {
        -List<Image> frames
        -int frameDelay
        -int currentFrame
        -long lastFrameTime
        +configRender()
        +config(String, String, int)
        -loadSprites(String, String)
        +getCurrentFrame(): Image
    }
}

' Components - Items
package "components.items" {
    abstract class Item {
        #int x
        #int y
        #String type
        #String state
        +Item(int, int, String)
        #getDefaultState(): String
        +getX(): int
        +getY(): int
        +getType(): String
        +getState(): String
        +update()
    }
    
    abstract class UnusableItem {
        +UnusableItem(int, int, String)
    }
    
    abstract class UsableItem {
        +UsableItem(int, int, String)
        +{abstract} interact()
    }
    
    class Chest {
        +Chest(int, int)
        +interact()
        +open()
        +close()
        +isClosed(): boolean
    }
    
    class Door {
        -boolean isLocked
        +Door(int, int)
        +interact()
        +open()
        +close()
        +lock()
        +unlock()
    }
    
    class Rock {
        +Rock(int, int)
        +update()
    }
}

' Components - Characters
package "components.characters" {
    abstract class Character {
        #int x
        #int y
        #int width
        #int height
        #String type
        #String state
        #int speed
        +Character(int, int, int, int, String)
        #{abstract} getDefaultState(): String
        #{abstract} getDefaultSpeed(): int
        +moveUp()
        +moveDown()
        +moveLeft()
        +moveRight()
        +update()
    }
    
    class Player {
        -int score
        -boolean isAlive
        +Player(int, int)
        +die()
        +isAlive(): boolean
        +addScore(int)
        +attack()
    }
    
    class Enemy {
        -int detectionRange
        +Enemy(int, int)
        +update()
        -patrol()
        -chasePlayer()
        +onContactWithPlayer(Player)
    }
}

' Physics Package (empty classes)
package "physics" {
    class hitBox {
    }
    
    class collision {
    }
    
    class wall {
    }
}

' ============ INHERITANCE RELATIONSHIPS ============

' Java Swing Inheritance
GameWindow --|> JFrame
BackgroundPanel --|> JPanel
GamePanel --|> JPanel
Button --|> JButton

' Item Hierarchy
UnusableItem --|> Item
UsableItem --|> Item
Chest --|> UsableItem
Door --|> UsableItem
Rock --|> UnusableItem

' Character Hierarchy
Player --|> Character
Enemy --|> Character

' ============ COMPOSITION/AGGREGATION ============

' Application creates GameWindow
Application ..> GameWindow : <<creates>>

' GameWindow contains panels and controller
GameWindow *-- BackgroundPanel : contains
GameWindow *-- GamePanel : contains
GameWindow o-- GameController : has
GameWindow ..> Button : <<uses>>

' GameController references GameWindow
GameController --> GameWindow : controls

' GamePanel uses Renderer and Items
GamePanel o-- Renderer : has
GamePanel o-- Chest : has

' Renderer uses configRender
Renderer *-- configRender : contains

' Renderer draws Items
Renderer ..> Item : <<draws>>

' Enemy interacts with Player
Enemy ..> Player : <<kills>>

@enduml
```

## Simplified Mermaid Diagram (Core Classes Only)

```mermaid
classDiagram
    %% Java Swing Base Classes
    class JFrame {
        <<Java Swing>>
    }
    class JPanel {
        <<Java Swing>>
    }
    class JButton {
        <<Java Swing>>
    }

    %% Main Application
    class Application {
        +main(String[] args)$
    }

    %% UI Layer
    class GameWindow {
        -GameController gameController
        -BackgroundPanel menuPanel
        -GamePanel gamePanel
        +startGame()
    }
    
    class BackgroundPanel {
        -Image backgroundImage
        +paintComponent(Graphics)
    }
    
    class GamePanel {
        -Renderer renderer
        -Chest chest1
        +paintComponent(Graphics)
    }
    
    class Button {
        +Button(String, int, int, int, int)
    }

    %% Controller
    class GameController {
        -GameWindow gameWindow
        +startGame()
        +pauseGame()
    }

    %% Graphics
    class Renderer {
        -configRender config
        +draw(Graphics, Item, int, int, int, int)
    }
    
    class configRender {
        -List~Image~ frames
        +getCurrentFrame() Image
    }

    %% Items
    class Item {
        <<abstract>>
        #int x, y
        #String type, state
        +getX() int
        +update()
    }
    
    class UsableItem {
        <<abstract>>
        +interact()*
    }
    
    class UnusableItem {
        <<abstract>>
    }
    
    class Chest {
        +open()
        +close()
    }
    
    class Door {
        -boolean isLocked
        +lock()
    }
    
    class Rock {
    }

    %% Characters
    class Character {
        <<abstract>>
        #int x, y, speed
        +moveUp()
        +update()
    }
    
    class Player {
        -boolean isAlive
        +die()
    }
    
    class Enemy {
        +onContactWithPlayer(Player)
    }

    %% Inheritance from Java Swing
    GameWindow --|> JFrame
    BackgroundPanel --|> JPanel
    GamePanel --|> JPanel
    Button --|> JButton

    %% Item Hierarchy
    UsableItem --|> Item
    UnusableItem --|> Item
    Chest --|> UsableItem
    Door --|> UsableItem
    Rock --|> UnusableItem

    %% Character Hierarchy
    Player --|> Character
    Enemy --|> Character

    %% Composition & Associations
    Application ..> GameWindow : creates
    GameWindow *-- BackgroundPanel
    GameWindow *-- GamePanel
    GameWindow o-- GameController
    GameController --> GameWindow
    GamePanel o-- Renderer
    GamePanel o-- Chest
    Renderer *-- configRender
    Renderer ..> Item : draws
    Enemy ..> Player : kills
```

## Architecture Layers

```
┌─────────────────────────────────────────────┐
│          Application (Entry Point)          │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│               UI Layer                      │
│  ┌─────────────────────────────────────┐   │
│  │ GameWindow (JFrame)                 │   │
│  │  ├─ BackgroundPanel (JPanel)        │   │
│  │  ├─ GamePanel (JPanel)              │   │
│  │  └─ Button (JButton)                │   │
│  └─────────────────────────────────────┘   │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│           Controller Layer                  │
│          GameController                     │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│           Graphics Layer                    │
│  Renderer + configRender                    │
└─────────────────┬───────────────────────────┘
                  │
┌─────────────────▼───────────────────────────┐
│          Components Layer                   │
│  ┌────────────────┬─────────────────┐       │
│  │   Characters   │     Items       │       │
│  │  ├─ Player     │  ├─ Chest       │       │
│  │  └─ Enemy      │  ├─ Door        │       │
│  │                │  └─ Rock        │       │
│  └────────────────┴─────────────────┘       │
└─────────────────────────────────────────────┘
```

## Recommendations for UML Tool

Để vẽ UML chính xác hơn, bạn nên dùng:

1. **PlantUML** (Khuyến nghị - code ở trên) 
   - Online: https://www.plantuml.com/plantuml/uml/
   - VS Code Extension: "PlantUML"
   
2. **draw.io** (Visual, dễ dùng)
   - https://app.diagrams.net/
   
3. **Lucidchart** (Professional)
   - https://www.lucidchart.com/

Mermaid có hạn chế về layout phức tạp, nên dùng PlantUML cho diagram đầy đủ hơn.
