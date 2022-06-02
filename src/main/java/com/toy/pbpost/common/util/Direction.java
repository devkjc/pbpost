package com.toy.pbpost.common.util;

public enum Direction {
    NORTH(0.0),
    WEST(270),
    SOUTH(180),
    EAST(90),
    NORTH_WEST(315),
    SOUTH_WEST(225),
    SOUTH_EAST(135),
    NORTH_EAST(45);

    private final double angle;

    public final double getAngle() {
        return this.angle;
    }

    Direction(double angle) {
        this.angle = angle;
    }
}
