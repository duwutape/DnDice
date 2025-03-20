package org.example;

import java.util.List;

public class Constants {

    public static final List<Integer> DICE = List.of(2, 4, 6, 8, 10, 12, 20, 100);
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final List<String> DICE_NAME = List.of("select die", "d2", "d4", "d6", "d8", "d10", "d12", "d20", "d100");
    public static final List<String> UNITS = List.of("feet", "meters");
    public static final List<String> ALL_UNITS = List.of("feet", "meters", "fields");
    public static final String FOOT = "foot";
    public static final String FEET = "feet";
    public static final String METER = "meter";
    public static final String METERS = "meters";
    public static final String FIELD = "field";
    public static final String FIELDS = "fields";

    // styles
    public static final String FONT_SIZE = "-fx-font-size: ";
    public static final String FONT_WEIGHT = "-fx-font-weight: ";
    public static final String TEXT_COLOR = "-fx-text-fill: ";
    public static final String TEXT_STRIKE = "-fx-strikethrough: ";
    public static final int SIZE_SMALL = 12;
    public static final int SIZE_BIG = 24;

    // colors
    public static final String DISPLAY_RED = "#ff0e42";
    public static final String DISPLAY_GREEN = "#76ff00";
    public static final String DISPLAY_BLACK = "#090909";
    public static final String DISPLAY_GRAY = "#c3c3c3";

}