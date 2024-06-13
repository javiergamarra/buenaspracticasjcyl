package com.nhpatt.gameoflife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameOfLife {

    int[] board;
    int length;


    public GameOfLife() {
        this.length = 3;

    }

    public GameOfLife(int length, int[][] points) {
        this.length = length;

        this.board = new int[length];

        for (int i = 0; i < points.length; i++) {
            this.board[i] = points[i][0] * length + points[i][1];
        }
    }

    public boolean checkIfAlive(boolean cellAlive, int aliveNeighbours) {
        if (cellAlive) {
            return aliveNeighbours == 2 || aliveNeighbours == 3;
        } else {
            return aliveNeighbours == 3;
        }
    }

    public List<int[]> getNeighbours(int x, int y, int length) {

        List<Integer> positions = Arrays.asList(-1, 0, 1);

        List<int[]> neighbours = new ArrayList<>();

        for (int j : positions) {
            for (int i : positions) {

                // Same position
                if (i == 0 && j == 0) {
                    continue;
                }

                if (x + i >= 0 && x + i < length &&
                        y + j >= 0 && y + j < length) {
                    neighbours.add(new int[]{x + i, y + j});
                }
            }
        }

        return neighbours;
    }

    public void tick() {
        int[] board = new int[length];
        int index = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                List<int[]> neighbours = getNeighbours(i, j, length);

                int aliveNeighbours = 0;
                for (int[] neighbour : neighbours) {
                    if (isAlive(neighbour[0], neighbour[1])) {
                        aliveNeighbours++;
                    }
                }

                boolean alive = checkIfAlive(isAlive(i, j), aliveNeighbours);

                if (alive) {
                    board[index] = i * length + j;
                    index++;
                }
            }
        }
        this.board = board;
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
