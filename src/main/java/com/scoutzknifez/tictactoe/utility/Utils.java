package com.scoutzknifez.tictactoe.utility;

import com.scoutzknifez.tictactoe.structures.TimeAtMoment;
import com.scoutzknifez.tictactoe.utility.exceptions.ListsLengthMismatchException;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {
    /**
     * Creates a JSON from the field name keys and an object list
     * @param keys      The field names of the JSON
     * @param objects   The object(s) stored in JSON
     * @return          A JSON object
     */
    public static Map<String, ?> createJSON(String[] keys, Object... objects) {
        Map<String, Object> mappedJSON = new LinkedHashMap<>();

        if (keys.length != objects.length)
            throw new ListsLengthMismatchException(keys, objects);

        for (int i = 0; i < keys.length; i++)
            mappedJSON.put(keys[i], objects[i]);

        return mappedJSON;
    }

    /**
     * Creates a JSON from a field name and object
     * @param fieldName The field name of the JSON
     * @param object    The object(s) stored in JSON
     * @return          A JSON object
     */
    public static Map<String, ?> createJSON(String fieldName, Object object) {
        return Collections.singletonMap(fieldName, object);
    }

    /**
     * Sends a message out to console with time stamp of log execution
     *
     * NOTE: use %s to replace part of string with object
     *
     * @param message   message to display with replaceable characters for objects
     * @param objects   Objects to replace inside of the message string
     */
    public static void log(String message, Object... objects) {
        TimeAtMoment timeAtMoment = new TimeAtMoment(System.currentTimeMillis());

        System.out.println("[" + timeAtMoment + "] " + String.format(message, objects));
    }

    public static void log(Object object) {
        log("%s", object);
    }
}
