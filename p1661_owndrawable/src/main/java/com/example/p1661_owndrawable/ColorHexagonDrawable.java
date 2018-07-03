package com.example.p1661_owndrawable;

public class ColorHexagonDrawable extends HexagonDrawable {
    public ColorHexagonDrawable(int color) {
        getPaint().setColor(color);
    }
}
