package test;

import model.MazeGenerator;

public class TestMaze {
    
    public static void main(String[] args){
        MazeGenerator maze = new MazeGenerator(20);
        maze.newMaze();
        maze.draw();
    }
}