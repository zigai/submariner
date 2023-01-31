package com.zigai.submariner;

import com.badlogic.gdx.math.Vector2;

public class Util {
    public static long seconds(double s) {
        double ns = s * 1_000_000_000;
        return (long) ns;
    }
}
