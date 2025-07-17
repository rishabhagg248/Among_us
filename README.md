# Among Us Game 🚀👾

A Java-based interactive Among Us game simulation built with Processing. Features drag-and-drop gameplay, impostor mechanics, and colorful crewmate characters in a space station environment.

## 🌟 Features

- **Interactive Gameplay** - Click and drag crewmates around the space station
- **Impostor System** - One randomly selected impostor eliminates other players
- **Dynamic Spawning** - Press 'A' to spawn new crewmates at random locations
- **Collision Detection** - Advanced overlap detection between characters
- **Visual Feedback** - Colorful sprites with smooth animations
- **Random Generation** - Procedurally generated colors and positions
- **Real-time Elimination** - Crewmates are eliminated when touched by impostor

## 🚀 Quick Start

### Prerequisites

- Java 8 or higher
- Processing library (included in JAR)
- Images folder with game assets

### Installation

1. **Download the game files:**
```bash
git clone https://github.com/yourusername/among-us-game.git
cd among-us-game
```

2. **Ensure required files are present:**
```
among-us-game/
├── SpaceStation.java
├── Amogus.java (assumed)
├── Utility.java (assumed)
├── processing-library.jar
└── images/
    ├── background.jpeg
    └── sprite1.png
```

3. **Compile and run:**
```bash
javac -cp processing-library.jar SpaceStation.java
java -cp .:processing-library.jar SpaceStation
```

## 🎮 How to Play

### Controls
- **Mouse Click + Drag**: Move crewmates around the space station
- **Press 'A'**: Spawn a new crewmate at random location
- **Mouse Release**: Stop dragging current crewmate

### Gameplay Mechanics

#### Objective
- **Crewmates**: Survive and avoid the impostor
- **Impostor**: Eliminate all crewmates by touching them

#### Game Flow
1. Game starts with one crewmate and randomly selects an impostor index
2. Press 'A' to spawn additional crewmates (max 8 total)
3. Drag crewmates to move them around
4. When impostor touches any crewmate, they are eliminated
5. Game continues until all crewmates are eliminated

## 🏗️ Technical Architecture

### Core Components

#### 1. **SpaceStation Class** (Main Game Logic)
```java
public class SpaceStation {
    private static PImage background;      // Background image
    private static PImage sprite;          // Sprite dimensions reference
    private static Amogus[] players;       // Array of all players
    private static int NUM_PLAYERS = 8;    // Maximum players
    private static int impostorIndex;      // Index of the impostor
}
```

#### 2. **Game Loop Structure**
```java
setup()      // Initialize game objects (called once)
draw()       // Continuous rendering and game logic
keyPressed() // Handle 'A' key for spawning
mousePressed() // Start dragging characters
mouseReleased() // Stop dragging characters
```

### Key Algorithms

#### Collision Detection
```java
public static boolean overlap(Amogus amogus1, Amogus amogus2) {
    // Calculate edge coordinates for both characters
    float rightEdge1 = amogus1X + (sprite.width/2);
    float leftEdge1 = amogus1X - (sprite.width/2);
    
    // Check 4 overlap cases: top-right, top-left, bottom-right, bottom-left
    // Returns true if any overlap is detected
}
```

#### Mouse Interaction
```java
public static boolean isMouseOver(Amogus amogus) {
    // Get character boundaries
    float rightEdge = amogusX + (sprite.width/2);
    float leftEdge = amogusX - (sprite.width/2);
    
    // Check if mouse coordinates are within character bounds
    return (mouseX > leftEdge && mouseX < rightEdge && 
            mouseY > top && mouseY < bottom);
}
```

## 🎨 Visual Design

### Graphics System
- **Background**: Space station environment
- **Sprites**: Colorful Among Us characters
- **Rendering**: Processing library for smooth graphics

### Character System
- **Colors**: 3 different character colors (randomly assigned)
- **Positioning**: Random spawn locations within screen bounds
- **Animation**: Smooth drag-and-drop movement

### Screen Layout
- **Resolution**: 1200x800 pixels
- **Background**: Centered at (600, 500)
- **Spawn Area**: Full screen (0-1200, 0-800)

## 🔧 Configuration

### Game Parameters

#### Player Settings
```java
private static int NUM_PLAYERS = 8;        // Maximum players
private static int impostorIndex;          // Impostor position (1-7)
```

#### Spawn Mechanics
```java
// Random position generation
float randomPlayerX = Utility.randGen.nextInt(1201);  // 0-1200
float randomPlayerY = Utility.randGen.nextInt(801);   // 0-800
int randomPlayerColor = Utility.randGen.nextInt(3)+1; // 1-3
```

### Customization Options

#### Change Maximum Players
```java
private static int NUM_PLAYERS = 12; // Increase to 12 players
```

#### Modify Spawn Key
```java
char spawnKey = 115; // Change to 's' key (ASCII 115)
if(Utility.key() == spawnKey) {
    // Spawn logic
}
```

#### Add More Colors
```java
int randomPlayerColor = Utility.randGen.nextInt(5)+1; // 1-5 colors
```

