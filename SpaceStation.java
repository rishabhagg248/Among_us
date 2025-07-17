/*
 * All the functions used in the SpaceStation are defined here
 * 
 * @author Rishabh Aggarwal
 *
 */

//importing the modules used in the program
import java.io.File;
import processing.core.PImage;

public class SpaceStation {
  
  //defining the variables used in the program
  private static PImage background; //to store the background image
  private static PImage sprite; //to store the image of amogus (used for dimensions of amogus sprite image in later methods)
  private static Amogus[] players; //array to store the palyers being displayed
  private static int NUM_PLAYERS = 8; //max number of players in a game
  private static int impostorIndex; //determines the index of the imposter
  
  public static void setup() {
    
    /*
     * used to initialize objects which need to be initialized only once
     */
    
    background = Utility.loadImage("images"+File.separator+"background.jpeg"); //initializing and storing backgorund in background
    sprite = Utility.loadImage("images"+File.separator+"sprite1.png"); //initializing and storing image of an amogus sprite in sprite
    players = new Amogus[NUM_PLAYERS]; //initializes the array which stores all the sprites
    int randInt = Utility.randGen.nextInt(3)+1; //generates a random integer used in picking random color for sprites
    Amogus amogus = new Amogus(randInt); //creates a new amogus character
    players[0] = amogus; //adds first character to array
    impostorIndex = Utility.randGen.nextInt(7)+1; //assigns random number between 1-7 for impostor
    System.out.print("impostor index = "+impostorIndex); //prints impostor's index in console
    
  }
  
  public static void draw(){
   
    /*
     * called continuosly so used to display the images on screen
     */
    
    Utility.image(background, 600, 500); //displays background image on screen
    
    //checks if impostor is overlapping with every character on screen.
    //if they are overlapping then declares character as "unalive"
    for(int i =0;i<players.length;i++) {
      if(players[i]!=null) {  
        if(players[impostorIndex]!=null) {
          if(overlap(players[i],players[impostorIndex]) || overlap(players[impostorIndex],players[i])) {
            players[i].unalive();
          }
        }
      }
    }
    
    //displays characters(all non null elements of players[]) on screen
    for(int i =0;i<players.length;i++) {
      if(players[i]!=null) {  
        players[i].draw();
      }
    }
    
  }
  
  public static void keyPressed() {
    
    /*
     * works whenever "a" is pressed by the user
     * if "a" key is pressed then it spawns a new character of random color on random location on screen
     */
    
    char a = 97; //ASCII (char) of "a"
    
    //checks if key pressed is "a"
    if(Utility.key()==a) {
      float randomPlayerX = Utility.randGen.nextInt(1201); //generates random number for x axis between 0-1200
      float randomPlayerY = Utility.randGen.nextInt(801); //generater random number for y axis between 0-800
      int randomPlayerColor = Utility.randGen.nextInt(3)+1; // generates random number for color of new sprite between 1-3
      
      //adds new sprite created using the already randomly generated numbers and adds them to the array of players[]
      for (int i=0; i<players.length; i++) {
        if(players[i]==null) {
          Amogus amogus = new Amogus(randomPlayerColor, randomPlayerX, randomPlayerY, i==impostorIndex);
          players[i]=amogus;
          break; //so that the loop doesnt keep creating new characters
        }
      }
    }
 
  }
  
  public static boolean isMouseOver(Amogus amogus) {
    
    /*
     * checks if mouse is over the given amogus
     */
    
    //co-ordinates of the given amogus
    float amogusX = amogus.getX();
    float amogusY = amogus.getY();
    
    //edge co-ordinates of the given amogus
    float rightEdge = amogusX + (sprite.width/2);
    float leftEdge = amogusX - (sprite.width/2);
    float bottom = amogusY + (sprite.height/2);
    float top = amogusY - (sprite.height/2);
    
    //checks if the co-ordinates of mouse are between the edge co-ordinates
    if(Utility.mouseX()>leftEdge && Utility.mouseX()<rightEdge) {
      if(Utility.mouseY()>top && Utility.mouseY()<bottom) {
        return true;
      }
    }
    
    return false; //if mouse is not between the edge co-ordinates function returns false
  
  }
  
  public static void mousePressed() {
    
    /*
     * checks if mouse is clicked
     * if click is detected the method checks if the mouse was clicked over any of the characters
     * if mouse was clicked over any of the characters then it starts dragging the character using startDragging() function of Amogus class
     */
    
    for(int i =0;i<players.length;i++) {
      if(players[i]!=null) {
        if(isMouseOver(players[i])) {
          players[i].startDragging();
        }
      }
    }
    
  }
  
  public static void mouseReleased() {
    
    /* 
     * checks if the mouse is released
     * if it is released then the method stops all the moving amogus using the stopDragging() function of Amogus class
     */
    
    for(int i =0;i<players.length;i++) {
      if(players[i]!=null) {
        players[i].stopDragging();
      }
    }
    
  }
  
  public static boolean overlap(Amogus amogus1, Amogus amogus2) {
    
    /*
     * checks if the 2 given amogus are overlapping
     */
    
    //co-ordinates of amogus 1
    float amogus1X = amogus1.getX();
    float amogus1Y = amogus1.getY();
    
    //edge co-ordinates of amogus 1
    float rightEdge1 = amogus1X + (sprite.width/2);
    float leftEdge1 = amogus1X - (sprite.width/2);
    float bottom1 = amogus1Y + (sprite.height/2);
    float top1 = amogus1Y - (sprite.height/2);
    
    //co-ordinates of amogus 2
    float amogus2X = amogus2.getX();
    float amogus2Y = amogus2.getY();
    
    //edge co-ordinates of amogus 2
    float rightEdge2 = amogus2X + (sprite.width/2);
    float leftEdge2 = amogus2X - (sprite.width/2);
    float bottom2 = amogus2Y + (sprite.height/2);
    float top2 = amogus2Y - (sprite.height/2);
    
    /*
     * overlap can be in 4 cases
     */
    
    //case 1: top right
    if(rightEdge1>=leftEdge2 && rightEdge1<=rightEdge2) {
      if(bottom1>=top2 && bottom1<=bottom2) {
        return true;
      }
    }
    
    //case 2: top left
    if(leftEdge1>=rightEdge2 && leftEdge1<=leftEdge2) {
      if(bottom1>=top2 && bottom1<=bottom2) {
        return true;
      }
    }
    
    //case 3: bottom right
    if(rightEdge1>=leftEdge2 && rightEdge1<=rightEdge2) {
      if(top1>=bottom2 && top1<=top2) {
        return true;
      }
    }
    
    //case 4: bottom left
    if(leftEdge1>rightEdge2 && leftEdge1<leftEdge2) {
      if(top1>=bottom2 && top1<=top2) {
        return true;
      }
    }
    
    return false; //if no overlap is found method returns false
    
  }
  
  public static void main(String[] args) {
    
    Utility.runApplication();
    
  }

}
