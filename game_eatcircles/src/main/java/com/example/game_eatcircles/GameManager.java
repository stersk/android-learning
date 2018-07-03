package com.example.game_eatcircles;

import android.graphics.Color;

import java.util.ArrayList;

public class GameManager {
    public static final int INIT_CIRCLES_COUNT = 10;
    public static final int INIT_ENEMY_MIN_RADIUS = 10;
    public static final int INIT_RADIUS = 45;
    public static final int INIT_SPEED = 30;
    public static final int SAFE_ZONE_OFFSET = 10;
    public static final int INIT_ENEMY_MAX_RADIUS = 100;
    public static final int MAIN_CIRCLE_COLOR = Color.BLUE;
    public static final int ENEMY_CIRCLE_COLOR = Color.RED;
    public static final int FOOD_CIRCLE_COLOR = Color.GREEN;
    public static final int ENEMY_CIRCLE_SPEED = 10;

    private static final int IDEAL_SCREEN_SIZE = 756;

    private static MainCircle mainCircle;
    ArrayList<EnemyCircle> circles;
    private static int windowWidth;
    private static int windowHeight;
    private CanvasView canvasView;

    public GameManager(CanvasView canvasView, int width, int height) {
        windowHeight = height;
        windowWidth = width;

        this.canvasView = canvasView;

        startNewGame();
    }

    private void startNewGame(){
        initMainCircle();
        initEnemyCircles();
    }

    private void initEnemyCircles() {
        circles = new ArrayList();
        for (int i = 0; i < INIT_CIRCLES_COUNT; i++ ) {
            EnemyCircle circle;
            Boolean isIntersect;

            do {
                circle = EnemyCircle.getRandomEnemyCircle();

                isIntersect = circle.isIntersect(mainCircle, SAFE_ZONE_OFFSET);
                if (!isIntersect) {
                    for (int j = 0; circles.size() > j; j++) {
                        isIntersect = circles.get(j).isIntersect(circle, SAFE_ZONE_OFFSET/2);

                        if (isIntersect) {
                            break;
                        }
                    }
                }

            } while (isIntersect);

            circle.recalculateCircleColor(mainCircle);
            circles.add(circle);
        }
    }

    public static int getWidth() {
        return windowWidth;
    }

    public static int getHeight() {
        return windowHeight;
    }

    private void initMainCircle() {
        mainCircle = new MainCircle(windowWidth / 2, windowHeight / 2);
    }

    public void onDraw() {
        canvasView.drawCircle(mainCircle);
        SimpleCircle eatenCircle = null;

        for(EnemyCircle enemyCircle : circles) {
            if (enemyCircle.isIntersect(mainCircle)) {
                eatenCircle = enemyCircle;
            }

            enemyCircle.moveOneStep();
            canvasView.drawCircle(enemyCircle);
        }

        if (eatenCircle != null) {
            int mainRadius = mainCircle.getRadius();
            if (mainRadius >= eatenCircle.getRadius()) {
                mainCircle.setRadius((int) Math.sqrt(Math.pow(mainRadius, 2) + Math.pow(eatenCircle.getRadius(), 2)));
                circles.remove(eatenCircle);
            } else {
                canvasView.showMessage("Ви програли!!!");

                startNewGame();
            }

            for (EnemyCircle enemyCircle : circles) {
                enemyCircle.recalculateCircleColor(mainCircle);
            }

            if (circles.size() == 0) {
                canvasView.showMessage("Ви виграли )))");

                startNewGame();
            }
        }
    }

    public void onTouchEvent(int x, int y) {
        mainCircle.moveCircleOnTouch(x,y);
    }


    public static int getAdoptedScreenValue(int value) {
        float coeficient;

        if (windowHeight < windowWidth) {
            coeficient = (float) windowHeight / (float) IDEAL_SCREEN_SIZE;
        } else {
            coeficient = (float) windowWidth / (float) IDEAL_SCREEN_SIZE;
        }

        int result = (int) (value * coeficient);
        return result;
    }
}
