package com.eisgroup.tasktracker.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dmitriy Laletin
 * on 20 Июль 2017
 * at 01:49
 */
public class Constants {
    public static final String MESSAGES_RESOURCE = "locales/messages";
    public static final String UUID = "UUID";
    public static final String LOCALE = "LOCALE";
    public static final int TITLE_LENGTH_MIN = 1;
    public static final int TITLE_LENGTH_MAX = 100;
    public static final long DATE_SEGMENT_FOR_GRADIENT = TimeUnit.DAYS.toMillis(15);
    public static final int DATE_GRADIENT_HUE_START = 0;
    public static final int DATE_GRADIENT_HUE_END = 120;
    public static final int DATE_GRADIENT_SATURATION = 80;
    public static final long DATE_GRADIENT_BRIGHTNESS = 40;
    public static final String DEFAULT_TASK_COLOR = "grey";
    public static final String TASK_ID_PREFIX = "task_";
}
