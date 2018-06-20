package com.example.game_eatcircles;

public class MainCircle extends SimpleCircle{

    public MainCircle(int x, int y) {

        super(x, y, GameManager.INIT_RADIUS);
        setColor(GameManager.MAIN_CIRCLE_COLOR);
    }

    public void moveCircleOnTouch(int x, int y) {
        int dx = (getX() - x) * GameManager.getAdoptedScreenValue(GameManager.INIT_SPEED) / GameManager.getWidth();
        int dy = (getY() - y) * GameManager.getAdoptedScreenValue(GameManager.INIT_SPEED) / GameManager.getHeight();

        setX(getX() - dx);
        setY(getY() - dy);
    }
}
