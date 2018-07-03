package com.example.game_eatcircles;

public class SimpleCircle {
    private int x;
    private int y;

    public void setRadius(int radius) {
        this.radius = radius;
    }

    private int radius;
    private int color;

    public SimpleCircle(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = GameManager.getAdoptedScreenValue(radius);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public boolean thisCircleIsBiggerThan(SimpleCircle circle) {
        return circle.getRadius() < radius;
    }

    public boolean isIntersect(SimpleCircle mainCircle) {
        int dx = mainCircle.getX() - getX();
        int dy = mainCircle.getY() - getY();
        int radius = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        return radius <= (mainCircle.getRadius() + getRadius());
    }

    public boolean isIntersect(SimpleCircle mainCircle, int offset) {
        int dx = mainCircle.getX() - getX();
        int dy = mainCircle.getY() - getY();
        int radius = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));

        return radius <= (mainCircle.getRadius() + getRadius() + offset);
    }
}
