package GameTest;

import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Test;

import Game.Maze;



public class MazeTest {
    
    //@Test
    public void Test01() {
        Maze ms = Maze.getInstance();
        ms.generateMaze("map/map1.txt");
        System.out.println(ms.toString());
        assertEquals(true, true);
    }

    @Test
    public void Test04() {
        MazeStub ms = MazeStub.getInstance();
        ms.generateMaze("map/map1.txt");
        int numOfFood = ms.getTotalNumOfFood();
        assertEquals(320, numOfFood);
    }

    @Test
    public void Test05(){
        MazeStub ms = MazeStub.getInstance();
        ms.genParentMaze();
        System.out.println(ms.toString());
        assertEquals(true, true);
    }
}
