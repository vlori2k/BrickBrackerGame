package BrickBrackerGame;                                                    //Imports the BrickBrackerGame package

import java.awt.BasicStroke;     //Import BasicStroke library
import java.awt.Color;           //Import Color library
import java.awt.Graphics2D;      //Import Graphics2D library

public class MapGenerator {         //Create the class MapGenerator
    public int map[][];             //Create a variable map with 2 empty lists
    public int WidthOfTheBrick;    //Create a variable called WidthOfTheBrick
    public int HeightOfTheBrick;   //Create a variable called HeightOfTheBrick
    public MapGenerator(int row, int col) {       //Constructor with 2 ints, row and col
        map = new int[row][col];                  //Assign the 2 variables row and col into map
        for(int i = 0; i < map.length; i++) {       //for each loop i that is 0, and is less than the map length
            for(int j=0; j< map[0].length; j++) {   //for each loop j that is 0, and is less than the very first index og map length
                map[i][j] = 1;                      //i and j is 1
            }

        }

        WidthOfTheBrick = 540/col;                  //Here, WidthOfTheBrick variable is 540 divided with col
        HeightOfTheBrick = 150/row;                 //Here, HeightOfTheBrick variable is 150 divided with row
    }
    public void draw(Graphics2D g) {                 //void method draw that takes Graphics2D as parameter and uses g as alias
        for(int i = 0; i < map.length; i++) {        //For each loop
            for(int j=0; j< map[0].length; j++) {    //For each loop
                if(map[i][j] > 0) {                  //If sentences that say: "IF: both lists are higher than 0" then:
                    g.setColor(Color.white);         //Then set the background colors of the bricks to be white
                    g.fillRect(j * WidthOfTheBrick + 80, i * HeightOfTheBrick + 50, WidthOfTheBrick, HeightOfTheBrick);

                    g.setStroke(new BasicStroke(3));     //Set the strokes(Border of the bricks) to be 3mm?
                    g.setColor(Color.black);                   //Color is black for the strokes
                    g.drawRect(j * WidthOfTheBrick + 80, i * HeightOfTheBrick + 50, WidthOfTheBrick, HeightOfTheBrick);
                }
            }
        }
    }
    public void setTheValueOfBrick(int value, int row, int col) {  //Method that assign the values to the bricks using the variables value, row and col as parameters
        map[row][col] = value;                                     //Variable map with the 2 lists containin row and col are equal to value
    }
}