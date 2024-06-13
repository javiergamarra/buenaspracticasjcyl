package com.nhpatt.gameoflife;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameOfLife {

    boolean[][] board;
    int length;


    public GameOfLife() {
        this.length = 3;

    }

    public GameOfLife(int length, int[][] points) {
        this.length = length;

        this.board = initBoard(length);

        for (int[] point : points) {
            this.board[point[0]][point[1]] = true;
        }
    }

    private boolean[][] initBoard(int length) {
        boolean[][] board = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            board[i] = new boolean[length];
        }
        return board;
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
        boolean[][] board = initBoard(length);
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
                    board[i][j] = true;
                }
            }
        }
        this.board = board;
    }

    public boolean isAlive(int x, int y) {
        return this.board[x][y];
    }
}
