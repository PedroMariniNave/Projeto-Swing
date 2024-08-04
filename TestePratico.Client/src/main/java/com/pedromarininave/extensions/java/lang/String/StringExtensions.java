package com.pedromarininave.extensions.java.lang.String;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Extension
public class StringExtensions {

    public static Object convertToType(@This String value, Class<?> targetType) {
        try {
            if (targetType == String.class) {
                return value;
            } else if (targetType == int.class || targetType == Integer.class) {
                return Integer.parseInt(value);
            } else if (targetType == long.class || targetType == Long.class) {
                return Long.parseLong(value);
            } else if (targetType == double.class || targetType == Double.class) {
                return Double.parseDouble(value);
            } else if (targetType == boolean.class || targetType == Boolean.class) {
                return Boolean.parseBoolean(value);
            } else if (targetType == BigDecimal.class) {
                return new BigDecimal(value);
            } else if (targetType == Date.class) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                return formatter.parse(value);
            } else {
                throw new IllegalArgumentException("Unsupported field type: " + targetType);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error converting value: " + value + " to type: " + targetType, e);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Error parsing date value: " + value, e);
        }
    }
}