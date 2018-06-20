package com.example.game_eatcircles;

public class EnemyCircle extends SimpleCircle {
    private int dx;
    private int dy;

    public EnemyCircle(int x, int y, int dx, int dy, int radius)
    {
        super(x, y, radius);
        this.dx = dx;
        this.dy = dy;
    }

    public static EnemyCircle getRandomEnemyCircle() {
        int circleSpeed = GameManager.getAdoptedScreenValue(GameManager.ENEMY_CIRCLE_SPEED);
        int x = (int) (Math.random() * GameManager.getWidth());
        int y = (int) (Math.random() * GameManager.getHeight());
        int xd = (int) (Math.random() * circleSpeed - 0.5 * circleSpeed);
        int yd = (int) (Math.random() * circleSpeed - 0.5 * circleSpeed);

        int radius = (int) (Math.random() * GameManager.INIT_ENEMY_MAX_RADIUS
                - GameManager.INIT_ENEMY_MIN_RADIUS
                + GameManager.INIT_ENEMY_MIN_RADIUS);

        return new EnemyCircle(x,y,xd,yd,radius);
    }



    public void recalculateCircleColor(SimpleCircle simpleCircle) {
        if (thisCircleIsBiggerThan(simpleCircle)) {
            setColor(GameManager.ENEMY_CIRCLE_COLOR);
        } else {
            setColor(GameManager.FOOD_CIRCLE_COLOR);
        }
    }

    public void moveOneStep() {
        if ((getX() + dx < 0) || (getX() + dx > GameManager.getWidth())) {
            dx = - dx;
        }
        if ((getY() + dy < 0) || (getY() + dy > GameManager.getHeight())) {
            dy = - dy;
        }
        setX(getX() + dx);
        setY(getY() + dy);
    }
}
