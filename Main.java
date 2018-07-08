package BrickBrackerGame;   //Since the project-name is called "BrickBrackerGame", so does the package

import javax.swing.JFrame;  //Here we import the library JFrame

public class Main {        //The main class of this program

    public static void main(String[] args) {                   //This is standard for method for the main class
        JFrame obj = new JFrame();                             //Create a new object called "JFrame"
        Gameplay gamePlay = new Gameplay();                    //Create a variable called "gamePlay" which is linked to the Gameplay class
        obj.setBounds(10, 10, 700, 600);      //Here and underneath we set the attributes of the object "JFrame" and it should do
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gamePlay);
    }

}