## 🛠️ Development

### Project Structure

```
SpaceStation.java           # Main game class
├── setup()                # Game initialization
├── draw()                 # Main game loop
├── keyPressed()           # Input handling
├── mousePressed()         # Mouse interaction start
├── mouseReleased()        # Mouse interaction end
├── isMouseOver()          # Mouse collision detection
├── overlap()              # Character collision detection
└── main()                 # Application entry point
```

### Dependencies

#### Required Classes (Assumed)
```java
class Amogus {
    // Character representation
    public void draw()            // Render character
    public void startDragging()   // Begin drag operation
    public void stopDragging()    // End drag operation
    public void unalive()         // Eliminate character
    public float getX()           // Get X position
    public float getY()           // Get Y position
}

class Utility {
    // Processing wrapper utilities
    public static void runApplication()  // Start game
    public static void image()           // Draw image
    public static PImage loadImage()     // Load image file
    public static char key()             // Get pressed key
    public static int mouseX()           // Get mouse X
    public static int mouseY()           // Get mouse Y
}
```

### Adding New Features

#### Power-ups
```java
class PowerUp {
    private float x, y;
    private int type; // speed boost, shield, etc.
    
    public void draw() {
        // Render power-up
    }
    
    public boolean isCollected(Amogus player) {
        // Check if player collected power-up
    }
}
```

#### Game Timer
```java
private static long gameStartTime;
private static long currentTime;

public static void setup() {
    gameStartTime = System.currentTimeMillis();
}

public static void draw() {
    currentTime = System.currentTimeMillis();
    long elapsedTime = currentTime - gameStartTime;
    // Display timer
}
```

#### Score System
```java
private static int score = 0;
private static int survivorsCount = 0;

public static void updateScore() {
    survivorsCount = 0;
    for(Amogus player : players) {
        if(player != null && player.isAlive()) {
            survivorsCount++;
        }
    }
    score = survivorsCount * 10; // 10 points per survivor
}
```

## 🎯 Game Mechanics Deep Dive

### Impostor Selection
- Random selection from indices 1-7 (index 0 is first player)
- Impostor identity hidden from visual indicators
- Console output shows impostor index for debugging

### Elimination System
- Continuous collision checking in draw() loop
- Instant elimination on contact with impostor
- No respawn mechanism (permanent elimination)

### Spawning Mechanics
- Maximum 8 players total
- Random color assignment (1-3)
- Random position within screen bounds
- Fills first available slot in players array

## 🐛 Troubleshooting

### Common Issues

1. **Images Not Loading**
   ```bash
   # Ensure images folder structure:
   images/
   ├── background.jpeg
   └── sprite1.png
   ```

2. **Compilation Errors**
   ```bash
   # Check Processing library in classpath
   javac -cp processing-library.jar:. SpaceStation.java
   ```

3. **Game Not Starting**
   ```bash
   # Verify main method execution
   java -cp .:processing-library.jar SpaceStation
   ```

### Debug Features

#### Console Output
```java
System.out.print("impostor index = " + impostorIndex);
```

#### Add Debug Information
```java
public static void draw() {
    // Add debug overlay
    System.out.println("Active players: " + countActivePlayers());
    System.out.println("Impostor active: " + (players[impostorIndex] != null));
}
```

## 🎮 Gameplay Tips

### For Players
1. **Spawn Strategy**: Press 'A' multiple times to create more crewmates
2. **Movement**: Use mouse to drag crewmates away from danger
3. **Survival**: Keep crewmates spread out to avoid mass elimination
4. **Awareness**: Watch for impostor behavior patterns

### For Developers
1. **Testing**: Use console output to track impostor
2. **Balancing**: Adjust NUM_PLAYERS for difficulty
3. **Debugging**: Add visual indicators for impostor
4. **Enhancement**: Consider adding safe zones or objectives

## 🔮 Future Enhancements

### Planned Features
- [ ] Win/lose conditions
- [ ] Multiple impostors
- [ ] Power-ups and abilities
- [ ] Sound effects and music
- [ ] Animated sprite sheets
- [ ] Particle effects for eliminations
- [ ] Minimap display
- [ ] Save/load game states

### Advanced Features
- [ ] Networked multiplayer
- [ ] AI-controlled crewmates
- [ ] Procedural map generation
- [ ] Achievement system
- [ ] Leaderboards
- [ ] Custom character creation

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Add your improvements
4. Test gameplay thoroughly
5. Submit a pull request

### Contribution Guidelines
- Follow Java coding standards
- Test all new features
- Maintain game balance
- Document new mechanics
- Ensure compatibility with Processing library

## 🆘 Support

If you encounter issues:

1. Check Java version compatibility
2. Verify all image files are present
3. Ensure Processing library is in classpath
4. Test with console debug output enabled
5. Open an issue with system details and error logs

---

**Sus or not sus? That is the question!** 🔍👀

*Built with ❤️ by Rishabh Aggarwal*

### Academic Integrity Notice
This project was created for educational purposes as part of CS 300 coursework. Please respect academic integrity policies when using this code for learning or reference.
