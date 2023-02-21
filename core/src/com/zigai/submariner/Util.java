package com.zigai.submariner;


import java.util.Random;

public class Util {
    private static final Random random = new Random();

    public static long seconds(double s) {
        double ns = s * 1_000_000_000;
        return (long) ns;
    }

    public static float getMineScale(float min, float max) {
        float num = (float) random.nextGaussian(1.1, 0.3);
        if (num > Config.MINE.MAX_SCALE) {
            num = Config.MINE.MAX_SCALE;
        }
        if (num < Config.MINE.MIN_SCALE) {
            num = Config.MINE.MIN_SCALE;
        }
        return num;
    }
}
