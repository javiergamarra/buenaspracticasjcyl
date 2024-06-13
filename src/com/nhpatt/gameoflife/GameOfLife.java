package com.nhpatt.gameoflife;

import java.util.*;

public class GameOfLife {

    TreeSet<Integer> board;
    int length;


    public GameOfLife() {
        this.length = 3;

    }

    public GameOfLife(int length, int[][] points) {
        this.length = length;

        this.board = new TreeSet<>();

        for (int[] point : points) {
            this.board.add(point[0] * length + point[1]);
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

                    Integer value = (x + i) * length + (y + j);
                    if (isAlive(value)) {
                        aliveNeighbours++;
                    }
                }
            }
        }

        return aliveNeighbours;
    }

    public void tick() {
        TreeSet<Integer> board = new TreeSet<>();
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

    private boolean isAlive(Integer neighbour) {
        if (neighbour > this.board.getLast() || neighbour < this.board.getFirst()) {
            return false;
        }

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
