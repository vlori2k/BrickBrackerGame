package BrickBrackerGame;                    //Since the project-name is called "BrickBrackerGame", so does the package



import java.awt.Color;                        //Import the library "AWT" Color
import java.awt.Font;                         //Import the library "AWT" Font
import java.awt.Graphics;                     //Import the library "AWT" Graphics
import java.awt.Graphics2D;                   //Import the library "AWT" Graphics2D
import java.awt.Rectangle;                    //Import the library "AWT" Rectangle
import java.awt.event.ActionEvent;            //Import the library "AWT" ActionEvent
import java.awt.event.ActionListener;         //Import the library "AWT" ActionListener
import java.awt.event.KeyEvent;               //Import the library "AWT" KeyEvent
import java.awt.event.KeyListener;            //Import the library "AWT" KeyListener
import javax.swing.Timer;                     //Import the library "AWT" Timer

import javax.swing.JPanel;                    //Import the library "SWING" JPanel

public class Gameplay extends JPanel implements KeyListener, ActionListener{  //Create a class "Gameplay" and make this public
    private boolean play = false;                                             //Create a variable "play" and set this to be false
    private int score = 0;                                                    //Create a int variable "score" and set this to be 0


    private int totalNumbersOfBricks = 21;                                    //Create a variable "totalNumbersOfBricks" and set this to be 21
    private Timer timer;                                                      //Create a variable "Timer" and set this to be a timer
    private int delay = 8;                                                    //Create a variable "delay" and set this to be 8

    private int playerX = 310;                                                //Create a variable "playerX" and set this to be 310

    private int ballpositionX = 120;                                          //Create a variable "ballpositionX" and set this to be 120
    private int ballpositionY = 350;                                          //Create a variable "ballpositionY" an
    private int ballXdir = -1;                                                //Create a variable ""
    private int ballYdir = -2;                                                //Create a variable ""

    private MapGenerator map;                                                 //Create a variable MapGenerator(from  the class) and set this to be variable map on the Gameplay

    public Gameplay() {                                                       //Constructor
        map = new MapGenerator(3, 7);                                         //Here we scale and fix the size of the bricks
        addKeyListener(this);                                               //Assign the addKeyListener method
        setFocusable(true);                                                   //Set the setFocusable to be true
        setFocusTraversalKeysEnabled(false);                                  //Set the setFocusTraversalKeysEnabled to be false
        timer = new Timer(delay, this);
        timer.start();                                                        //Set the timer to start

    }

    public void paint(Graphics g) {                                            //Add a method called "paint" which takes Graphics as a parameter, we add the alias g for this.
        //Underneat we will fill GUI. We give colors to the ball, background color etc.

        g.setColor(Color.green);                                              //Here we set the background color to be green
        g.fillRect(1, 1, 692, 592);                           //Here we set the size of the background

        //Here we draw the map
        map.draw((Graphics2D) g);                                              //method draw that takes Graphics2D as parameter

        // borders
        g.setColor(Color.yellow);                                             //We set the border color to be yellow
        g.fillRect(0, 0, 3, 592);                            //Set the height and width of the border
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);


        // scores
        g.setColor(Color.black);                                              //Set the score font color to be black
        g.setFont(new Font("Comic Sans", Font.BOLD, 25));           //Set font type and size for score number
        g.drawString("" + score, 590, 30);                             //Set the score to be placed at upper right corner of the GUI

        // the paddle
        g.setColor(Color.black);                                              //Set the paddle color to be black
        g.fillRect(playerX, 550, 100, 8);                      //Determine the size of the paddle

        //the ball
        g.setColor(Color.blue);                                               //Give blue color to the ball
        g.fillOval(ballpositionX, ballpositionY, 20, 20);         //Set the size of the ball


        if (totalNumbersOfBricks <= 0) {                                       //Set an if-statement that says: "If total numbers of bricks are equal or less than 0"
            play = false;                                                     //Set play to be false
            ballXdir = 0;                                                     //ballXdir and ballYdir is then 0
            ballYdir = 0;
            g.setColor(Color.RED);                                            //Font color is red
            g.setFont(new Font("Comic Sans", Font.BOLD, 30));       //Type and Size of the font
            g.drawString("Gratz! You won :D", 190, 300);             //A text displaying that the player won

            g.setFont(new Font("Comic Sans", Font.BOLD, 20));       //Type and Size of the font
            g.drawString("Press Enter to restart", 230, 350);        //New text popping up
        }

        if (ballpositionY > 570) {                                            //An IF statement that says: "If position Y of the ball is higher than 570: then:
            play = false;                                                    //Then play is set to be false
            ballXdir = 0;                                                    //ballXdir is set to be 0
            ballYdir = 0;                                                    //ballYdir is set to be 0
            g.setColor(Color.RED);                                           //Font color is set to be red
            g.setFont(new Font("Comic Sans", Font.BOLD, 20));      //Set font style and size
            g.drawString("Game over, you lost :( bad luck", 190, 300);

            g.setFont(new Font("Comic Sans", Font.BOLD, 20));
            g.drawString("You want to play again? Press enter then!", 230, 350);
        }

