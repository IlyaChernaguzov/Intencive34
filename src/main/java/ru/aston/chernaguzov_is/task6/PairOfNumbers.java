package ru.aston.chernaguzov_is.task6;

public class PairOfNumbers {
    private int x;
    private int y;

    public PairOfNumbers(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "PairOfNumbers{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
