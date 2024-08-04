package com.pedromarininave.utils;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectFieldsUtils {
    public static void fillFieldsWithValue(Object object, HashMap<String, JTextField> textFields) {
        for (Map.Entry<String, JTextField> entry : textFields.entrySet()){
            String propertyName = entry.getKey();
            JTextField textField = entry.getValue();

            try {
                setField(object, propertyName, textField.getText());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void setField(Object object, String propertyName, String value) throws Exception {
        Field field = object.getClass().getDeclaredField(propertyName);
        field.setAccessible(true);
        Object convertedValue = value.convertToType(field.getType());
        field.set(object, convertedValue);
    }
}