        g.dispose();                                                         //Instead of terminating the program/exit, dispose can be used to restart it   }
    }

    @Override                                                                //Overriding the functionality of the existing method
    public void actionPerformed(ActionEvent e) {                             //Void method actionPerformed that takes ActionEvent as parameter with alias e
        timer.start();                                                       //start timer
        if(play) {                                                           //If play is true?
            if(new Rectangle(ballpositionX, ballpositionY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
                ballYdir = -ballYdir;                                        //Take the variable ballYdir and subtracts by itself..
            }

            A: for(int i = 0; i<map.map.length; i++) {                       //For each loop with variable i set to be 0 and less than the map length
                for(int j = 0; j<map.map[0].length; j++) {                   //For each loop with variable j set to be 0 and less than the map length
                    if(map.map[i][j] > 0) {                                  //For each loop which says: "IF the map list with both I and J is higher than 0:
                        int brickX = j* map.WidthOfTheBrick + 80;            //Variable j is multiplied with WidthOfTheBrick and + 80
                        int brickY = i* map.HeightOfTheBrick + 50;           //variable i is multiplied with HeightofTheBrick and + 50
                        int WidthOfTheBrick = map.WidthOfTheBrick;           //Variable int named "WidthOfTheBrick" which is equal to the number assigned to the variable map
                        int HeightOfTheBrick = map.HeightOfTheBrick;         //Variable int named "HeightOfTheBrick" which is equal to the number assigned to the variable map

                        Rectangle rect = new Rectangle(brickX, brickY, WidthOfTheBrick, HeightOfTheBrick); //Variable Rect creates new Rectangle which takes brickX, brickY, WidthOfTheBrick/HeightOfTheBrick as parameters
                        Rectangle ballRect = new Rectangle(ballpositionX, ballpositionY, 20, 20); //Variable ballRect which takes ballpositionX and ballpositionY as parameter and sets the size for them
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)) {  //If statement which says: "IF ballRect intersects brickRect" then:
                            map.setTheValueOfBrick(0, i, j);  //Values of i and j are assigned to "setTheValueOfBrick"
                            totalNumbersOfBricks--;           //Decrease value of totalNumbersOfBricks variable?
                            score += 5;                       //For each time a brick dissapears, 5 points are earned

                            if(ballpositionX + 19 <= brickRect.x || ballpositionX + 1 >= brickRect.x + brickRect.width){ //IF statement which says: "IF ballpositionX + 19 are equal or less than brirect.x OR
                                ballXdir = -ballXdir; //OR ballpositionX + 1 is equal or higher than brickRect.x together brickRect.width" Then:
                                                      //Then the variable ballXdir is subtracted by itself
                            } else {                  //otherwise:
                                ballYdir = -ballYdir; //otherwise ballYdir should be subtracted by itself..
                            }
                            break A;                  //Force the program to stop
                        }

                    }

                }

            }

            ballpositionX += ballXdir;               //ballpositionX added with ballXdir
            ballpositionY += ballYdir;               //ballpositionY added with ballYdir
            if(ballpositionX < 0) {                  //If ballpositionX is less than 0
                ballXdir = -ballXdir;                //ballXdir is then subtracted by itself

            }
            if(ballpositionY < 0) {                  //If ballpositionY is less than 0
                ballYdir = -ballYdir;                //ballYdir is then subtracted by itself
            }
            if(ballpositionX > 670) {                //If ballposition is higher than 670
                ballXdir = -ballXdir;                //then ballXdir should be subtracted by itself
            }

        }
        repaint();                                   //restart the method ActionEvent?
    }

    @Override
    public void keyTyped(KeyEvent e) {}              //Void method that takes KeyEvent as parameter with e as alias
    @Override
    public void keyReleased(KeyEvent e) {}           //Void method that also takes KeyEvent as parameter with e as alias

    boolean keyIsDown = False;

    @Override
    public void keyPressed(KeyEvent e) {
        //Same
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {    //If statement that says: "IF e.getKeyCode is the same as KeyEvent.VK_RIGHT" Then:
            if(playerX >= 600 ) {                    //A new if statement that says: "IF playerX is higher or equals 600" Then:
                playerX = 600;                       // set playerX to be 600
            } else {                                 //Otherwise:
                moveRight();                         //Method moveRight
            }

        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {     //If statement that says: "IF e.getKeyCode is the same as KeyEvent.VK_LEFT" Then:
            if(playerX < 10 ) {                      //A new if statement that says: "IF playerX is higher than 10, then:
                playerX = 10;                        //Set playerX to be 10..
            } else {                                 //Otherwise:
                moveLeft();                          //Methode moveLeft
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER) {    //If statement that says: "IF e.getKeyCode is the same as KeyEvent.VK_ENTER" Then:
            if(!play) {                              //If variable that says: "IF variable play is false(If the game is not in play)", Then:
                play = true;                         //Set play to be true
                ballpositionX = 120;                 //Set variable ballpositionX to be 120
                ballpositionY = 350;                 //Set variable ballpositionY to be 350
                ballXdir = -1;                       //ballXdir variable subtracted by 1
                ballYdir = -2;                       //ballYdir variable subtracted by 2
                playerX  = 310;                      //playerX to be 310
                score = 0;                           //Score is set to 0
                totalNumbersOfBricks = 21;           //21 bricks
                map = new MapGenerator(3, 7);        //New map and scaled

                repaint();                           //Draw the GUI again
            }
        }
    }
    public void moveRight() {                        //Void method moveRight
        play = true;                                 //Variable play is set to be true
        playerX+=20;                                 //playerX is added with 20
    }
    public void moveLeft() {                         //Void method moveLeft
        play = true;                                 //Variable play is set to be true
        playerX-=20;                                 //playerX is subtracted with 20
    }
}

