package com.nhpatt.gameoflife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameOfLife {

    List<Point> cellsAlive = new ArrayList<>();
    int length;


    public GameOfLife() {
        this.length = 3;
    }

    public GameOfLife(int length, Point... points) {
        this.length = length;
        this.cellsAlive = new ArrayList<>(Arrays.asList(points));
    }

    public boolean checkIfAlive(boolean cellAlive, int aliveNeighbours) {
        if (cellAlive) {
            return aliveNeighbours == 2 || aliveNeighbours == 3;
        } else {
            return aliveNeighbours == 3;
        }
    }

    public List<Point> getNeighbours(int x, int y, int length) {

        List<Integer> positions = Arrays.asList(-1, 0, 1);

        List<Point> points = new ArrayList<>();

        for (int j : positions) {
            for (int i : positions) {

                // Same position
                if (i == 0 && j == 0) {
                    continue;
                }

                if (x + i >= 0 && x + i < length &&
                        y + j >= 0 && y + j < length) {
                    points.add(new Point(x + i, y + j));
                }
            }
        }

        return points;
    }

    public void tick() {
        List<Point> cells = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                List<Point> neighbours = getNeighbours(i, j, length);

                int aliveNeighbours = (int) neighbours.stream().filter(point -> isAlive(point.x(), point.y())).count();

                boolean alive = checkIfAlive(findCell(i, j), aliveNeighbours);

                if (alive) {
                    cells.add(new Point(i, j));
                }
            }
        }
        this.cellsAlive = cells;
    }

    private boolean findCell(int x, int y) {
        return cellsAlive.stream().anyMatch(point -> point.x() == x && point.y() == y);
    }

    public boolean isAlive(int x, int y) {
        return findCell(x, y);
    }
}
