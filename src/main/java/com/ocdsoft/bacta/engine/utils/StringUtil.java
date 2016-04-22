package com.ocdsoft.bacta.engine.utils;

/**
 * Created by crush on 4/19/2016.
 */
public class StringUtil {
    public static String convertUnderscoreToUpper(final String filename) {
        final StringBuilder sb = new StringBuilder(filename.length());

        for (int i = 0; i < filename.length(); ++i) {
            char c = filename.charAt(i);

            if (i > 0) {
                if (c != '_') {
                    sb.append(c);
                } else {
                    sb.append(Character.toUpperCase(filename.charAt(++i)));
                }
            } else {
                sb.append(Character.toUpperCase(c));
            }
        }

        return sb.toString();
    }

    public static String upperFirst(final String string) {
        final char c = string.charAt(0);

        if (Character.isUpperCase(c))
            return string; //already has first letter uppercase.

        return Character.toUpperCase(c) + string.substring(1);
    }
}
