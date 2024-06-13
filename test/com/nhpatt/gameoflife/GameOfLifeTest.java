package com.nhpatt.gameoflife;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GameOfLifeTest {

    @Test
    public void oneDeadCellWithThreeAliveNeighboursIsBorn() {
        GameOfLife game = new GameOfLife();
        int aliveNeighbours = 3;
        boolean alive = game.checkIfAlive(false, aliveNeighbours);
        assertTrue(alive);
    }

    @Test
    public void oneAliveCellWithTwoAliveNeighboursIsAlive() {
        GameOfLife game = new GameOfLife();
        int aliveNeighbours = 2;
        boolean alive = game.checkIfAlive(true, aliveNeighbours);
        assertTrue(alive);
    }

    @Test
    public void oneAliveCellWithFourAliveNeighboursDiesBecauseSobrepopulation() {
        GameOfLife game = new GameOfLife();
        int aliveNeighbours = 4;
        boolean alive = game.checkIfAlive(true, aliveNeighbours);
        assertFalse(alive);
    }

    @Test
    public void oneDeadCellWithFourAliveNeighboursIsStillDead() {
        GameOfLife game = new GameOfLife();
        int aliveNeighbours = 4;
        boolean alive = game.checkIfAlive(false, aliveNeighbours);
        assertFalse(alive);
    }

    @Test
    public void oneCellInTheCornerHasThreeNeighbours() {
        GameOfLife game = new GameOfLife();
        List<Point> points = game.getNeighbours(0, 0, 3);

        assertEquals(3, points.size());

        assertEquals(1, points.get(0).x());
        assertEquals(0, points.get(0).y());
        assertEquals(0, points.get(1).x());
        assertEquals(1, points.get(1).y());
        assertEquals(1, points.get(2).x());
        assertEquals(1, points.get(2).y());
    }

    @Test
    public void oneCellInTheMiddleHasEightNeighbours() {
        GameOfLife game = new GameOfLife();
        List<Point> points = game.getNeighbours(1, 1, 3);

        assertEquals(8, points.size());
    }

    @Test
    public void aBoardWithAMiddleCellAliveDies() {
        GameOfLife game = new GameOfLife(3, new com.nhpatt.gameoflife.Point(1, 1));
        game.tick();

        assertEquals(false, game.isAlive(1, 1));
    }

    @Test
    public void aBoardWithAMiddleCellAliveAndSomeNeighboursLives() {
        GameOfLife game = new GameOfLife(3, new Point(1, 1), new Point(2, 1), new Point(2, 2), new Point(1, 2));
        game.tick();

        assertEquals(true, game.isAlive(1, 1));
    }

    @Test
    public void aBoardWithSomeCellsAliveDiesInTwoSteps() {
        GameOfLife game = new GameOfLife(3, new Point(0, 1), new Point(1, 1), new Point(2, 2));
        game.tick();

        assertEquals(false, game.isAlive(0, 0));
        assertEquals(false, game.isAlive(0, 1));
        assertEquals(false, game.isAlive(0, 2));
        assertEquals(false, game.isAlive(1, 0));
        assertEquals(true, game.isAlive(1, 1));
        assertEquals(true, game.isAlive(1, 2));
        assertEquals(false, game.isAlive(2, 0));
        assertEquals(false, game.isAlive(2, 1));
        assertEquals(false, game.isAlive(2, 2));

        game.tick();

        assertEquals(false, game.isAlive(0, 0));
        assertEquals(false, game.isAlive(0, 1));
        assertEquals(false, game.isAlive(0, 2));
        assertEquals(false, game.isAlive(1, 0));
        assertEquals(false, game.isAlive(1, 1));
        assertEquals(false, game.isAlive(1, 2));
        assertEquals(false, game.isAlive(2, 0));
        assertEquals(false, game.isAlive(2, 1));
        assertEquals(false, game.isAlive(2, 2));
    }
}
