package com.ewmw.addr.services.beans;

import java.lang.reflect.Constructor;

public class ReflectionHelper {
    public static boolean hasParameterlessPublicConstructor(Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameterCount() == 0) {
                return true;
            }
        }
        return false;
    }
}
