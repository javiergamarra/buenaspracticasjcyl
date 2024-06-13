package com.nhpatt.gameoflife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameOfLife {

    List<Integer> board;
    int length;


    public GameOfLife() {
        this.length = 3;

    }

    public GameOfLife(int length, int[][] points) {
        this.length = length;

        this.board = new ArrayList<>();

        for (int i = 0; i < points.length; i++) {
            this.board.add(points[i][0] * length + points[i][1]);
        }
    }

    public boolean checkIfAlive(boolean cellAlive, int aliveNeighbours) {
        if (cellAlive) {
            return aliveNeighbours == 2 || aliveNeighbours == 3;
        } else {
            return aliveNeighbours == 3;
        }
    }

    List<Integer> positions = Arrays.asList(-1, 0, 1);

    public int getAliveNeighbours(int x, int y, int length) {

        int aliveNeighbours = 0;

        for (int j : positions) {
            for (int i : positions) {

                // Same position
                if (i == 0 && j == 0) {
                    continue;
                }

                if (x + i >= 0 && x + i < length &&
                        y + j >= 0 && y + j < length) {

                    int value = (x + i) * length + (y + j);
                    if (isAlive(value)) {
                        aliveNeighbours++;
                    }
                }
            }
        }

        return aliveNeighbours;
    }

    public void tick() {
        List<Integer> board = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {

                int aliveNeighbours = getAliveNeighbours(i, j, length);

                boolean alive = checkIfAlive(isAlive(i, j), aliveNeighbours);

                if (alive) {
                    board.add(i * length + j);
                }
            }
        }
        this.board = board;
    }

    private boolean isAlive(int neighbour) {
        return this.board.contains(neighbour);
    }

    public boolean isAlive(int x, int y) {
        for (int value : this.board) {
            if (value == x * length + y) {
                return true;
            }
        }
        return false;
    }
}
